package com.github.bot.curiosone.core.extraction;

import java.util.List;
import java.util.Optional;

import com.github.bot.curiosone.core.nlp.Phrase;
import com.github.bot.curiosone.core.nlp.Token;

/**
 * Precomuted answers to sentences not understood by chatbot.
 */
public class RandomAnswer {
  
  /**
   * Private constructor.
   */
  private RandomAnswer() { };
  
  /** This array contains costants added to the answer when the input is made by a single word. */
  private static String[] costants;
  
  /** This array contains answers to a not-english sentence. */
  private static String[] englishAnswers;
  
  /** This array contains answers made for too much general/not understood sentence. */
  private static String[] generalAnswers;
  
  /**
   * Gives a general answer to a general user input.
   * @param phrase given in input
   * @return general answer
   */
  public static Optional<BrainResponse> getAnswer(Phrase phrase) {
    if (costants == null) {
      costants = new String[] {"?","? I do not understand", ".. that is cool"};
      englishAnswers = new String[] {"I think you should speak english", "PLEASE, speak english!", "Are you a robot too?"};
      generalAnswers = new String[] {"What a nice day to talk to a chatbot!","Can you reformulate your sentence please?","Tell me something interesting please."};
    }
    
    List<Token> tokenList = phrase.getTokens();
    
    if (tokenList.size() == 1) {
      return Optional.of(new BrainResponse(phrase.getText() + costants[randomIndex(costants)],""));
    } else if (tokenList.stream().map(x->x.getLemma()).anyMatch(x -> x == null)) {
      return Optional.of(new BrainResponse(englishAnswers[randomIndex(englishAnswers)],""));
    } 
    return Optional.of(new BrainResponse(generalAnswers[randomIndex(generalAnswers)],""));
  }
  
  /**
   * Returns a random index of the array given in input
   * @param array whose index is needed
   * @return index
   */
  private static int randomIndex(String[] array) {
    return (int)(Math.random() * array.length);
  }
  
  public static void main(String[] args) 
  {
    Phrase p = new Phrase("i bought a car");
    System.out.println(Conversation.getAnswer(p));
    System.out.println(BadWords.check(p));
    System.out.println(RandomAnswer.getAnswer(p));
  }
}