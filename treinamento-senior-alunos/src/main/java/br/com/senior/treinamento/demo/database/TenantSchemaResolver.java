package br.com.senior.treinamento.demo.database;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {

	public static final String DEFAULT_TENANT = "public";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String t = TenantContext.getTenantSchema();
		if (t == null) {
			return DEFAULT_TENANT;
		}
		return t;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}