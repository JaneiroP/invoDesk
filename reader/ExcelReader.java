package reader;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static Workbook getWorkbook(String rutaArchivo) throws IOException {
        if (!rutaArchivo.endsWith(".xlsx")) {
            throw new IllegalArgumentException("Solo se permiten archivos .xlsx");
        }
        FileInputStream fis = new FileInputStream(new File(rutaArchivo));
        return new XSSFWorkbook(fis);  // debes cerrar esto en el DAO
    }

    public static void leerExcel(String rutaArchivo) {
        // Tu método original, sin cambios
    }
}
