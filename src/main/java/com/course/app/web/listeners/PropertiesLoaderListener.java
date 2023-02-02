package com.course.app.web.listeners;

import com.course.app.dao.db.ds.factories.DataSourceC3p0Singleton;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class PropertiesLoaderListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		File confDir = new File(System.getenv("catalina_base") + "/conf");
		File prop = new File(confDir + "/application.properties");
		try {
			Properties properties = new Properties();
			properties.load(new FileReader(prop));
			DataSourceC3p0Singleton.setProperties(properties);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Файл с настройками не найден, создайте файл " +
					"application.properties в папке conf");
		} catch (IOException e) {
			throw new RuntimeException("Ошибка чтения файла настроек application.properties", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			DataSourceC3p0Singleton.getInstance().close();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
