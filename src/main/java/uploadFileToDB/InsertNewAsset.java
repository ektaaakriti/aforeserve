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

public class InsertNewAsset {
	 public static void NewAsset() throws IOException {
			
		 connectToDb DB=new connectToDb();
		 Connection dbcon=DB.connect_db();
		 String path=DB.createcompletefolder();
		 Properties properties = DB.getProperties();
	     
	    	 String csvFilePath =properties.getProperty("csvpathNewAsset");
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
	 
	           
	            String sql = "INSERT INTO asset_master(Scan_Date,System_Make,System_form_Factor,System_Model,System_Serial_Number,Product_Type,System_IP_Address,System_Hostname,System_OS_type,OS_License_details,OS_Version,OS_Key,Total_RAM,RAM_Slots_Available,RAM_Slots_Used,HD_Make,HD_Model,HD_Serial_Number,HD_Capacity,Processor_Details,MBD_Make,MBD_Model,MBD_Serial_Number,Type_of_Chipset,Monitor_Screen_Make,Monitor_Model,Monitor_Serial_Number,Monitor_Screen_Size,Assets_Status,Retired_Date,Software_list_with_version_and_installed_Date,Procured_Date,Procument_ID,Warranty_AMC,Warranty_AMC_Vendor_Name,Warrenty_AMC_From,Warrenty_AMC_To,User_ID,Department_Name,Site_Name,Sub_Department_Name,Aforesight_Agent_ID) VALUES (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?,?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?)";
	            PreparedStatement statement = dbcon.prepareStatement(sql);

	            beanReader = new CsvBeanReader(new FileReader(csvFilePath), CsvPreference.STANDARD_PREFERENCE);
	           
	            beanReader.getHeader(true); // skip header line
	 
	            String[] header = {"Scan_Date","System_Make","System_form_Factor","System_Model","System_Serial_Number","Product_Type",	"System_IP_Address","System_Hostname","System_OS_type","OS_License_details","OS_Version","OS_Key","Total_RAM","RAM_Slots_Available","RAM_Slots_Used","HD_Make","HD_Model","HD_Serial_Number","HD_Capacity","Processor_Details","MBD_Make","MBD_Model","MBD_Serial_Number","Type_of_Chipset","Monitor_Screen_Make","Monitor_Model","Monitor_Serial_Number","Monitor_Screen_Size","Assets_Status","Retired_Date","Software_list_with_version_and_installed_Date","Procured_Date","Procument_ID","Warranty_AMC","Warranty_AMC_Vendor_Name","Warrenty_AMC_From","Warrenty_AMC_To","User_ID","Department_Name","Site_Name","Sub_Department_Name","Aforesight_Agent_ID"};
	            Review bean = null;
	 
	            int count = 0;
	 
	            while ((bean = beanReader.read(Review.class, header, processors)) != null) {
	            	 String Scan_Date=bean.getScan_Date();
	                 String System_Make=bean.getSystem_Make();	
	                 String System_form_Factor=bean.getSystem_form_Factor();
	                String System_Model=bean.getSystem_Model();
	                String System_Serial_Number=bean.getSystem_Serial_Number();
	                String Product_Type=bean.getProduct_Type();
	                String System_IP_Address=bean.getSystem_IP_Address();
	                String System_Hostname=bean.getSystem_Hostname();
	                String System_OS_type=bean.getSystem_OS_type();
	                String OS_License_details=bean.getOS_License_details();
	                String OS_Version=bean.getOS_Version();
	                 String OS_Key=bean.getOS_Key();
	                String Total_RAM=bean.getTotal_RAM();
	                String RAM_Slots_Available=bean.getRAM_Slots_Available();
	                String RAM_Slots_Used=bean.getRAM_Slots_Used();
	                String HD_Make=bean.getHD_Make();
	                String HD_Model=bean.getHD_Model();
	                String HD_Serial_Number=bean.getHD_Serial_Number();
	                String HD_Capacity=bean.getHD_Capacity();
	                String Processor_Details=bean.getProcessor_Details();
	                String MBD_Make=bean.getMBD_Make();
	                String MBD_Model=bean.getMBD_Model();
	                String MBD_Serial_Number=bean.getMBD_Serial_Number();
	                String Type_of_Chipset=bean.getType_of_Chipset();
	                String Monitor_Screen_Make=bean.getMonitor_Screen_Make();;
	                String Monitor_Model=bean.getMonitor_Model();
	                 String Monitor_Serial_Number=bean.getMonitor_Serial_Number();
	                String Monitor_Screen_Size=bean.getMonitor_Screen_Size();
	                String Assets_Status=bean.getAssets_Status();
	                String Retired_Date=bean.getRetired_Date();
	                 String Software_list_with_version_and_installed_Date=bean.getSoftware_list_with_version_and_installed_Date();
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
	 
	                statement.setString(1,Scan_Date);
	                statement.setString(2,System_Make);
	                statement.setString(3,System_form_Factor);
	                statement.setString(4,System_Model);
	                statement.setString(5,System_Serial_Number);
	                statement.setString(6,Product_Type);
	                statement.setString(7,System_IP_Address);
	                statement.setString(8,System_Hostname);
	                statement.setString(9,System_OS_type);
	                statement.setString(10,OS_License_details);
	                statement.setString(11,OS_Version);
	                statement.setString(12,OS_Key);
	                statement.setString(13,Total_RAM);
	                statement.setString(14,RAM_Slots_Available);
	                statement.setString(15,RAM_Slots_Used);
	                statement.setString(16,HD_Make);
	                statement.setString(17,HD_Model);
	                statement.setString(18,HD_Serial_Number);
	                statement.setString(19,HD_Capacity);
	                statement.setString(20,Processor_Details);
	                statement.setString(21,MBD_Make);
	                statement.setString(22,MBD_Model);
	                statement.setString(23,MBD_Serial_Number);
	                statement.setString(24,Type_of_Chipset);
	                statement.setString(25,Monitor_Screen_Make);
	                statement.setString(26,Monitor_Model);
	                statement.setString(27,Monitor_Serial_Number);
	                statement.setString(28,Monitor_Screen_Size);
	                statement.setString(29,Assets_Status);
	                statement.setString(30,Retired_Date);
	                statement.setString(31,Software_list_with_version_and_installed_Date);
	                statement.setString(32,Procured_Date);
	                statement.setString(33,Procument_ID);
	                statement.setString(34,Warranty_AMC);
	                statement.setString(35,Warranty_AMC_Vendor_Name);
	                statement.setString(36,Warrenty_AMC_From);
	                statement.setString(37,Warrenty_AMC_To);
	                statement.setString(38,User_ID);
	                statement.setString(39,Department_Name);
	                statement.setString(40,Site_Name);
	                statement.setString(41,Sub_Department_Name);
	                statement.setString(42,Aforesight_Agent_ID); 
	                
	 
	           
	 
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
