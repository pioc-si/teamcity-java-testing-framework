package com.example.teamcity.api;

import com.example.teamcity.api.generators.TestData;
import com.example.teamcity.api.generators.TestDataGenerator;
import com.example.teamcity.api.generators.TestDataStorage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseApiTest extends BaseTest {

    public TestDataStorage testDataStorage;

    @BeforeMethod
    public void setupTest() {
        testDataStorage = TestDataStorage.getStorage();

    }

    @AfterMethod
    public void cleanTest() {
        testDataStorage.delete();
    }

}
