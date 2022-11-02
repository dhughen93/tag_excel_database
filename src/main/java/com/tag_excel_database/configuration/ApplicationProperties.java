package com.tag_excel_database.configuration;

import javafx.stage.Stage;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.format.DateTimeFormatter;

@Configuration
@PropertySource(value = "classpath:application.properties", name = "appProperties")
public class ApplicationProperties
{
    private static Stage applicationStage = null;
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public static Stage getApplicationStage() {
        return applicationStage;
    }

    public static void setApplicationStage(Stage applicationStage) {
        ApplicationProperties.applicationStage = applicationStage;
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }
}