package ru.inno.utils;

import java.util.Properties;

import static ru.inno.utils.PropsReaderUtils.getPropertyHandler;
import static ru.inno.utils.PropsReaderUtils.readProperties;

public class TestProperties {

    private static final String propertiesFileName = "tests.properties";
    private final String baseUrl;
    private final String userName;
    private final String password;
    private final String loginEndpoint;
    private final String generateTokenEndpoint;
    private final String authEndpoint;
    private final String deleteAllEndpoint;

    public TestProperties() {
        Properties properties = readProperties(propertiesFileName);
        baseUrl = getPropertyHandler(properties, "base.url", "https://demoqa.com/");
        userName = getPropertyHandler(properties, "userName", null);
        password = getPropertyHandler(properties, "password", null);
        loginEndpoint = getPropertyHandler(properties, "login.endpoint", "/login");
        generateTokenEndpoint = getPropertyHandler(properties, "generateToken.endpoint", "/Account/v1/GenerateToken");
        authEndpoint = getPropertyHandler(properties, "auth.endpoint", "/Account/v1/Login");
        deleteAllEndpoint = getPropertyHandler(properties, "deleteAll.endpoint", "/BookStore/v1/Books");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginEndpoint() {
        return loginEndpoint;
    }

    public String getGenerateTokenEndpoint() {
        return generateTokenEndpoint;
    }

    public String getAuthEndpoint() {
        return authEndpoint;
    }

    public String getDeleteAllEndpoint() {
        return deleteAllEndpoint;
    }

    public static TestProperties getProperties() {
        return PropsHolder.instance;
    }

    private static class PropsHolder {
        public static final TestProperties instance = new TestProperties();
    }
}
