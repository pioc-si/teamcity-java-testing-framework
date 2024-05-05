package com.example.teamcity.api.generators;

import com.example.teamcity.api.models.NewProjectDescription;
import com.example.teamcity.api.models.User;
import com.example.teamcity.api.requests.unchecked.UncheckedProject;
import com.example.teamcity.api.spec.Specifications;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestData {

    private User user;
    private NewProjectDescription project;

    public void delete() {
        var spec = Specifications.getSpec().authSpec(user);
        new UncheckedProject(spec).delete(project.getId());
    }

}
