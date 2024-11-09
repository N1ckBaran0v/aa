package text;

import java.util.stream.Stream;

public interface TextSource {
    Stream<String> get();
}
