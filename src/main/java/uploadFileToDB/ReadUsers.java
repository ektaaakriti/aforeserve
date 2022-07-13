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

public class ReadUsers {
	public static void Users() {
	connectToDb DB=new connectToDb();
Connection dbcon=DB.connect_db();
Properties properties = DB.getProperties();
 
	 String csvFilePath =properties.getProperty("csvpathUsers");
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

       String sql = "INSERT INTO Users(First_Name, Last_Name, AD_User_login_ID, Password_enc, Mobile, Email_ID, Password, Department, Location, Manager_Name, Manager_User_ID, Emp_Code, username, admin_panel_enable, user_group_id,user_id) VALUES (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?,?,?)";
       PreparedStatement statement = dbcon.prepareStatement(sql);

       beanReader = new CsvBeanReader(new FileReader(csvFilePath), CsvPreference.STANDARD_PREFERENCE);
      
       beanReader.getHeader(true); // skip header line

       String[] header = {"First_Name","Last_Name","AD_User_login_ID","Password_enc","Mobile","Email_ID","Password","Department","Location","Manager_Name","Manager_User_ID","Emp_Code","username","admin_panel_enable","user_group_id","user_id"};
       Users bean = null;

       int count = 0;

       while ((bean = beanReader.read(Users.class, header, processors)) != null) {
       	 String First_Name =bean.getFirst_Name();
            String Last_Name =bean.getLast_Name();	
            String AD_User_login_ID=bean.getAD_User_login_ID();
           String Password_enc=bean.getPassword_enc();
           String Mobile=bean.getMobile();
           String Email_ID=bean.getEmail_ID();
           String Password=bean.getPassword();
           String Department=bean.getDepartment();
           String Location=bean.getLocation();
           String Manager_Name=bean.getManager_Name();
           String Manager_User_ID=bean.getManager_User_ID();
            String Emp_Code=bean.getEmp_Code();
           String username=bean.getUsername();
           String admin_panel_enable=bean.getAdmin_panel_enable();
           String user_group_id=bean.getUser_group_id();
           String user_id=bean.getUser_id();
           

           statement.setString(1,First_Name);
           statement.setString(2,Last_Name);
           statement.setString(3,AD_User_login_ID);
           statement.setString(4,Password_enc);
           statement.setString(5,Mobile);
           statement.setString(6,Email_ID);
           statement.setString(7,Password);
           statement.setString(8,Department);
           statement.setString(9,Location);
           statement.setString(10, Manager_Name);
           statement.setString(11,Manager_User_ID);
           statement.setString(12,Emp_Code);
           statement.setString(13,username);
           statement.setString(14,admin_panel_enable);
           statement.setString(15,user_group_id);
           statement.setString(16, user_id);
          
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
   String path = null;
try {
	path = DB.createcompletefolder();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   DB.moveFile(csvFilePath,1);
}


}


