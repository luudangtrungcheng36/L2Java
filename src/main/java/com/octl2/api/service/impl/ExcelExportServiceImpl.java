package com.octl2.api.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.octl2.api.repository.ExcelExportRepository;
import com.octl2.api.repository.projection.ExcelExportProjection;
import com.octl2.api.service.ExcelExportService;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    private final ExcelExportRepository excelExportRepository;

    public ExcelExportServiceImpl(ExcelExportRepository excelExportRepository) {
        this.excelExportRepository = excelExportRepository;
    }

    @Value("${logistics.level-mapping:1}")
    private Integer levelMapping;

    @Override
    public byte[] exportExcel() {

        List<ExcelExportProjection> data = getLogistics();

        return createExcel(data);
    }

    private List<ExcelExportProjection> getLogistics() {
        if (levelMapping == 1) {
            return excelExportRepository.findLogisticsLevel1(4);
        }
        if (levelMapping == 2) {
            return excelExportRepository.findLogisticsLevel2(4);
        }
        if (levelMapping == 3) {
            return excelExportRepository.findLogisticsLevel3(4);
        }

        throw new IllegalArgumentException("Invalid level mapping: " + levelMapping);
    }

    private byte[] createExcel(List<ExcelExportProjection> data) {

        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Logistics");

            createHeader(sheet);

            createDataRows(sheet, data);

            workbook.write(outputStream);

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error while creating Excel file", e);
        }
    }

    private void createDataRows(Sheet sheet, List<ExcelExportProjection> data) {
        int rowIndex = 1;

        for (ExcelExportProjection item : data) {
            Row row = sheet.createRow(rowIndex);

            row.createCell(0).setCellValue(rowIndex++);
            row.createCell(1).setCellValue(Objects.toString(item.getProvinceName(), ""));
            row.createCell(2).setCellValue(Objects.toString(item.getDistrictName(), ""));
            row.createCell(3).setCellValue(Objects.toString(item.getSubdistrictName(), ""));
            row.createCell(4).setCellValue(Objects.toString(item.getFfmName(), ""));
            row.createCell(5).setCellValue(Objects.toString(item.getLmName(), ""));
            row.createCell(6).setCellValue(Objects.toString(item.getWarehouseName(), ""));
        }
    }

    private void createHeader(Sheet sheet) {
        Row headeRow = sheet.createRow(0);

        String[] headers = {
                "STT",
                "Tỉnh",
                "Huyện",
                "Xã",
                "FFM name",
                "LM name",
                "Warehouse name"
        };

        for (int i = 0; i < headers.length; i++) {
            headeRow.createCell(i).setCellValue(headers[i]);
        }
    }

}
