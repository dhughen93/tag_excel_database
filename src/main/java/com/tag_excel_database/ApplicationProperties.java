package com.tag_excel_database;

import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class ApplicationProperties 
{
    public static Stage applicationStage = null;
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
}
