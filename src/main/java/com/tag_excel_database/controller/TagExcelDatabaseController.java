package com.tag_excel_database.controller;

import com.tag_excel_database.excel.TagExcelParser;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        String outputDirectory = selectedDirectory.getAbsolutePath() + "/General_Tags_Processed_" + date.format(dateTimeFormatter) + ".xlsx";
        outputFile = new File(outputDirectory);
        if (outputFile != null)
        {
            outputTextField.setText(outputFile.getAbsolutePath());
        }

    }

    @FXML
    protected void onSubmitButtonClick()
    {
        if (!inputTextField.getText().isEmpty() && !outputTextField.getText().isEmpty())
        {
            TagExcelParser excelParser = new TagExcelParser();
            excelParser.copyTagWorkbook(inputFile, outputFile);
            excelParser.processTagWorkbook(outputFile);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Done");
            alert.setContentText("Workbook has been processed.");
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enter Input and Output");
            alert.setContentText("Please enter input file and output directory before processing.\nGo 'Noles >---;;;--->");
            alert.show();
        }
    }
}