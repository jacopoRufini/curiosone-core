package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TokenTest {
  @Test
  public void testInstantiation() {
    Token classUnderTest = Token.tokenize("color").get(0);
    Set<Meaning> means = classUnderTest.getMeanings();
    assertTrue(means.size() > 0);
  }

  @Test
  public void testEquals() {
    Token classUnderTest = Token.tokenize("color").get(0);
    Token classUnderTestClone = Token.tokenize("color").get(0);
    assertEquals(classUnderTest, classUnderTestClone);
  }

  @Test
  public void testTokenize() {
    List<Token> tokens;

    tokens = Token.tokenize("The cat is on the table!");
    assertEquals(5, tokens.size());
    assertEquals("the", tokens.get(0).getText());
    assertEquals("cat", tokens.get(1).getText());
    assertEquals("is on", tokens.get(2).getText());
    assertEquals("the", tokens.get(3).getText());
    assertEquals("table", tokens.get(4).getText());

    tokens = Token.tokenize("I love united states of america");
    assertEquals(3, tokens.size());
    assertEquals("i", tokens.get(0).getText());
    assertEquals("love", tokens.get(1).getText());
    assertEquals("united states of america", tokens.get(2).getText());

    tokens = Token.tokenize("I am born in the united states of america");
    assertEquals(5, tokens.size());
    assertEquals("i", tokens.get(0).getText());
    assertEquals("am born", tokens.get(1).getText());
    assertEquals("in", tokens.get(2).getText());
    assertEquals("the", tokens.get(3).getText());
    assertEquals("united states of america", tokens.get(4).getText());

    tokens = Token.tokenize("what is an apple?");
    assertEquals(4, tokens.size());
    assertEquals("what", tokens.get(0).getText());
    assertEquals("is", tokens.get(1).getText());
    assertEquals("an", tokens.get(2).getText());
    assertEquals("apple", tokens.get(3).getText());

    tokens = Token.tokenize("Where is the cat?");
    assertEquals(4, tokens.size());
    assertEquals("where", tokens.get(0).getText());
    assertEquals("is", tokens.get(1).getText());
    assertEquals("the", tokens.get(2).getText());
    assertEquals("cat", tokens.get(3).getText());

    tokens = Token.tokenize("When the cat was created?");
    assertEquals(5, tokens.size());
    assertEquals("when", tokens.get(0).getText());
    assertEquals("the", tokens.get(1).getText());
    assertEquals("cat", tokens.get(2).getText());
    assertEquals("was", tokens.get(3).getText());
    assertEquals("created", tokens.get(4).getText());
  }
}
