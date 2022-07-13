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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import uploadFileToDB.Review;
import uploadFileToDB.connectToDb;

import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.google.protobuf.Timestamp;

public class Main {
	
	 public static void main(String[] args) throws IOException {
		 connectToDb DB=new connectToDb();
		 Properties properties = DB.getProperties();
		 ReadUsers us=new ReadUsers();
		 InsertNewAsset ast=new InsertNewAsset();
		 updateexistingAsset as=new updateexistingAsset();
		 UpdateAssetBySerialNumber ass=new  UpdateAssetBySerialNumber();
		 String file="";
		 String users=properties.getProperty("csvpathUsers");
		 String newAsset =properties.getProperty("csvpathNewAsset");
		 String AssetIP=properties.getProperty("csvPathUpdateByIpAddress");
		 String AssetSerial=properties.getProperty("csvPathUpdateBySerialNumber");
		 int i=0;
		 while (true) {
			 i=i++;
			 if(file==users) {
				 us.Users();
				 DB.moveFile(users,i);
				 
			 }
			if(file==newAsset) {
				ast.NewAsset();
				DB.moveFile(newAsset, i);
				
			}
		if(file==AssetIP) {
			as.AssetByIP();
			DB.moveFile(AssetIP, i);
		}
			if(file==AssetSerial) {
				ass.Serial();
				DB.moveFile(AssetSerial, i);
				
			}
		 }
	 }
	 }
	 
	

