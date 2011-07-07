package nsdb.hibernate.util;

import nsdb.NSDBSettings;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * http://www.vaannila.com/hibernate/hibernate-example/hibernate-tools-1.html
 * Create the HibernateUtil class. The HibernateUtil  class helps in creating the 
 * SessionFactory from the Hibernate configuration file. 
 * The SessionFactory is threadsafe, so it is not necessary to obtain one for each thread.
 *  Here the static singleton pattern is used to instantiate the SessionFactory. 
 *  The implementation of the HibernateUtil class is shown below.
 */
 
 
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure(NSDBSettings.HIBERNATE_CONFIG_FILE)
                    .buildSessionFactory();
            
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
