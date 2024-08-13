package replacer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Strebler {

  public static void main(String[] args) throws IOException {
    var dir = Path.of("/home/alex/Downloads/answers/answers.axonivy.com");
    try (var walker = Files.walk(dir)) {
     walker.forEach(path -> process(path));
    }

   //process(Path.of("/home/alex/Downloads/index.html"));
  }

  private static void process(Path path) {
    var name = path.getFileName().toString();
    if (name.endsWith(".png")) {
      return;
    }
    if (name.endsWith(".css")) {
      return;
    }
    if (name.endsWith(".js")) {
      return;
    }
    if (name.endsWith(".gif")) {
      return;
    }
    if (name.endsWith(".tmp")) {
      return;
    }
    if (name.endsWith(".jpg")) {
      return;
    }
    if (name.endsWith(".iar")) {
      return;
    }
    if (name.endsWith(".jar")) {
      return;
    }
    if (name.endsWith(".delayed")) {
      return;
    }
    if (name.endsWith(".eot")) {
      return;
    }
    if (name.endsWith(".woff2")) {
      return;
    }
    if (name.endsWith(".svg")) {
      return;
    }
    if (name.endsWith(".ttf")) {
      return;
    }
    if (name.endsWith(".swf")) {
      return;
    }
    if (name.endsWith(".woff")) {
      return;
    }
    if (name.endsWith(".bak")) {
      return;
    }
    if (Files.isDirectory(path)) {
      return;
    }

    try {
      System.out.println(path);
      var content = Files.readString(path);

      for (var pattern : patterns) {
        var matcher = pattern.matcher(content);
        //while (matcher.find()) {
        //  content = matcher.replaceFirst("");
        //}

        // StringBuilder to store the modified string
        StringBuilder sb = new StringBuilder();

        // find and replace occurrences of the pattern in the input string
        while (matcher.find()) {
            matcher.appendReplacement(sb, "name=\"q\"");
        }

        // append the remaining part of the input string after the last match
        matcher.appendTail(sb);

        content = sb.toString();
      }

      //Files.writeString(Path.of("/home/alex/Downloads/new.html"), content, StandardCharsets.ISO_8859_1);
      Files.writeString(path, content);

    } catch (IOException ex) {
      throw new RuntimeException("could not process " + path, ex);
    }
  }

  private static Set<Pattern> patterns = new HashSet<Pattern>();

  // first apply this
  // find . -type f -name '*.html' -o -name '*.html' | xargs -Ix sed -i.bak -r 's/\r//g' x
  // find . -type f -name '*.bak' | xargs -Ix rm x
  // https://stackoverflow.com/questions/33450269/how-to-search-and-remove-m-in-all-files-in-a-directory-recursively

  // find . -name '*.html' -o -name '*.html'

  static {
    //patterns.add(Pattern.compile("\t<div id=\"searchBar\">.*</form>\\s\\n\\t</div>", Pattern.DOTALL));
    //patterns.add(Pattern.compile("<a href=\".*signin/index.html\" >login</a>"));
    //patterns.add(Pattern.compile("<a href=\".*signup/index.html\" >signup</a>"));
    //patterns.add(Pattern.compile("<a id=\"nav_ask\" href=\".*ask/index.html\" class=\"special\">Ask a Question</a>"));
    //patterns.add(Pattern.compile("<form id=\"fmanswer\".*</form>", Pattern.DOTALL));
    patterns.add(Pattern.compile("name=\"query\""));
  }
}
