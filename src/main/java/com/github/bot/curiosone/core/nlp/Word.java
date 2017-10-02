package com.github.bot.curiosone.core.nlp;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles a Word.
 * A Word is like a Token, but with a restricted set of meanings.
 */
public class Word {

  /**
   * Stores the textual representation of this Word.
   */
  String text;

  /**
   * Stores the base form of the word.
   */
  String lemma;

  /**
   * Stores all the possible meanings of the word.
   */
  Set<Meaning> means;

  /**
   * Constructs a Word starting from a text, a lemma and a Set of meanings.
   * @param text a textual representation of this Word
   * @param lemma the lemma for this Word
   * @param means Set containing all the possible meanings for this Word
   */
  public Word(String text, String lemma, Set<Meaning> means) {
    this.text = text;
    this.lemma = lemma;
    this.means = means;
  }

  /**
   * Constructs a Word starting from a text, a lemma and a single meaning.
   * @param text textual representation of this Word
   * @param lemma the lemma for this Word
   * @param mean meaning of this Word
   */
  public Word(String text, String lemma, Meaning mean) {
    this.text = text;
    this.lemma = lemma;
    this.means = new HashSet<>();
    means.add(mean);
  }

  /**
   * Returns the textual representation of this Word.
   */
  public String getText() {
    return text;
  }

  /**
   * Returns the lemma of this Word.
   */
  public String getLemma() {
    return lemma;
  }

  /**
   * Returns the Set of the meanings of this Word.
   */
  public Set<Meaning> getMeanings() {
    return means;
  }

  /**
   * Checks whether this Word has the given meaning or not.
   * @param  mean the meaning to be checked
   * @return {@code true} if the given meaning is associated with this Word, {@code false} otherwise
   */
  public boolean itMeans(Meaning mean) {
    return means.contains(mean);
  }

  /**
   * Checks whether this Word is the given Part of Speech Type or not.
   * @param  pos the POS to be checked
   * @return {@code true} if this Word is the given POS, {@code false} otherwise
   */
  public boolean itMeans(POS pos) {
    return means.stream()
        .map(Meaning::getPOS)
        .collect(Collectors.toList())
        .contains(pos);
  }

  /**
   * Checks whether this Word is the given Lexical Type or not.
   * @param lex the LEX to be checked
   * @return {@code true} if this Word is the given LEX, {@code false} otherwise
   */
  public boolean itMeans(LEX lex) {
    return means.stream()
        .map(Meaning::getLEX)
        .collect(Collectors.toList())
        .contains(lex);
  }

  /**
   * Returns a String representation of this Word in the form [text, word, meanings].
   */
  @Override
  public String toString() {
    return "[" + text + " (" + lemma + ")" + ", " + means + "]";
  }

  /**
   * Checks whether this Word equals to the given Object.
   * @param other the other Word to be compared against
   * @return {@code true} if this Word equals the other Word;
   *         {@code false} otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other == null || other.getClass() != this.getClass()) {
      return false;
    }
    Word that = (Word) other;
    return this.text.equals(that.text);
  }

  /**
   * Returns the HashCode of this Word.
   * The HashCode is based on the HashCode of the textual representation of this Word.
   */
  @Override
  public int hashCode() {
    return text.hashCode();
  }
}
