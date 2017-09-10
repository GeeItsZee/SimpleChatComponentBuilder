package com.gmail.tracebachi.SimpleChatComponentBuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
class StringReplacerTest
{
  @Test
  public void handlesNoReplacements()
  {
    assertEquals("Hello World", StringReplacer.replaceAll("Hello World"));
    assertEquals("Hello World", StringReplacer.replaceAll("Hello World", "x", "y"));
    assertEquals("Hello World", StringReplacer.replaceAll("Hello World", "x", "y", "z", "a"));
  }

  @Test
  public void handlesSingleReplacement()
  {
    assertEquals("Hello World!", StringReplacer.replaceAll("Hello World", "orld", "orld!"));
  }

  @Test
  public void handlesMultipleReplacements()
  {
    assertEquals("Ello World!", StringReplacer.replaceAll("Hello World", "orld", "orld!", "Hello", "Ello"));
  }
}
