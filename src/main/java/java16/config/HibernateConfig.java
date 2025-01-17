package java16.config;

import jakarta.persistence.EntityManagerFactory;
import java16.entities.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public final class HibernateConfig {
    public static EntityManagerFactory getEntityManagerFactory() {
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_URL,"jdbc:postgresql://localhost:5432/java16");
        properties.put(Environment.JAKARTA_JDBC_USER,"postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD,"1234");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.FORMAT_SQL, "true");
        properties.put(Environment.USE_SQL_COMMENTS, "true");
        properties.put(Environment.HIGHLIGHT_SQL, "true");


        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Agency.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(House.class);
        configuration.addAnnotatedClass(Owner.class);
        configuration.addAnnotatedClass(Rent_Info.class);
        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
