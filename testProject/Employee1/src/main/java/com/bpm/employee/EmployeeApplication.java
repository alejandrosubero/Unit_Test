package com.bpm.employee;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class EmployeeApplication {

		protected static final Log logger = LogFactory.getLog(EmployeeApplication.class);
		public static void main(String[] args) {

		logger.info("the document  Swagger is in link: ==>  http://localhost:1112/wfe/swagger-ui.html");
			SpringApplication.run(EmployeeApplication.class, args);
		logger.info("the document  Swagger is in link: ==>  http://localhost:1112/wfe/swagger-ui.html");
	}
}

