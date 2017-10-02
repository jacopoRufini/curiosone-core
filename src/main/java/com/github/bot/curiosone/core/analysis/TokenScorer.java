package com.github.bot.curiosone.core.analysis;

import static java.util.Comparator.comparing;

import com.github.bot.curiosone.core.nlp.Meaning;
import com.github.bot.curiosone.core.nlp.POS;
import com.github.bot.curiosone.core.nlp.Token;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Finding Sentiment's score.
 *
 */

public class TokenScorer {
  private static DictionaryLoader dl = DictionaryLoader.getInstance();

  /**
   * Calculate the score of the Token.
   *
   * @param t is the Token add t's sentiment's score to scoreSet.
   */
  public static Double calculateScore(Token t) {
    POS p = t.getMeanings().stream()
        .sorted(comparing(Meaning::getFrequency).reversed())
        .findFirst()
        .get().getPOS();

    return (p == POS.ADJ || p == POS.V || p == POS.ADV || p == POS.N)
        ? dl.getScore(t.getLemma()) : 0.0;
  }

  /**
   * Calculate the score of the input token's list.
   *
   * @param tokenList the token list.
   */
  public static Double calculateScore(List<Token> tokenList) {
    Double s = 0.0;
    for (Token x : tokenList) {
      s += calculateScore(x);
    }
    return s;
  }
}
