package pl.mac.bry.configurations.audit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.audit4j.core.MetaData;
import org.audit4j.core.handler.ConsoleAuditHandler;
import org.audit4j.core.handler.Handler;
import org.audit4j.core.handler.file.FileAuditHandler;
import org.audit4j.core.layout.Layout;
import org.audit4j.core.layout.SimpleLayout;
import org.audit4j.handler.db.DatabaseAuditHandler;
import org.audit4j.integration.spring.AuditAspect;
import org.audit4j.integration.spring.SpringAudit4jConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AuditConfiguration {

	private Properties auditProperties = AuditPropertiesConfiguration.properties();
	
	final String embedded = auditProperties.getProperty("DbAudit.Embedded");
	final String username = auditProperties.getProperty("DbAudit.username");
	final String password = auditProperties.getProperty("DbAudit.password");
	final String url = auditProperties.getProperty("DbAudit.url");
	final String dbDriver = auditProperties.getProperty("DbAudit.dbDriver");
	
	

	@Bean
	public Layout layout() {
		return new SimpleLayout();
	}
	
	@Bean
	public MetaData metaData() {
		return new AuditMetaData();
	}
	
	@Bean
	public DatabaseAuditHandler databaseAuditHandler() {
		DatabaseAuditHandler databaseAuditHandler = new DatabaseAuditHandler();
		databaseAuditHandler.setEmbedded(embedded);
		databaseAuditHandler.setDb_user(username);
		databaseAuditHandler.setDb_password(password);
		databaseAuditHandler.setDb_url(url);
		databaseAuditHandler.setDb_driver(dbDriver);
		return databaseAuditHandler;
	}
	
	@Bean
	public FileAuditHandler fileAuditHandler() {
		FileAuditHandler fileAuditHandler = new FileAuditHandler();
		return fileAuditHandler;
	}
	
	@Bean
	public ConsoleAuditHandler consoleAuditHandler() {
		return new ConsoleAuditHandler();
	}
	
	@Bean
    public SpringAudit4jConfig springAudit4jConfig() {
        SpringAudit4jConfig audit4jConfig = new SpringAudit4jConfig();
        Map<String, String> props = new HashMap<>();
        props.put("log.file.location", ".");
        @SuppressWarnings("rawtypes")
		List<Handler> handlers = new ArrayList<>();
        handlers.add(consoleAuditHandler());
        handlers.add(fileAuditHandler());
        handlers.add(databaseAuditHandler());
        audit4jConfig.setHandlers(handlers);
        audit4jConfig.setLayout(layout());
        audit4jConfig.setMetaData(metaData());
        audit4jConfig.setProperties(props);
        return audit4jConfig;

    }

    @Bean
    public AuditAspect auditAspect() {
        return new AuditAspect();
    }
}
