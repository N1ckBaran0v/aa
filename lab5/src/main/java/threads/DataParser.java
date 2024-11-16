package threads;

import database.Recipe;
import org.jetbrains.annotations.NotNull;
import tasks.Task;

import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DataParser extends TasksPipeRunnable {
    private final Pattern urlPattern = Pattern.compile("og:url[^>]+");
    private final Pattern titlePattern = Pattern.compile("og:title[^>]+");
    private final Pattern ingredientsPattern =
            Pattern.compile("recipeIngredient\">[^<]+</span></span> <[^>]+><[^<]+<[^<]+<[^<]+");
    private final Pattern stepsPattern = Pattern.compile("(1fhv1xg|qlvyz2)\"> <[^<]+<[^<]+<[^<]+<[^<]+");
    private final Pattern imagePattern = Pattern.compile("og:image[^>]+");

    public DataParser(@NotNull Queue<Task> input, @NotNull Queue<Task> output, @NotNull Path logPath,
                      @NotNull String action) {
        super(input, output, logPath, action);
    }

    @Override
    protected void taskAction(Task task) {
        var data = (String) task.getData();
        var id = task.getId();
        var matcher = urlPattern.matcher(data);
        matcher.find();
        var url = data.substring(matcher.start() + 17, matcher.end() - 1);
        matcher = titlePattern.matcher(data);
        matcher.find();
        var title = data.substring(matcher.start() + 19, matcher.end() - 1);
        if (title.endsWith("Видеорецепт")) {
            title = title.substring(0, title.length() - 12);
        }
        matcher = ingredientsPattern.matcher(data);
        var ingredients = getIngredients(matcher, data);
        matcher = stepsPattern.matcher(data);
        var steps = getSteps(matcher, data);
        matcher = imagePattern.matcher(data);
        matcher.find();
        var imageUrl = data.substring(matcher.start() + 19, matcher.end() - 1);
        var recipe = new Recipe(id, url, title, ingredients, steps, imageUrl);
        task.setData(recipe);
    }

    private List<Map<String, String>> getIngredients(Matcher matcher, String data) {
        var result = new ArrayList<Map<String, String>>();
        while (matcher.find()) {
            var tmp = new LinkedHashMap<String, String>();
            var buffer = data.substring(matcher.start() + 18, matcher.end());
            tmp.put("name", buffer.substring(0, buffer.indexOf('<')));
            var amount = buffer.substring(buffer.lastIndexOf('>') + 1);
            var first = amount.charAt(0);
            if (first >= '0' && first <= '9') {
                var spacePos = amount.indexOf(' ');
                tmp.put("unit", amount.substring(spacePos + 1));
                tmp.put("quantity", amount.substring(0, spacePos));
            } else {
                tmp.put("unit", amount);
                tmp.put("quantity", "");
            }
            result.add(tmp);
        }
        return result;
    }

    private List<String> getSteps(Matcher matcher, String data) {
        var result = new ArrayList<String>();
        while (matcher.find()) {
            var tmp = data.substring(matcher.start(), matcher.end());
            result.add(tmp.substring(tmp.lastIndexOf('>') + 1));
        }
        return result;
    }
}
