package text;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.stream.Stream;

public final class PageLoader implements TextSource {
    private final Document result;
    private static final String BASE = "https://eda.ru";

    public PageLoader(String path) throws IOException {
        this.result = Jsoup.connect(BASE + path).timeout(0).get();
    }

    @Override
    public Stream<String> get() {
        return result.stream().map(Element::html);
    }
}
