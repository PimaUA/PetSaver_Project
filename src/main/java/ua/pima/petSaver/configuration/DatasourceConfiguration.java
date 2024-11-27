package ua.pima.petSaver.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {
    @Bean(name = "securityDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.security")
    public DataSource securityDataSource() {
        return DataSourceBuilder.create().build();
    }
}
