package com.example.teamcity.api.generators;

import com.example.teamcity.api.models.NewProjectDescription;
import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.models.User;
import com.example.teamcity.api.requests.unchecked.UncheckedProject;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestData {

    private User user;
    private NewProjectDescription project;

    public void delete() {
        new UncheckedProject(user).delete(project.getId());
    }

}
