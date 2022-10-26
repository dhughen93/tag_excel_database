package com.tag_excel_database.controller;

import com.tag_excel_database.excel.TagExcelParser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Properties;

import static com.tag_excel_database.ApplicationProperties.applicationStage;
import static com.tag_excel_database.ApplicationProperties.dateTimeFormatter;

public class TagExcelDatabaseController
{
    @FXML
    private Label welcomeText;
    @FXML
    private TextField inputTextField;
    @FXML
    private TextField outputTextField;

    File inputFile;
    File outputFile;

    @FXML
    protected void onInputButtonClick()
    {
        FileChooser inputFileChooser = new FileChooser();
        inputFileChooser.setTitle("Select Input Excel Spreadsheet");
        inputFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel", "*.xlsx"));
        inputFile = inputFileChooser.showOpenDialog(applicationStage);
        if (inputFile != null)
        {
            inputTextField.setText(inputFile.getAbsolutePath());
        }
    }

    @FXML
    protected void onOutputButtonClick()
    {
        LocalDateTime date = LocalDateTime.now();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Output Directory");
        File selectedDirectory = directoryChooser.showDialog(applicationStage);
        try(InputStream input = new FileInputStream("src/main/resources/application.properties"))
        {
            Properties properties = new Properties();
            properties.load(input);
            String outputDirectory = selectedDirectory.getAbsolutePath() + "/" + properties.getProperty("outputfilename") + date.format(dateTimeFormatter) + ".xlsx";
            outputFile = new File(outputDirectory);
            if (outputFile != null)
            {
                outputTextField.setText(outputFile.getAbsolutePath());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSubmitButtonClick()
    {
        TagExcelParser excelParser = new TagExcelParser();
        excelParser.copyTagWorkbook(inputFile, outputFile);
    }
}