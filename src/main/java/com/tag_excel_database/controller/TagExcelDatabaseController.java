package com.tag_excel_database.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Date;

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
        LocalDate date = LocalDate.now();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Output Directory");
        directoryChooser.setInitialDirectory(inputFile);
        File selectedDirectory = directoryChooser.showDialog(applicationStage);
        String outputDirectory = selectedDirectory.getAbsolutePath() + "/General Tags Processed_" + date.format(dateTimeFormatter) + ".xlsx";
        outputFile = new File(outputDirectory);
        if (outputFile != null)
        {
            outputTextField.setText(outputFile.getAbsolutePath());
        }
    }

    @FXML
    protected void onSubmitButtonClick()
    {

    }
}