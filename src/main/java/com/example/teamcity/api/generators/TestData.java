package com.example.teamcity.api.generators;

import com.example.teamcity.api.models.BuildType;
import com.example.teamcity.api.models.NewProjectDescription;
import com.example.teamcity.api.models.User;
import com.example.teamcity.api.requests.unchecked.UncheckedUser;
import com.example.teamcity.api.spec.Specifications;
import com.example.teamcity.api.requests.unchecked.UncheckedProject;
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
