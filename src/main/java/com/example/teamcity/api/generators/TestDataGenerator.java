package com.example.teamcity.api.generators;

import com.example.teamcity.api.models.NewProjectDescription;
import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.models.User;

public class TestDataGenerator {

    public TestData generate() {
        var user = User.builder()
                .username("admin")
                .password("admin")
                .build();

        var project = NewProjectDescription
                .builder()
                .parentProject(Project.builder()
                        .locator("_Root")
                        .build())
                .name(RandomData.getString())
                .id(RandomData.getString())
                .copyAllAssociatedSettings(true)
                .build();

        return TestData.builder()
                .user(user)
                .project(project)
                .build();

    }

}
