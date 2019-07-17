package demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Properties;

public class PropertiesFile {

	static Properties prop = new Properties();
	static String projectPath = System.getProperty("user.dir");

	public static void main(String[] args) throws IOException {
		getProperties();
		setProperties();
	}

	public static String getProperties() {
		try {
			InputStream input = new FileInputStream(projectPath + "/src/main/java/com/bav/config/config.properties");
			prop.load(input);
			String browser = prop.getProperty("browser");
			System.out.println("Browser is = " + browser);
			TestNG_Demo.browserName = browser;
			TestNG_Demoa.browserName = browser;
			TestNG_Demoab.browserName = browser;
			

		} catch (Exception e) {
			System.out.println("getMessage =" + e.getMessage());
			System.out.println("getCause = " + e.getCause());

		}
		return projectPath;

	} // End getProperties

	public static void setProperties() throws IOException {
		try {
			OutputStream output = new FileOutputStream(projectPath + "/src/main/java/com/bav/config/config.properties");
			prop.setProperty("browser", "chrome");
			prop.store(output, "A comment");
			
			String browser = prop.getProperty("browser");
			System.out.println("Browser is now updated to "+browser);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} //End setProperties

	
	
		
}
