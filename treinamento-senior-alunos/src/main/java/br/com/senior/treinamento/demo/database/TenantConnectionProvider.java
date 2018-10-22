package br.com.senior.treinamento.demo.database;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class TenantConnectionProvider
		implements org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider {

	private static final long serialVersionUID = -5699680567062180910L;

	private DataSource datasource;

	public TenantConnectionProvider(DataSource dataSource) {
		this.datasource = dataSource;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return datasource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = getAnyConnection();
		connection.setSchema(tenantIdentifier);
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		connection.setSchema(tenantIdentifier);
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;

	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

}