package uploadFileToDB;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class updateexistingAsset {
	public static void AssetByIP() {
	connectToDb DB=new connectToDb();
	 Connection dbcon=DB.connect_db();

	 Properties properties = DB.getProperties();
    
   	 String csvFilePath =properties.getProperty("csvPathUpdateByIpAddress");
     int batchSize = 20;

       

       ICsvBeanReader beanReader = null;
       CellProcessor[] processors = new CellProcessor[] {
    		   new NotNull(),
               new NotNull(), 
               new NotNull(),
               new NotNull(),
               new NotNull(),
               new NotNull(), 
               new NotNull(),
               new NotNull(),
               new NotNull(),
               new NotNull(), 
               new NotNull(),
               new NotNull(),
               new NotNull(),
               new NotNull(), 
               new NotNull(),
               new NotNull(),
              
    		   
       };
    		 
       

       try {
           long start = System.currentTimeMillis();

           
           dbcon.setAutoCommit(false);

         // String sql="insert into asset_master(System_form_Factor,System_IP_Address,OS_License_details,OS_key,Type_of_Chipset,Procured_Date,Procument_ID,Warranty_AMC,Warranty_AMC_Vendor_Name,Warrenty_AMC_From,Warrenty_AMC_To,User_ID,Department_Name,Site_Name,Sub_Department_Name,Aforesight_Agent_ID)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           String sql = "update asset_master set System_form_Factor=?, OS_License_details=?, OS_key=?, Type_of_Chipset=?, Procured_Date=?, Procument_ID=?, Warranty_AMC=?, Warranty_AMC_Vendor_Name=?,Warrenty_AMC_From=?,Warrenty_AMC_To=?,User_ID=?,Department_Name=?,Site_Name=?,Sub_Department_Name=?,Aforesight_Agent_ID=? where System_IP_Address=?";
           PreparedStatement statement = dbcon.prepareStatement(sql);

           beanReader = new CsvBeanReader(new FileReader(csvFilePath), CsvPreference.STANDARD_PREFERENCE);
          
           beanReader.getHeader(true); // skip header line

           String[] header = {"System_form_Factor","OS_License_details","OS_Key","Type_of_Chipset","Procured_Date","Procument_ID","Warranty_AMC","Warranty_AMC_Vendor_Name","Warrenty_AMC_From","Warrenty_AMC_To","User_ID","Department_Name","Site_Name","Sub_Department_Name","Aforesight_Agent_ID","system_ip_address"};
           Review bean = null;

           int count = 0;

           while ((bean = beanReader.read(Review.class, header, processors)) != null) {
           	
                String System_form_Factor=bean.getSystem_form_Factor();
               
           
               String System_IP_Address=bean.getSystem_IP_Address();
              
               String OS_License_details=bean.getOS_License_details();
               
                String OS_Key=bean.getOS_Key();
               
               String Type_of_Chipset=bean.getType_of_Chipset();
              
               String Procured_Date=bean.getProcured_Date();
               String Procument_ID=bean.getProcument_ID()	;
               String Warranty_AMC=bean.getWarranty_AMC();
               String Warranty_AMC_Vendor_Name=bean.getWarranty_AMC_Vendor_Name();
               String Warrenty_AMC_From=bean.getWarrenty_AMC_From();
                String Warrenty_AMC_To=bean.getWarranty_AMC();
                String User_ID=bean.getUser_ID();	;
               String Department_Name=bean.getDepartment_Name();
                String Site_Name=bean.getSite_Name();
               String Sub_Department_Name=bean.getSub_Department_Name();
                String Aforesight_Agent_ID=bean.getAforesight_Agent_ID();

               
               statement.setString(1,System_form_Factor);
               
              
              
               statement.setString(2,OS_License_details);
            
               statement.setString(3,OS_Key);
               
               statement.setString(4,Type_of_Chipset);
              
               statement.setString(5,Procured_Date);
               statement.setString(6,Procument_ID);
               statement.setString(7,Warranty_AMC);
               statement.setString(8,Warranty_AMC_Vendor_Name);
               statement.setString(9,Warrenty_AMC_From);
               statement.setString(10,Warrenty_AMC_To);
               statement.setString(11,User_ID);
               statement.setString(12,Department_Name);
               statement.setString(13,Site_Name);
               statement.setString(14,Sub_Department_Name);
               statement.setString(15,Aforesight_Agent_ID);
               statement.setString(16,System_IP_Address);
               

          

               statement.addBatch();

               if (count % batchSize == 0) {
                   statement.executeBatch();
                   count++;
               }
               
           }

           beanReader.close();

           // execute the remaining queries
           statement.executeBatch();

           dbcon.commit();
           dbcon.close();

           long end = System.currentTimeMillis();
           System.out.println("Execution Time: " + (end - start));
       } catch (IOException ex) {
           System.err.println(ex);
       } catch (SQLException ex) {
           ex.printStackTrace();

           try {
               dbcon.rollback();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

  // DB.moveFile(csvFilePath,path);
      
}
}




