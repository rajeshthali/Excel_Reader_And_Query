package com.example.demo;

 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(UtilityController.class);
	
	 //for local
	/*private static final String url = "jdbc:postgresql://localhost:5432/postgres";
	private static final String user = "postgres";
	private static final String password = "1234";*/
	
	//for Dev
    /*private static final  String url = "jdbc:postgresql://db-25b99d65-25d1-4b32-a89f-6da115be0051.c7uxaqxgfov3.us-west-2.rds.amazonaws.com:5432/onereportal_postgres_service_local";
      private static final  String user = "uec13dca3b4684419b36c9c77aceb4db9";
      private static final String password = "4b37888b439044cd85ca8acc9615e48b";*/
     //for stage
    /* private static final String url = "jdbc:postgresql://db-a68ccb6c-e38a-4584-8074-8e71da3418eb.c7uxaqxgfov3.us-west-2.rds.amazonaws.com:5432/onereportal_postgres_service_preview";
     private static final String user = "u77cabed280504884b61ef22ca77636bc";
     private static final String password = "99473b95dd2d4b75a7e885153dfa591d";*/
     //for prod
     private static final String url = "jdbc:postgresql://db-fea1f8b7-5c7d-4912-90af-82bcc36633fe.c7uxaqxgfov3.us-west-2.rds.amazonaws.com:5432/onerepairportal_postgres_service";
     private static final String user = "oneppuser";
     private static final String password = "oneppuser123";

    @RequestMapping("/TableNames")
    public ArrayList<String> index() {

    	 Connection con = null;
         Statement st = null;
         ResultSet rs = null;
         ArrayList<String> al=new ArrayList<String>();
         try {
             con = DriverManager.getConnection(url, user, password);
             st = con.createStatement();
             //for local
            //  rs = st.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'repairs_usm_portal' AND TABLE_CATALOG='postgres'");
            
             //for dev
             //rs = st.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'repairs_usm_portal' AND TABLE_CATALOG='onereportal_postgres_service_local'");
             //for stage
             // rs = st.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'repairs_usm_portal' AND TABLE_CATALOG='onereportal_postgres_service_preview'");
             //for prod
              rs = st.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'repairs_usm_portal' AND TABLE_CATALOG='onerepairportal_postgres_service'");
            
             while(rs.next()) {

            	al.add(rs.getString(1));
             }




         } catch (SQLException ex) {
        	 System.out.println("Exception while creating DB connection :: "+ex.getMessage());
          } finally {
             try {
                 if (rs != null) {
                     rs.close();
                 }
                 if (st != null) {
                     st.close();
                 }
                 if (con != null) {
                     con.close();
                 }

             } catch (SQLException ex) {
            	 System.out.println("Exception while creating DB connection :: "+ex.getMessage());
        
             }
         }
        return al;
    }
    @RequestMapping("/TableData/{Tablename}")
    public String  index1(@PathVariable String Tablename,HttpServletResponse response) throws FileNotFoundException,IOException,FileNotFoundException, IOException  {
    	 String filename = "DatabaseDetails.xls" ;
         HSSFWorkbook workbook = new HSSFWorkbook();
         HSSFSheet sheet = workbook.createSheet("FirstSheet");  


        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
               
        List<String>data=new ArrayList<String>();

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();


            rs1 = st.executeQuery("SELECT * FROM repairs_usm_portal."+Tablename+"");
            ResultSetMetaData metaData = rs1.getMetaData();
            int rowCount = metaData.getColumnCount();
            System.out.println("Table Name : " + metaData.getTableName(1));
            HSSFRow rowhead = sheet.createRow((short)0);
            for (int i = 0; i < rowCount; i++) {
            System.out.println(metaData.getColumnName(i + 1) + " \t");
            data.add(metaData.getColumnName(i + 1));
            rowhead.createCell(i).setCellValue(metaData.getColumnName(i + 1));
            }
            int k=1;
            while(rs1.next())
            {	
            	HSSFRow row = sheet.createRow((short)k);
            	int p=0;
            	for (String s:data) {
            			row.createCell(p).setCellValue(rs1.getObject(s) != null ? rs1.getObject(s).toString() : "");
            			p++;
				}
               	k++;
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            //------------------------download from cloud--------------------

            byte[] excelBytes = null;
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                excelBytes = out.toByteArray();
            } catch (Exception e) {
            	System.out.println("ERROR:::"+e.getMessage());
            }


            try {
            	byte[] partRules=excelBytes;
    			InputStream is = new ByteArrayInputStream(partRules);
    			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
    			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    			IOUtils.copy(is, response.getOutputStream());
    			response.flushBuffer();
    		} catch (IOException e) {
    			System.out.println("Exception while creating excel data :: "+ e.getMessage());
    		}



            fileOut.close();
            System.out.println("Your excel file has been generated!");


        } catch (SQLException ex) {
        	System.out.println("Exception while getting data :: "+ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();

                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
            	System.out.println("Exception while getting data :: "+ex.getMessage());
            }
        }
		return "DATA";
    }
}