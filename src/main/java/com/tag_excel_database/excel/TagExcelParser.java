package com.tag_excel_database.excel;

import org.apache.poi.ss.usermodel.*;
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

        int orderedCellonFirstSheet = 2; // Column C
        int orderedRowOnEachSheet = 1; // Row 2
        int orderedCellonEachSheet = 3; // Column D

        int personalizedCellOnFirstSheet = 3; // Column D
        int shippedRowOnEachSheet = 1; // Row 2
        int shippedCellOnEachSheet = 0; // Column J



        try
        {
            FileInputStream fileInputStream = new FileInputStream(tagFile);
            Workbook tagWorkbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet1 = tagWorkbook.getSheetAt(0);
            Row row;
            Cell cell;
            for (int i = 1; i < tagWorkbook.getNumberOfSheets(); i++)
            {
                // We need the value of D2 from each sheet placed into column C of sheet 1, starting at row 5 until we're out of sheets
                row = tagWorkbook.getSheetAt(i).getRow(orderedRowOnEachSheet);
                cell = row.getCell(orderedCellonEachSheet);
                writeCell(sheet1, row, cell, rowOnFirstSheet, orderedCellonFirstSheet);

                cell = row.getCell(shippedCellOnEachSheet);
                writeCell(sheet1, row, cell, rowOnFirstSheet, shippedCellOnEachSheet);






                CellStyle orderedCellStyle = sheet1.getRow(rowOnFirstSheet).getCell(orderedCellonFirstSheet).getCellStyle();
                orderedCellStyle.setAlignment(HorizontalAlignment.CENTER);
                rowOnFirstSheet += 1;
            }
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

    private void writeCell(Sheet sheet, Row row, Cell cell, int rowIndex, int cellIndex)
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
    }
}