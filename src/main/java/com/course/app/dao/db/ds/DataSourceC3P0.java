package com.course.app.dao.db.ds;

import com.course.app.dao.db.ds.api.IDataSourceWrapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceC3P0 implements IDataSourceWrapper {

	private static final String DRIVER_CLASS_PROPERTY_NAME = "db.class";
	private static final String URL_PROPERTY_NAME = "db.url";
	private static final String USER_PROPERTY_NAME = "db.user";
	private static final String PASSWORD_PROPERTY_NAME = "db.password";


	private ComboPooledDataSource dataSource;

	public DataSourceC3P0(Properties properties) throws PropertyVetoException {
		this.dataSource = new ComboPooledDataSource();
		this.dataSource.setDriverClass( properties.getProperty(DRIVER_CLASS_PROPERTY_NAME));
		this.dataSource.setJdbcUrl(properties.getProperty(URL_PROPERTY_NAME));
		this.dataSource.setUser(properties.getProperty(USER_PROPERTY_NAME));
		this.dataSource.setPassword(properties.getProperty(PASSWORD_PROPERTY_NAME));
	}

	@Override
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	@Override
	public void close() throws Exception{
		this.dataSource.close();
	}
}
