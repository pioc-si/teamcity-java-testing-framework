package com.example.teamcity.api;

import com.example.teamcity.api.generators.TestData;
import com.example.teamcity.api.generators.TestDataGenerator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseApiTest extends BaseTest {

    public TestData testData;

    @BeforeMethod
    public void setupTest() {
        testData = new TestDataGenerator().generate();

    }

    @AfterMethod
    public void cleanTest() {
        testData.delete();
    }

}
