package com.github.bot.curiosone.core.nlp;

// SUPPRESS CHECKSTYLE AvoidStarImport
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SentenceTest {
  @Test
  public void testInstantiation() {
    List<Phrase> phrases = Phrase.extract("What is an apple?");
    System.out.println(phrases);

    assertTrue(phrases.size() == 1);
    Phrase phrase = phrases.get(0);

    List<Sentence> sentences = Sentence.extract(phrase);
    System.out.println(sentences);

    assertTrue(sentences.size() > 0);
    Sentence sentence = sentences.get(0);

    assertTrue(sentence.respect(new POS[] {POS.PRON, POS.VP}));
    assertTrue(sentence.respect(new POS[] {POS.PRON, POS.V, POS.DET, POS.N}));
    assertFalse(sentence.respect(new POS[] {POS.PRON, POS.VP, POS.N}));
    assertFalse(sentence.respect(new POS[] {POS.PRON, POS.N}));

    List<String>[] parameters = sentence.get(new POS[] {POS.PRON, POS.V, POS.NP});
    assertTrue(parameters.length == 3);
    System.out.println(parameters[0]);
    System.out.println(parameters[1]);
    System.out.println(parameters[2]);
  }

  @Test
  public void testEquals() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple?"));
    List<Sentence> ls2 = Sentence.extract(new Phrase("What is an apple?"));
    assertEquals(ls.get(0), ls.get(0));
    assertEquals(ls.get(0), ls2.get(0));
    assertNotEquals(ls.get(0),null);
    assertNotEquals(ls.get(0), "Bla bla bla");
    assertNotEquals(ls.get(0), new StringBuffer("bla bla bla"));
  }

  @Test
  public void testHashCode() {
    List<Sentence> ls = Sentence.extract(new Phrase("What is an apple?"));
    List<Sentence> ls2 = Sentence.extract(new Phrase("What is an apple?"));
    assertTrue(Integer.class.isInstance(ls.get(0).hashCode()));
    assertEquals(ls.get(0).hashCode(), ls2.get(0).hashCode());
  }

  @Test
  public void testGetWord() {
    List<String> lw = Sentence.extract(
        new Phrase("What is an apple?")).get(0).getWords();
    assertEquals("what", lw.get(0));
    assertEquals("is", lw.get(1));
    assertEquals("an", lw.get(2));
    assertEquals("apple", lw.get(3));
  }
}