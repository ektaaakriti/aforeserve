package uploadFileToDB;

import java.io.BufferedReader;
import org.supercsv.io.CsvBeanReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import uploadFileToDB.Review;
import uploadFileToDB.connectToDb;

import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.google.protobuf.Timestamp;

public class Main {
	
	 public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		 connectToDb DB=new connectToDb();
		 Properties properties = DB.getProperties();
		 ReadUsers us=new ReadUsers();
		 InsertNewAsset ast=new InsertNewAsset();
		 updateexistingAsset as=new updateexistingAsset();
		 UpdateAssetBySerialNumber ass=new  UpdateAssetBySerialNumber();
		 LicenseCSV lc=new LicenseCSV();
		
		 
		 int i=1;
		
		 while (true) {
			 
			 try {
				 ast.NewAsset();
			/* lc.ReadLicense(i);
			 TimeUnit.MINUTES.sleep(1);
			 i++;
			 us.Users(i);*/
			 TimeUnit.MINUTES.sleep(1);
			 
			 }
			 catch(Exception e) {
				 System.out.println("File not found to upload"+e);
				 
			 }	 
	 } 
}}
	 
	

