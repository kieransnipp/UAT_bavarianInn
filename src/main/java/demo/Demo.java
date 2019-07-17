package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

public class Demo {

	static private Logger logger = LogManager.getLogger(Demo.class);

	public static void main(String[] args) {	
		//Logger logMan = LogManager.getLogger(Demo.class);
		System.out.println("\n Hello world \n");

		logger.trace("This is the trace");
		
		logger.error("This is the error msg");
		logger.debug("This is a message");
		logger.warn("This is a warning message");
		logger.info("This is the info");
		logger.fatal("This is a fatal message");
		
		String className = logger.getClass().toString();
		
		System.out.println("Completed class = " + className);
	}

}
