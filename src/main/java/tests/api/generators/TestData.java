package tests.api.generators;

import tests.api.models.BuildType;
import tests.api.models.NewProjectDescription;
import tests.api.models.User;
import tests.api.requests.unchecked.UncheckedUser;
import tests.api.spec.Specifications;
import tests.api.requests.unchecked.UncheckedProject;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestData {

    private User user;
    private NewProjectDescription project;
    private BuildType buildType;

    public void delete() {
        var spec = Specifications.getSpec().authSpec(user);

        new UncheckedProject(spec).delete(project.getId());
        new UncheckedUser(spec).delete(user.getUsername());

    }

}
