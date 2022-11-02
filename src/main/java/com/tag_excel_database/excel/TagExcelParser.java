package com.tag_excel_database.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;

public class TagExcelParser
{
    public void copyTagWorkbook(File inputFile, File outputFile)
    {
        FileOutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream(outputFile);
            Files.copy(inputFile.toPath(), outputStream);
            outputStream.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void processTagWorkbook(File tagFile)
    {
        // TODO: make these variables better
        int rowOnFirstSheet = 4; // Row 5
        int rowOnEachSheet = 1; // Row 2

        int orderedCellonFirstSheetPassenger = 2; // Column C
        int orderedCellonEachSheetPassenger = 3; // Column D

        int personalizedCellOnFirstSheetPassenger = 3; // Column D
        int personalizedCellOnEachSheetPassenger = 9; // Column J

        int straightRunCellOnFirstSheetPassenger = 4; // Column E
        int straightRunCellOnEachSheetPassenger = 10; // Column O

        int orderedCellOnFirstSheetMotorcycle = 5; // Column F
        int orderedCellOnEachSheetMotorcycle = 14; // Column O

        int personalizedCellOnFirstSheetMotorcycle = 6; // Column G
        int personalizedCellOnEachSheetMotorcycle = 20; // Column U

        int straightRunCellOnFirstSheetMotorcycle = 7; // Column H
        int straightRunCellOnEachSheetMotorcycle = 21; // Column V

        int passengerOrderedTotal = 0;
        int passengerPersonalizedTotal = 0;
        int passengerStraightRunTotal = 0;
        int motorcycleOrderedTotal = 0;
        int motorcyclePersonalizedTotal = 0;
        int motorcycleStraightRunTotal = 0;

        try
        {
            FileInputStream fileInputStream = new FileInputStream(tagFile);
            Workbook tagWorkbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet1 = tagWorkbook.getSheetAt(0);
            Row row;
            Cell cell;
            for (int i = 1; i < tagWorkbook.getNumberOfSheets(); i++)
            {
                // Passenger Cars
                // D2 from each sheet into column C of sheet 1, starting at row 5 until we're out of sheets
                row = tagWorkbook.getSheetAt(i).getRow(rowOnEachSheet);
                cell = row.getCell(orderedCellonEachSheetPassenger);
                passengerOrderedTotal += writeCell(sheet1, cell, rowOnFirstSheet, orderedCellonFirstSheetPassenger);

                // J2 from each sheet into column D of sheet 1, starting at row 5 until we're out of sheets
                row = tagWorkbook.getSheetAt(i).getRow(rowOnEachSheet);
                cell = row.getCell(personalizedCellOnEachSheetPassenger);
                passengerPersonalizedTotal += writeCell(sheet1, cell, rowOnFirstSheet, personalizedCellOnFirstSheetPassenger);

                // K2 from each sheet into column E of sheet 1, starting at row 5 until we're out of sheets
                row = tagWorkbook.getSheetAt(i).getRow(rowOnEachSheet);
                cell = row.getCell(straightRunCellOnEachSheetPassenger);
                passengerStraightRunTotal += writeCell(sheet1, cell, rowOnFirstSheet, straightRunCellOnFirstSheetPassenger);

                // Motorcycles
                // O2 from each sheet into column F of sheet 1, starting at row 5 until we're out of sheets
                row = tagWorkbook.getSheetAt(i).getRow(rowOnEachSheet);
                cell = row.getCell(orderedCellOnEachSheetMotorcycle);
                motorcycleOrderedTotal += writeCell(sheet1, cell, rowOnFirstSheet, orderedCellOnFirstSheetMotorcycle);

                // U2 from each sheet into column G of sheet 1, starting at row 5 until we're out of sheets
                row = tagWorkbook.getSheetAt(i).getRow(rowOnEachSheet);
                cell = row.getCell(personalizedCellOnEachSheetMotorcycle);
                motorcyclePersonalizedTotal += writeCell(sheet1, cell, rowOnFirstSheet, personalizedCellOnFirstSheetMotorcycle);


                // V2 from each sheet into column H of sheet 1, starting at row 5 until we're out of sheets
                row = tagWorkbook.getSheetAt(i).getRow(rowOnEachSheet);
                cell = row.getCell(straightRunCellOnEachSheetMotorcycle);
                motorcycleStraightRunTotal += writeCell(sheet1, cell, rowOnFirstSheet, straightRunCellOnFirstSheetMotorcycle);

                // Set cell styles on first sheet
                CellStyle cellStyle = sheet1.getRow(rowOnFirstSheet).getCell(orderedCellonFirstSheetPassenger).getCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle = sheet1.getRow(rowOnFirstSheet).getCell(personalizedCellOnFirstSheetPassenger).getCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle = sheet1.getRow(rowOnFirstSheet).getCell(straightRunCellOnFirstSheetPassenger).getCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle = sheet1.getRow(rowOnFirstSheet).getCell(orderedCellOnFirstSheetMotorcycle).getCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle = sheet1.getRow(rowOnFirstSheet).getCell(personalizedCellOnFirstSheetPassenger).getCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle = sheet1.getRow(rowOnFirstSheet).getCell(straightRunCellOnFirstSheetMotorcycle).getCellStyle();
                cellStyle.setAlignment(HorizontalAlignment.CENTER);

                rowOnFirstSheet += 1;
            }

            if (sheet1.getRow(rowOnFirstSheet) == null)
            {
                sheet1.createRow(rowOnFirstSheet);
            }
            sheet1.getRow(rowOnFirstSheet).createCell(orderedCellonFirstSheetPassenger - 1).setCellValue("Totals");
            sheet1.getRow(rowOnFirstSheet).createCell(orderedCellonFirstSheetPassenger).setCellValue(passengerOrderedTotal);
            sheet1.getRow(rowOnFirstSheet).createCell(personalizedCellOnFirstSheetPassenger).setCellValue(passengerPersonalizedTotal);
            sheet1.getRow(rowOnFirstSheet).createCell(straightRunCellOnFirstSheetPassenger).setCellValue(passengerStraightRunTotal);
            sheet1.getRow(rowOnFirstSheet).createCell(orderedCellOnFirstSheetMotorcycle).setCellValue(motorcycleOrderedTotal);
            sheet1.getRow(rowOnFirstSheet).createCell(personalizedCellOnFirstSheetMotorcycle).setCellValue(motorcyclePersonalizedTotal);
            sheet1.getRow(rowOnFirstSheet).createCell(straightRunCellOnFirstSheetMotorcycle).setCellValue(motorcycleStraightRunTotal);

            try
            {
                FileOutputStream outputStream = new FileOutputStream(tagFile);
                tagWorkbook.write(outputStream);
                outputStream.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                tagWorkbook.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private int writeCell(Sheet sheet, Cell cell, int rowIndex, int cellIndex)
    {
        if (sheet.getRow(rowIndex) == null)
        {
            sheet.createRow(rowIndex);
        }
        if (sheet.getRow(rowIndex).getCell(cellIndex) == null)
        {
            sheet.getRow(rowIndex).createCell(cellIndex);
        }
        sheet.getRow(rowIndex).getCell(cellIndex).setCellValue((int)cell.getNumericCellValue());
        return (int)cell.getNumericCellValue();
    }
}