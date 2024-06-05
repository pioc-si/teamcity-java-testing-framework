package tests.api.generators;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
    private static final Integer LENGTH = 10;
    private static final Integer MAXLENGTH = 250;
    private static final Integer MAXLENGTHPROJECTNAME = 75;

    public static String getString(){
        return "test_" + RandomStringUtils.randomAlphabetic(LENGTH);
    }

    public static String get255String(){
        return "test_" + RandomStringUtils.randomAlphabetic(MAXLENGTH);
    }

    public static String get256String(){
        return "test1_" + RandomStringUtils.randomAlphabetic(MAXLENGTH);
    }

    public static String get80String(){
        return "test_" + RandomStringUtils.randomAlphabetic(MAXLENGTH);
    }


}
