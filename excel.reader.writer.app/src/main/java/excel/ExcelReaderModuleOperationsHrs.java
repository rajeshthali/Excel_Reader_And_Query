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


public class ExcelReaderModuleOperationsHrs {

	private static final String MATERIAL_FILE_NAME = "TCS_MasterDatafile_Rev18.xls";
	
	public static void main(String[] args) {
		ExcelReaderModuleOperationsHrs excelReaderObj = new ExcelReaderModuleOperationsHrs();
		excelReaderObj.readExcel();

	}
	
	public void readExcel() {
		Writer writer = null;
		try {

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(MATERIAL_FILE_NAME).getFile());
			
			File fileToWrite = new File("C:/Users/827445/Desktop/Script/Module_Operations_Hrs.txt");  
			writer = new BufferedWriter(new FileWriter(fileToWrite));
	            
            FileInputStream excelFile = new FileInputStream(file);
           // Workbook workbook = new XSSFWorkbook(excelFile);
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(17);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int count = 0;
            int moduleOpeartionId =1;
            iterator.next();
              while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                count++;
                Iterator<Cell> cellIterator = currentRow.iterator();
                int cellCount =0;
                writer.write(Constants.ong_sowcfg_std_module_operation_hrs_lov + count +","+ moduleOpeartionId +",");
                String temp = "";//Constants.ong_sowcfg_std_module_operation_hrs_lov + count +","+ moduleOpeartionId +",";
                while (cellIterator.hasNext()) {
                	
                    Cell currentCell = cellIterator.next();
                    if(cellCount == 4 || cellCount == 5 || cellCount == 6 || cellCount == 7 || cellCount == 8 || cellCount == 9) {
                    	//writer.write(Constants.ong_sowcfg_std_module_operation_hrs_lov + count +","+ moduleOpeartionId +",");
                    	//writer.write(temp);
                    	if(cellCount <9) {
                    	count++;
                    	}
                    	
                    	if(cellCount == 4) {
                    		writer.write("'LM2500SAC"+"',");
                    	}else if(cellCount == 5) {
                    		writer.write("'LM2500DLE"+"',");
                    	}if(cellCount == 6) {
                    		writer.write("'LM2500+SAC"+"',");
                    	}if(cellCount == 7) {
                    		writer.write("'LM2500+DLE"+"',");
                    	}if(cellCount == 8) {
                    		writer.write("'LM2500+G4SAC"+"',");
                    	}if(cellCount ==9) {
                    		writer.write("'LM2500+G4DLE"+"',");
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

                    	  if(cellCount <9) {
                    	  writer.write(Constants.ong_sowcfg_std_module_operation_hrs_lov + count +","+ moduleOpeartionId +","+temp);
                    	  }
                    	  System.out.println(Constants.ong_sowcfg_std_module_operation_hrs_lov + count +","+ moduleOpeartionId +","+temp);
                    		if(cellCount <9 && count % 6 == 0) {
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
                if(cellCount <9) {
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
