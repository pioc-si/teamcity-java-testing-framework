package tests.api.requests.checked;


import tests.api.models.Project;
import tests.api.requests.CrudInterface;
import tests.api.requests.Request;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import tests.api.requests.unchecked.UncheckedProject;

public class CheckedProject extends Request implements CrudInterface {


    public CheckedProject(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public Project create(Object obj) {
        return new UncheckedProject(spec).create(obj)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Project get(String id) {
        return new UncheckedProject(spec)
                .get(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Object update(String id, Object obj) {
        return null;
    }


    @Override
    public String delete(String id) {
        return new UncheckedProject(spec)
                .delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}
