package ${groupId};

import ${groupId}.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@ComponentScan("${groupId}")
@Configuration
public class TestConfig extends Config {

    @Bean
    @Override
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource("jdbc:h2:mem:testdb");
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        return driverManagerDataSource;
    }
}
