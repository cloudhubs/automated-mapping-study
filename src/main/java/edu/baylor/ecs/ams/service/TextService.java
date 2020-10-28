package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.Keyword;
import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextService {

  private RakeAlgorithm rakeAlg;

  public TextService() throws IOException {
    // Make the rake!
    // Create an object to hold algorithm parameters
    String[] stopWords = new SmartWords().getSmartWords();
    String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
    int minWordChar = 1;
    boolean shouldStem = true;
    String phraseDelims = "[-,.?():;\"!/]";
    RakeParams params = new RakeParams(stopWords, stopPOS, minWordChar, shouldStem, phraseDelims);

    // Create a RakeAlgorithm object
    String POStaggerURL = "model-bin/en-pos-maxent.bin"; // The path to your POS tagging model
    String SentDetecURL = "model-bin/en-sent.bin"; // The path to your sentence detection model
    rakeAlg = new RakeAlgorithm(params, POStaggerURL, SentDetecURL);
  }

  public List<Keyword> extractKeywords(String text) {
    List<Keyword> keywords = new ArrayList<>();
    if (text == null || text.isBlank()) {
      return keywords;
    }
    Result result = rakeAlg.rake(text);
    // Print the result
    System.out.println(result.distinct());
    for (int i = 0; i < result.distinct().getFullKeywords().length; i++) {
      if (result.distinct().getScores()[i] > 1.5) {
        String word = result.distinct().getFullKeywords()[i];
        keywords.add(new Keyword(word.substring(0, Math.min(word.length(), 255)).toLowerCase()));
      }
    }
    return keywords;
//    return Arrays
//            .stream(result.distinct().getFullKeywords())
//            .map(s -> new Keyword(s.substring(0, Math.min(s.length(), 255)).toLowerCase())) // truncate keywords and lowercase them
//            .collect(Collectors.toList());
  }
}
