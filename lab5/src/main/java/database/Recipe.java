package database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "recipes")
public final class Recipe implements Serializable {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "issue_id")
    private long issueId = 9134;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "ingredients")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Map<String, String>> ingredients;

    @Column(name = "steps")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> steps;

    @Column(name = "image_url")
    private String imageUrl;

    @SuppressWarnings("UnusedDeclaration")
    public Recipe() {
    }

    public Recipe(long id, @NotNull String url, @NotNull String title, @NotNull List<Map<String, String>> ingredients,
                  @NotNull List<String> steps, @NotNull String imageUrl) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Map<String, String>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Map<String, String>> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe recipe)) return false;
        return id == recipe.id && issueId == recipe.issueId && Objects.equals(url, recipe.url) &&
                Objects.equals(title, recipe.title) && Objects.equals(ingredients, recipe.ingredients) &&
                Objects.equals(steps, recipe.steps) && Objects.equals(imageUrl, recipe.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueId, url, title, ingredients, steps, imageUrl);
    }
}
