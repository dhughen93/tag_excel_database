package com.tag_excel_database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TagExcelDatabaseApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        ApplicationProperties.setApplicationStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(TagExcelDatabaseApplication.class.getResource("tagexceldatabaseview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Alabama DOC Tag Excel/Database");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}