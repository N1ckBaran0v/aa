package database;

import org.hibernate.Session;

public final class RecipesDAO {
    public static void save(Session session, Recipe recipe) {
        session.persist(recipe);
    }

    public static Recipe getRecipe(Session session, long id) {
        return (Recipe) session.get(Recipe.class, id);
    }
}
