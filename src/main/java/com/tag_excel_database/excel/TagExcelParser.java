package com.tag_excel_database.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;

import static org.apache.poi.ss.usermodel.Cell.*;

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
        int orderedRowOnFirstSheet = 4; // Row 5
        int orderedCellonFirstSheet = 2; // Column C
        int orderedRowOnEachSheet = 1; // Row 2
        int orderedCellonEachSheet = 3; // Column D
        try
        {
            Workbook tagWorkbook = WorkbookFactory.create(new FileInputStream(tagFile));
            Sheet sheet1 = tagWorkbook.getSheetAt(0);
            Row row;
            Cell cell;
            for (int i = 1; i < tagWorkbook.getNumberOfSheets(); i++)
            {
                // We need the value of D2 from each sheet placed into column C of sheet 1, starting at row 5 until we're out of sheets
                row = tagWorkbook.getSheetAt(i).getRow(orderedRowOnEachSheet);
                cell = row.getCell(orderedCellonEachSheet);
                if (sheet1.getRow(orderedRowOnFirstSheet) == null)
                {
                    sheet1.createRow(orderedRowOnFirstSheet);
                }
                if (sheet1.getRow(orderedRowOnFirstSheet).getCell(orderedCellonFirstSheet) == null)
                {
                    sheet1.getRow(orderedRowOnFirstSheet).createCell(orderedCellonFirstSheet);
                }
                sheet1.getRow(orderedRowOnFirstSheet).getCell(orderedCellonFirstSheet).setCellValue((int)cell.getNumericCellValue());
                CellStyle orderedCellStyle = sheet1.getRow(orderedRowOnFirstSheet).getCell(orderedCellonFirstSheet).getCellStyle();
                orderedCellStyle.setAlignment(HorizontalAlignment.CENTER);
                System.out.println("Value from " + cell.getSheet().getSheetName() + ": " + (int)cell.getNumericCellValue() + " Sheet 1 value: " + sheet1.getRow(orderedRowOnFirstSheet).getCell((int)orderedCellonFirstSheet));
                orderedRowOnFirstSheet += 1;
            }
            try (FileOutputStream outputStream = new FileOutputStream(tagFile))
            {
                tagWorkbook.write(outputStream);
                tagWorkbook.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}