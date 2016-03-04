package it.cnr.ilc.lc.omega;

import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author oakgen
 */
public class HibernateUtil {

    private static SessionFactory SESSION_FACTORY = null;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        Properties properties = configuration.getProperties();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
    }

    private HibernateUtil() {
    }

    public static Session getSession() {
        return SESSION_FACTORY.openSession();
    }
}
