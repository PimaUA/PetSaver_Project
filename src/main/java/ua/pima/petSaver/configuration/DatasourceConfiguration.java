package ua.pima.petSaver.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() {
       return DataSourceBuilder.create().build();
    }

    @Bean(name = "securityDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.security")
    public DataSource getSecurityDataSource() {
        return DataSourceBuilder.create().build();
    }


}
