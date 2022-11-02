package com.tag_excel_database;

import com.tag_excel_database.configuration.ApplicationProperties;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class TagExcelDatabaseApplication extends Application
{
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception
    {
        springContext = SpringApplication.run(TagExcelDatabaseApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(TagExcelDatabaseApplication.class.getResource("tagexceldatabaseview.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        ApplicationProperties.setApplicationStage(stage);
        Scene scene = new Scene(rootNode, 800, 400);
        stage.setTitle("Alabama DOC Tag Excel/Database");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception
    {
        springContext.close();
    }
}