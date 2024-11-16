package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
    }

    @Test
    void getId() {
        recipe.setId(1);
        assertEquals(1, recipe.getId());
    }

    @Test
    void getIssueId() {
        recipe.setIssueId(2);
        assertEquals(2, recipe.getIssueId());
    }

    @Test
    void getUrl() {
        recipe.setUrl("url");
        assertEquals("url", recipe.getUrl());
    }

    @Test
    void getTitle() {
        recipe.setTitle("title");
        assertEquals("title", recipe.getTitle());
    }

    @Test
    void getIngredients() {
        var ingredients = new ArrayList<Map<String, String>>();
        var first = new HashMap<String, String>();
        first.put("name", "first");
        first.put("quantity", "1");
        ingredients.add(first);
        var second = new HashMap<String, String>();
        second.put("name", "second");
        second.put("quantity", "2");
        ingredients.add(second);
        recipe.setIngredients(ingredients);
        assertEquals(ingredients, recipe.getIngredients());
    }

    @Test
    void getSteps() {
        var steps = new ArrayList<String>();
        steps.add("step1");
        steps.add("step2");
        recipe.setSteps(steps);
        assertEquals(steps, recipe.getSteps());
    }

    @Test
    void getImageUrl() {
        recipe.setImageUrl("url");
        assertEquals("url", recipe.getImageUrl());
    }

    @Test
    void equalsNull() {
        assertFalse(recipe.equals(null));
    }

    @Test
    void equalsDifferentClass() {
        assertFalse(recipe.equals(new Object()));
    }

    @Test
    void equalsSelf() {
        assertTrue(recipe.equals(recipe));
    }

    @Test
    void equalsAnotherObject() {
        var anotherRecipe = new Recipe();
        assertTrue(recipe.equals(anotherRecipe));
    }

    @Test
    void hashCodeTest() {
        assertEquals(recipe.hashCode(), recipe.hashCode());
    }
}