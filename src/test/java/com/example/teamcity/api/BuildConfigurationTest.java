package com.example.teamcity.api;

import com.example.teamcity.api.requests.checked.CheckedProject;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest {
    @Test
    public void buildConfigurationTest(){

        var project = new CheckedProject(testData.getUser()).create(testData.getProject());

        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());
    }
}