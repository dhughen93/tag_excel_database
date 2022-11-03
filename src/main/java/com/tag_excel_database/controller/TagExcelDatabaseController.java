package com.tag_excel_database.controller;

import com.tag_excel_database.configuration.ApplicationProperties;
import com.tag_excel_database.excel.TagExcelParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TagExcelDatabaseController implements Initializable
{
    @FXML
    private TextField inputTextField;
    @FXML
    private TextField outputTextField;
    @FXML
    private ChoiceBox tagChoiceBox;

    @Value("${tag.types}")
    String[] tagTypes;

    private TagExcelParser tagExcelParser;

    public TagExcelDatabaseController(TagExcelParser tagExcelParser)
    {
        this.tagExcelParser = tagExcelParser;
    }

    File inputFile;
    File outputFile;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        tagChoiceBox.getItems().setAll(tagTypes);
        tagChoiceBox.setValue(tagTypes[0]);
    }

    @FXML
    protected void onInputButtonClick()
    {
        FileChooser inputFileChooser = new FileChooser();
        inputFileChooser.setTitle("Select Input Excel Spreadsheet");
        inputFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel", "*.xlsx"));
        inputFile = inputFileChooser.showOpenDialog(ApplicationProperties.getApplicationStage());
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
        File selectedDirectory = directoryChooser.showDialog(ApplicationProperties.getApplicationStage());
        if (selectedDirectory != null)
        {
            String outputDirectory = selectedDirectory.getAbsolutePath() + "/" + tagChoiceBox.getValue() + date.format(ApplicationProperties.getDateTimeFormatter()) + ".xlsx";
            outputFile = new File(outputDirectory);
            if (outputFile != null)
            {
                outputTextField.setText(outputFile.getAbsolutePath());
            }
        }
    }

    @FXML
    protected void onSubmitButtonClick()
    {
        if (!inputTextField.getText().isEmpty() && !outputTextField.getText().isEmpty())
        {
            tagExcelParser.copyTagWorkbook(inputFile, outputFile);
            tagExcelParser.processTagWorkbook(outputFile);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Done");
            alert.setContentText("Workbook has been processed.");
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enter Input and Output");
            alert.setContentText("Please enter input file and output directory before processing.");
            alert.show();
        }
    }
}