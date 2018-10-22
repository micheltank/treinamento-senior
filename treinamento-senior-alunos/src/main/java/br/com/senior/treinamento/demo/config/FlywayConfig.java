package br.com.senior.treinamento.demo.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlywayConfig.class);
	private List<String> tenants = Arrays.asList("treinamento1", "treinamento2");

	@Bean
	public Boolean tenantsFlyway(DataSource dataSource) {
		LOGGER.info("Migrando schemas");
		tenants.forEach(tenant -> {
			Flyway flyway = new Flyway();
			flyway.setDataSource(dataSource);
			flyway.setSchemas(tenant);
			flyway.migrate();
		});

		return true;
	}

}
