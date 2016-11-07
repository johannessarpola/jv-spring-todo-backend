package fi.johannes.conf;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

//@Configuration
//@EnableJpaRepositories(entityManagerFactoryRef = "h2EntityManager")
public class H2Conf {

	//@Bean
	//@ConfigurationProperties(prefix = "datasource.todo.h2")
	public DataSource h2DataSource() {
		return DataSourceBuilder.create().driverClassName("org.h2.Driver").build();
	}

	//@Bean(name = "h2EntityManager")
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(h2DataSource()).packages("fi.johannes.dao").build();
	}
}