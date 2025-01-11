package com.unitTestGenerator.interfaces;

import com.unitTestGenerator.pojos.Clase;

public interface IApplicationTestProperties {


    default String generateImport(Clase clase) {
        StringBuilder contex = new StringBuilder();
        contex.append("import org.springframework.test.context.ActiveProfiles;").append("\n");
        contex.append("import org.springframework.boot.test.context.SpringBootTest;").append("\n");
        contex.append("import org.springframework.test.context.junit.jupiter.SpringExtension;").append("\n");
        return contex.toString();
    }

    default String contentApplicationTestPropertiesFile(){
        StringBuilder contex = new StringBuilder("# src/test/resources/application-test.properties");
        contex.append("spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1").append("\n");
        contex.append("spring.datasource.driverClassName=org.h2.Driver").append("\n");
        contex.append("spring.datasource.username=sa").append("\n");
        contex.append("spring.datasource.password=").append("\n");
        contex.append("spring.jpa.database-platform=org.hibernate.dialect.H2Dialect").append("\n");
        return contex.toString();
    }


}
