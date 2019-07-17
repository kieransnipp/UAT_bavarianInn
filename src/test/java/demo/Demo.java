package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

public class Demo {

	static private Logger logger = LogManager.getLogger(Demo.class);
	static private Logger logger2 = LogManager.getLogger(Demo.class);

	public static void main(String[] args) {
		
		Logger logMan = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
		System.out.println("\n Hello world \n");

		System.out.println("logMan.getName().toString()"+logMan.getName());
		logger.info("This is the info");
		logger.error("This is the error msg");
		logger.debug("This is a message");
		logger.warn("This is a warning message");
		logger.fatal("This is a fatal message");
		
		 logger2 = LogManager.getRootLogger();
    	logger2.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile"));
		
		System.out.println("Completed");
	}

}
