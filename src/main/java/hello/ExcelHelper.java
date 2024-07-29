package hello;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelHelper {

  private Logger _log = Logger.getLogger(this.getClass().getName());
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "Número", "Cor", "Data", "Horário" };
  static String SHEET = "Rolls";

  public static boolean hasExcelFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public List<Roll> excelToRolls(InputStream is) {
    try {

          // String text = new String(is.readAllBytes(), StandardCharsets.UTF_8);
          // _log.info("hey "+text);

      XSSFWorkbook workbook = new XSSFWorkbook(is);


      Sheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rows = sheet.iterator();

      List<Roll> rolls = new ArrayList<Roll>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
          if (rowNumber < 2) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        Roll.RollBuilder rollBuilder = Roll.builder();

        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();
        _log.info("currentCell "+currentCell);

          switch (cellIdx) {
          case 0:
              rollBuilder.roll((int)currentCell.getNumericCellValue());
            break;
          case 1:
            // rollBuilder.color(currentCell.getStringCellValue());
            break;

          case 2:
            // rollBuilder.created(Vj(currentCell.getStringCellValue());
            // tutorial.setDescription(currentCell.getStringCellValue());
            break;

          case 3:
            // rollBuilder.color(currentCell.getStringCellValue());
            // tutorial.setPublished(currentCell.getBooleanCellValue());
            break;

          default:
            break;
          }

          cellIdx++;
        }

        rolls.add(rollBuilder.build());
      }

      workbook.close();

      return rolls;
    } catch (Throwable e) {
      _log.warning("error: "+e.getMessage());
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
}