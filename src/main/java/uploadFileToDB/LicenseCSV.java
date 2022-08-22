package uploadFileToDB;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class LicenseCSV {
	
	static connectToDb DB=new connectToDb();
	static Connection dbcon=DB.connect_db();
	static Properties properties = DB.getProperties();
	 static String csvFilePath="";
	public static void ReadLicense(int i) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		
		  try {
		 csvFilePath =properties.getProperty("csvPathLicense");
	   int batchSize = 100;
int validity=validity();
	   

	   ICsvBeanReader beanReader = null;
	   CellProcessor[] processors = new CellProcessor[] {
	           new NotNull(),
	           new NotNull(), 
	          
	           
	   
	   };

	 
	       long start = System.currentTimeMillis();

	       
	       dbcon.setAutoCommit(false);

	       String sql = "INSERT INTO license_details(ip,start_date,end_date,status_flag) VALUES (?,?, ?, ?)";
	       PreparedStatement statement = dbcon.prepareStatement(sql);

	       beanReader = new CsvBeanReader(new FileReader(csvFilePath), CsvPreference.STANDARD_PREFERENCE);
	      
	       beanReader.getHeader(true); // skip header line

	       String[] header = {"ip","Start_date"};
	       Users bean = null;

	       int count = 0;
	       int license_number=0;
	       System.out.println("Dummy3");
	       int remain=remaining_license();
	       while ((bean = beanReader.read(Users.class, header, processors)) != null) {
	    	   if(remain>license_number) {
	    		   
	       	String ip=bean.getIp();
	       	String start_date=bean.getStart_date();
	         String Eip=encrypt1(ip);
	         System.out.println("Dummy2");
	         String Estart_date=encrypt1(start_date);
	         System.out.println(start_date);
	         start_date=start_date.replace("/","-");
	         String[] stdt=start_date.split("-",3);
	    	 String year=stdt[2];
	    	 int year1=Integer.parseInt(year);
	    	 System.out.println("Dummy1");
	    	 int endyear=year1+validity;
	    	 String endyr=Integer.toString(endyear);
	    	 String Enddate=stdt[0]+"-"+stdt[1]+"-"+endyr;
	    	 System.out.println(Enddate);
	    	 String enddate=encrypt1(Enddate);
	    	 String status=encrypt1("activated");
	           statement.setString(1,Eip);
	           statement.setString(2,Estart_date);
	           statement.setString(3,enddate);
	           statement.setString(4,status);
	           
	          
	           statement.addBatch();}
	    	   
	    	   else {
	    		   System.out.println("license number extended");
	    	   }
	    	   license_number++;
	           if (count % batchSize == 0) {
	               statement.executeBatch();
	               count++;
	           }
	           
	       }

	       beanReader.close();

	       // execute the remaining queries
	       statement.executeBatch();

	       dbcon.commit();
	      // dbcon.close();

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
	  DB.moveFile(i,csvFilePath);
	
	}
	 public static String encrypt1(String toBeEncrypt) throws NoSuchPaddingException, NoSuchAlgorithmException,InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException
	 {
	 	
	 	final String SECRET_KEY_1 = "1234567890123456";
	     final String SECRET_KEY_2 = "8fdfdb7245c044279078ea1966a096fa";
	  
	     IvParameterSpec ivParameterSpec;
	     SecretKeySpec secretKeySpec;
	     Cipher cipher;
	     ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes("UTF-8"));
	     secretKeySpec = new SecretKeySpec(SECRET_KEY_2.getBytes("UTF-8"), "AES");
	     cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	    
	 cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
	 byte[] encrypted = cipher.doFinal(toBeEncrypt.getBytes());
	 return Base64.encodeBase64String(encrypted);



	 }
	 public static String decrypt(String encrypted) throws InvalidAlgorithmParameterException, InvalidKeyException,
     BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
		 final String SECRET_KEY_1 = "1234567890123456";
		     final String SECRET_KEY_2 = "8fdfdb7245c044279078ea1966a096fa";
		 
		    IvParameterSpec ivParameterSpec;
		    SecretKeySpec secretKeySpec;
		     Cipher cipher;
		   
		    
		        ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes("UTF-8"));
		        secretKeySpec = new SecretKeySpec(SECRET_KEY_2.getBytes("UTF-8"), "AES");
		        cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
 cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
 byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(encrypted));
 return new String(decryptedBytes);
}
	 public static int validity() {
		  String validity="";
		  int divalidity=0;
		 try {
				java.sql.Statement stmt1 = dbcon.createStatement();
				
				ResultSet rs1 = stmt1.executeQuery("select * from license_data ");
				
				while (rs1.next()) {
					
			 validity=rs1.getString("validity");
					
					
			
					}
				rs1.close();	
				String dvalidity=decrypt(validity);
				divalidity=Integer.parseInt(dvalidity);
				System.out.println(divalidity);
			}
		 
			catch (Exception e) {
			System.out.println("path no found");	
			}
			return divalidity;
		
		 
	 }
	 public static int total_license() {
		  String validity="";
		  int divalidity=0;
		 try {
				java.sql.Statement stmt1 = dbcon.createStatement();
				
				ResultSet rs1 = stmt1.executeQuery("select * from license_data ");
				
				while (rs1.next()) {
					
			 validity=rs1.getString("total_license");
					
					
			
					}
				rs1.close();	
				String dvalidity=decrypt(validity);
				divalidity=Integer.parseInt(dvalidity);
				System.out.println(divalidity);
			}
		 
			catch (Exception e) {
			System.out.println("path no found");	
			}
			return divalidity;
		
		 
	 }
	 public static int remaining_license() {
		  String validity="";
		  int used=0;
		  int remaining=0;
		 try {
				java.sql.Statement stmt1 = dbcon.createStatement();
				
				ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(*) as count FROM license_details ");
				
				while (rs1.next()) {
					
			 used=rs1.getInt("COUNT");
					
					
			
					}
				rs1.close();	
			int total=total_license();
			System.out.println(total);
			remaining=total-used;
			System.out.println(remaining);
			}
		 
			catch (Exception e) {
			System.out.println("path no found3");	
			}
			return remaining;
		
		 
	 }
	 
}

