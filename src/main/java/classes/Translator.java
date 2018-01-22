package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by it on 20/07/17.
 */
public class Translator {

  private static final int es = 0;
  private static final int br = 1;
  private Map<String, List<String>> dictionary = new HashMap<>();
  private String language;

  public Translator(String language) {
    this.language = language;
  }

  public synchronized void loadLanguages(String key, List<String> list) {
    if (dictionary != null && dictionary.containsKey(key)) {
      return;
    }
    dictionary.put(key, list);
  }

  public List<String> createWordList(String es, String br) {
    List<String> list = new ArrayList<>();
    list.add(es);
    list.add(br);

    return list;
  }

  public String tr(String value) {
    int languageIndex = es;
    if (language.contains("es")) {
      languageIndex = es;
    } else if (language.contains("br")) {
      languageIndex = br;
    }

    return dictionary.get(value).get(languageIndex);
  }

  public String trXpath(String value) {
    String trValue = tr(value);
    return "//*[@text = '" + trValue + "' or @value = '" + trValue + "' or @name = '" + trValue
        + "' or @content-desc='" + value + "']";
  }

  public String getLanguage() {
    return language;
  }
}
