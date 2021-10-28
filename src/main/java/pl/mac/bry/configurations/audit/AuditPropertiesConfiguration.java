package pl.mac.bry.configurations.audit;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class AuditPropertiesConfiguration {

	
	public static Properties properties() {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream stream = classLoader.getResourceAsStream("audit.properties");
		try {
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
}
