package uploadFileToDB;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import com.opencsv.CSVReader;

//import java.util.logging.Level;




public class connectToDb {
	
	
	String username ="";
	String password ="";
	String dbconfStr="";
	 
	Properties properties = new Properties();
	 
	 
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	connectToDb(){
		loadPropertiesFile();
		this.username=properties.getProperty("dbuser");
		this.password=properties.getProperty("dbpassword");
		this.dbconfStr=properties.getProperty("DB_CONF_STRING");
		
		if (dbcon==null)
		{
				dbcon = connect_db();}
	}
	


	public static  Connection dbcon = null;//connect_db();

	private  void loadPropertiesFile(){
	    InputStream iStream= getClass().getClassLoader().getResourceAsStream("config.properties");
	    		try {
	       if(iStream!=null) {           
	    	   
	     
			properties.load(iStream);
	       } else {throw new FileNotFoundException("Property file Not found");}
	      } catch (IOException e) {
	       // TODO Auto-generated catch block
	       e.printStackTrace();
	      }finally {
	        try {
	          if(iStream != null){
	            iStream.close();
	          }
	        } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
	    }
	 private File getFileFromResource(String fileName) throws URISyntaxException{
		 File targetFile =null;  
		 try {       
	        ClassLoader classLoader = getClass().getClassLoader();
	        InputStream iStream= getClass().getClassLoader().getResourceAsStream(fileName);
	      
	        targetFile = new File(fileName);
	        OutputStream outStream = new FileOutputStream(targetFile);
	        byte[] buffer = new byte[8 * 1024];
	        int bytesRead;
	        while ((bytesRead = iStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	        
	        //f = new File(classLoader.getResource(fileName).getFile());
	        System.out.println("file is"+fileName);
            }catch (Exception ex) {
            	System.out.println("exception from get file is"+ex);
            }
		 
	            return targetFile;
	      //  }

	    }
	 public  Connection connect_db() {

			Connection con = null;
			
			try {
				
			   // System.out.println("username is "+username);
				//System.out.println("password is "+password);
				System.out.println("str is"+dbconfStr);
				con = DriverManager.getConnection(dbconfStr, username, password);
			  // log.info("Database connection estblished");
			  
			} catch (Exception e) {
				//log.fatal("exception is" + e);

			}
			return con;

		}
	
	 public static String createcompletefolder() throws IOException{
	        String currentdir = System.getProperty("user.dir");
	        currentdir = currentdir.replace( "\\", "/" );
	        
	            String completedpath = currentdir+ "/completedCSV";
	            System.out.println("Making directory at " + completedpath);
	            File dir = new File(completedpath);//The name of the directory to create                                                                                      
	            dir.mkdir();//Creates the directory                                                                                                               
	    
	        return completedpath;

	} 
	 static void moveFile( int i,String src) {
		 Path temp = null;
		try {
			 String currentdir = System.getProperty("user.dir");
		        currentdir ="C:\\Agent\\";
		        currentdir = currentdir.replace( "\\", "/" );
		        
				
				String path="C:\\Agent\\License\\"+i+".csv";
				//String src="C:\\Agent\\License\\LicenseCSV.csv";
				System.out.println(path);
				System.out.println(src);
			//temp = Files.move
			          //  (Paths.get(src),
			           // Paths.get(currentdir+"/completedCSV/"+i+".csv"));
				 temp = Files.move
				        (Paths.get(src),
				        Paths.get(path));
				 
			
			
				
			        
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("no file to be moved"+e);
		}
	         
	                if(temp != null)
	                {
	                    System.out.println("File renamed and moved successfully");
	                }
	                else
	                {
	                    System.out.println("Failed to move the file");
	                }
	 }
	
     }
	 
	  
	     

	 



