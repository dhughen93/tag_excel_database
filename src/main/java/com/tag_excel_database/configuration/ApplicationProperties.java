package com.tag_excel_database.configuration;

import javafx.stage.Stage;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.format.DateTimeFormatter;

@Configuration
@PropertySource("classpath:resources/application.properties")
public class ApplicationProperties
{
    public static Stage applicationStage = null;
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
}
