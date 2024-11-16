package database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseService {
    private final SessionFactory sessionFactory;

    public DatabaseService(String filename) {
        var logger = Logger.getLogger("org.hibernate");
        logger.setLevel(Level.OFF);
        var configuration = getSQLiteConfiguration(filename);
        var builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        var serviceRegistry = builder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getSQLiteConfiguration(String filename) {
        var configuration = new Configuration();
        configuration.addAnnotatedClass(Recipe.class);
        configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
        configuration.setProperty("hibernate.connection.url", String.format("jdbc:sqlite:%s", filename));
        configuration.setProperty("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        return configuration;
    }

    public void addRecipe(Recipe recipe) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            RecipesDAO.save(session, recipe);
            session.getTransaction().commit();
        }
    }

    public Recipe getRecipe(long id) {
        var result = (Recipe) null;
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = RecipesDAO.getRecipe(session, id);
            session.getTransaction().commit();
        }
        return result;
    }
}
