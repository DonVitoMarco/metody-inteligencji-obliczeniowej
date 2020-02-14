package pl.marek.doc;

import io.vavr.Tuple3;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelWriter implements Documentation {

    @Override
    public void write(List<Tuple3<Integer, Double, Double>> values) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        for (Tuple3<Integer, Double, Double> value : values) {
            Row row = sheet.createRow(++rowCount);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(value._1);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(value._2);

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(value._3);
        }

        try(FileOutputStream outputStream = new FileOutputStream("chart.xls")) {
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
