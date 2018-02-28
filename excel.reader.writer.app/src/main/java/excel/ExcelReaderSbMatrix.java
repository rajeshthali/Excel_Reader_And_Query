package excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelReaderSbMatrix {

	private static final String MATERIAL_FILE_NAME = "SB_MATRIX.xls";
	
	public static void main(String[] args) {
		ExcelReaderSbMatrix excelReaderObj = new ExcelReaderSbMatrix();
		excelReaderObj.readExcel();

	}
	
	public void readExcel() {
		Writer writer = null;
		try {

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(MATERIAL_FILE_NAME).getFile());
			
			File fileToWrite = new File("C:/Users/827445/Desktop/Script/scripts_sb_matrix.txt");  
			writer = new BufferedWriter(new FileWriter(fileToWrite));
	            
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int count = 0;
            iterator.next();
              while (iterator.hasNext()) {
            	
            	count++;
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                writer.write(Constants.ong_sowcfg_std_sb_lov + count +",");
                while (cellIterator.hasNext()) {
                	
                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                   
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print("'"+currentCell.getStringCellValue()+"'" + "--");
                        if(currentCell.getStringCellValue().equalsIgnoreCase("null")){
                        	writer.write(currentCell.getStringCellValue().trim()+",");
                        }else{
                        	writer.write("'"+currentCell.getStringCellValue().trim()+"',");
                        }
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                    	double doubleValue = currentCell.getNumericCellValue();
                    	int intValue = (int)doubleValue;
                    	if(doubleValue == intValue){
                    		System.out.print(intValue + "--");
                    		writer.write(intValue+",");
                    	}else {
                    		System.out.print(doubleValue + "--");
                    		writer.write(doubleValue+",");
                    	}                   
                    }

                }
                System.out.println();
                writer.write(");"+"\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
        	System.out.println(e);
        }finally {
        	if(writer!=null){
        		try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }

    }

}
