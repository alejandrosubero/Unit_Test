package com.pts;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ProjectTrackingSoftwareApplication {

		protected static final Log logger = LogFactory.getLog(ProjectTrackingSoftwareApplication.class);

		public static void main(String[] args) {
		// logger.info("the document  Swagger is in link: ==>  http://localhost:1111/com/swagger-ui.html");
			SpringApplication.run(ProjectTrackingSoftwareApplication.class, args);
		 logger.info("the document  Swagger is in link: ==>  http://localhost:1212/projectTrackingSoftware/swagger-ui.html");
	}
}

