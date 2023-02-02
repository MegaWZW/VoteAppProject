package com.course.app.dao.db.ds.factories;

import com.course.app.dao.db.ds.DataSourceC3P0;
import com.course.app.dao.db.ds.api.IDataSourceWrapper;

import java.beans.PropertyVetoException;
import java.util.Properties;

public class DataSourceC3p0Singleton {

	private static Properties properties;
	private volatile static IDataSourceWrapper instance;

	private DataSourceC3p0Singleton(){

	}

	public static void setProperties(Properties properties){
		synchronized (DataSourceC3p0Singleton.class){
			if(instance != null) {
				throw new IllegalStateException("Нельзя менять настройки при созданном подключении к базе данных");
			}
			DataSourceC3p0Singleton.properties = properties;
		}
	}

	public static IDataSourceWrapper getInstance() throws PropertyVetoException {
		if(instance == null) {
			synchronized (DataSourceC3p0Singleton.class) {
				if (instance == null) {
					instance = new DataSourceC3P0(properties);
				}
			}
		}
		return instance;
	}
}
