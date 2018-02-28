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


public class ExcelReaderSbMatrixRules {

	private static final String MATERIAL_FILE_NAME = "SB_MATRIX.xls";
	
	public static void main(String[] args) {
		ExcelReaderSbMatrixRules excelReaderObj = new ExcelReaderSbMatrixRules();
		excelReaderObj.readExcel();

	}
	
	public void readExcel() {
		Writer writer = null;
		try {

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(MATERIAL_FILE_NAME).getFile());
			
			File fileToWrite = new File("C:/Users/827445/Desktop/Script/scripts_sb_matrix_rules.txt");  
			writer = new BufferedWriter(new FileWriter(fileToWrite));
	            
            FileInputStream excelFile = new FileInputStream(file);
           // Workbook workbook = new XSSFWorkbook(excelFile);
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(2);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int count = 0;
            int moduleOpeartionId =1;
            iterator.next();
              while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                count++;
                Iterator<Cell> cellIterator = currentRow.iterator();
                int cellCount =0;
                writer.write(Constants.ong_sowcfg_std_sb_rules_lov + count +","+ moduleOpeartionId +",");
                String temp = "";//Constants.ong_sowcfg_std_module_operation_hrs_lov + count +","+ moduleOpeartionId +",";
                while (cellIterator.hasNext()) {
                	
                    Cell currentCell = cellIterator.next();
                    if(cellCount == 1 || cellCount == 2 || cellCount == 3 || cellCount == 4 || cellCount == 5 || cellCount == 6) {
                    	if(cellCount <6) {
                    	count++;
                    	}
                    	
                    	if(cellCount == 1) {
                    		writer.write("'moh"+"',");
                    	}else if(cellCount == 2) {
                    		writer.write("'hsr"+"',");
                    	}if(cellCount == 3) {
                    		writer.write("'hsrTdi"+"',");
                    	}if(cellCount == 4) {
                    		writer.write("'hsrTopCase"+"',");
                    	}if(cellCount == 5) {
                    		writer.write("'tdi"+"',");
                    	}if(cellCount ==6) {
                    		writer.write("'topCase"+"',");
                    	}
                    	  if (currentCell.getCellTypeEnum() == CellType.STRING) {
                              //System.out.print("'"+currentCell.getStringCellValue()+"'" + "--");
                              if(currentCell.getStringCellValue().equalsIgnoreCase("null")){
                              	writer.write(currentCell.getStringCellValue().trim()+",");
                              }else{
                              	writer.write("'"+currentCell.getStringCellValue().trim()+"',");
                              }
                          } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                          	double doubleValue = currentCell.getNumericCellValue();
                          	int intValue = (int)doubleValue;
                          	if(doubleValue == intValue){
                          		//System.out.print(intValue + "--");
                          		writer.write(intValue+",");
                          	}else {
                          		//System.out.print(doubleValue + "--");
                          		writer.write(doubleValue+",");
                          	}                   
                          }
                    	  
                    	  writer.write(");"+"\n");

                    	  if(cellCount <6) {
                    	  writer.write(Constants.ong_sowcfg_std_sb_rules_lov + count +","+ moduleOpeartionId +","+temp);
                    	  }
                    	  System.out.println(Constants.ong_sowcfg_std_sb_rules_lov + count +","+ moduleOpeartionId +","+temp);
                    		if(cellCount <6 && count % 6 == 0) {
                          		moduleOpeartionId++;
                          	}
                    }else{
                       if (currentCell.getCellTypeEnum() == CellType.STRING) {
                      //  System.out.print("'"+currentCell.getStringCellValue()+"'" + "--");
                        if(currentCell.getStringCellValue().equalsIgnoreCase("null")){
                        	writer.write(currentCell.getStringCellValue().trim()+",");
                        	temp = temp +currentCell.getStringCellValue().trim()+",";
                        }else{
                        	writer.write("'"+currentCell.getStringCellValue().trim()+"',");
                        	temp = temp + "'"+currentCell.getStringCellValue().trim()+"',";
                        }
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                    	double doubleValue = currentCell.getNumericCellValue();
                    	int intValue = (int)doubleValue;
                    	if(doubleValue == intValue){
                    		//System.out.print(intValue + "--");
                    		writer.write(intValue+",");
                    		temp = temp + intValue+",";
                    	}else {
                    		//System.out.print(doubleValue + "--");
                    		writer.write(doubleValue+",");
                    		temp = temp + doubleValue+",";
                    	}                   
                    }
                    }
                    cellCount++;
                }
              //  System.out.println();
                if(cellCount <6) {
                writer.write(");"+"\n");
                }
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
