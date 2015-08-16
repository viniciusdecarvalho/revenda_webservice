package br.edu.ftlf.ads.revenda.dao;

import java.net.URL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory factory;
	
	public static Session getSession() {
		if (factory == null) {
			Configuration cfg = new Configuration().configure(getHibernateCfgLocation());			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			factory = cfg.buildSessionFactory(serviceRegistry);
		}
		return factory.openSession();
	}
	
	private static URL getHibernateCfgLocation() {
		return HibernateUtil.class.getResource("/hibernate.cfg.xml");
	}
}
