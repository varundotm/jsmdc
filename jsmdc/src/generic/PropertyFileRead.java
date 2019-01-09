package generic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class PropertyFileRead {
	public static String getProperty(String input) throws IOException {
		String propertyFilePath=".//jsmdc.properties";
		BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
		Properties obj = new Properties();
		obj.load(reader);
		String returnelement=obj.getProperty(input);
		return returnelement;
	}
}
