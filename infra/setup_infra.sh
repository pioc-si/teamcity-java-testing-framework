cd ..

teamcity_tests_directory=$(pwd)
workdir="teamcity_tests_infrastructure"
teamcity_server_workdir="teamcity_server"
teamcity_agent_workdir="teamcity_agent"
selenoid_workdir="selenoid"
teamcity_server_container_name="teamcity_server_instance"
teamcity_agent_container_name="teamcity_agent_instance"
selenoid_container_name="selenoid_instance"
selenoid_ui_container_name="selenoid_ui_instance"
teamcity_server_instance="teamcity-server-instance"

container_names=($teamcity_server_instance $teamcity_agent_container_name $selenoid_container_name $selenoid_ui_container_name)
workdir_names=($teamcity_server_workdir $teamcity_agent_workdir $selenoid_workdir)


############################################
echo "Request ip"
export ips=$(ifconfig | grep -Eo 'inet (addr:)?([0-9]*\.){3}[0-9]*' | grep -Eo '([0-9]*\.){3}[0-9]*' | grep -v '127.0.0.1')
export ip=${ips%%$'\n'*}
echo "Current IP: $ip"


############################################
echo "Delete previous run data"
rm -rf $workdir
mkdir $workdir
cd $workdir

for dir in "${workdir_names[@]}"; do
  mkdir $dir
done

for container in "${container_names[@]}"; do
  docker stop $container
  docker rm $container
done

############################################
echo "Start teamcity server"

cd $teamcity_server_workdir
mkdir data
mkdir logs

docker run -d --name teamcity-server-instance -v $(pwd)/data:/data/teamcity_server/datadir -v $(pwd)/logs:/opt/teamcity/logs -p 8111:8111 jetbrains/teamcity-server:latest

docker run --rm -v $(pwd)/logs:/opt/teamcity/logs busybox chown -R 1000:1000 /opt/teamcity/logs
docker run --rm -v $(pwd)/data:/data/teamcity_server/datadir busybox chown -R 1000:1000 /data/teamcity_server/datadir

docker start teamcity-server-instance



echo "Teamcity Server is running..."

############################################
echo "Start selenoid"
cd .. && cd $selenoid_workdir
mkdir config

cp ../../infra/browsers.json config/browsers.json

docker run -d   --name $selenoid_container_name -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v $(pwd)/config/:/etc/selenoid/:ro  aerokube/selenoid:latest-release

image_names=($(awk -F'"' '/"image": "/{print $4}' "$(pwd)/config/browsers.json"))

echo "Pull all browser images: $image_names"

for image in "${image_names[@]}"; do
  docker pull $image
done

############################################
echo "Start selenoid-ui"

docker run -d --name $selenoid_ui_container_name  --link $selenoid_container_name  -p 8080:8080 aerokube/selenoid-ui --selenoid-uri "http://localhost:4444"


############################################
echo "Setup teamcity server"
echo $(pwd)
cd ../..
mvn clean test -Dtest=tests.ui.SetupTest#startUpTest


############################################
echo "Parse superuser token"
echo $(pwd)
superuser_token=$(grep -o 'Super user authentication token: [0-9]*' $workdir/$teamcity_server_workdir/logs/teamcity-server.log | awk '{print $NF}')
echo "Super user token: $superuser_token"

