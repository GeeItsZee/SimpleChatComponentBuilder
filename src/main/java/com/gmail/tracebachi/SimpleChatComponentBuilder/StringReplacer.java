package com.gmail.tracebachi.SimpleChatComponentBuilder;

import java.util.Objects;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
public class StringReplacer
{
  /**
   * Static helper class
   */
  private StringReplacer()
  {

  }

  public static String replaceAll(String initialStr, String key, String value)
  {
    if (initialStr == null || key == null || value == null)
    {
      return initialStr;
    }

    // Approximate a 50% increase in length from replacements
    StringBuilder builder = new StringBuilder((int)(initialStr.length() * 1.5));

    // Append the initial string
    builder.append(initialStr);

    replaceAll(builder, key, value);

    return builder.toString();
  }

  public static String replaceAll(String initialStr, String... replacements)
  {
    if (initialStr == null)
    {
      return null;
    }

    // Approximate a 50% increase in length from replacements
    StringBuilder builder = new StringBuilder((int)(initialStr.length() * 1.5));

    // Append the initial string
    builder.append(initialStr);

    replaceAll(builder, replacements);

    return builder.toString();
  }

  public static void replaceAll(StringBuilder builder, String key, String value)
  {
    if (builder == null)
    {
      return;
    }

    int startIndex = builder.indexOf(key);
    int keyLen = key.length();
    int valueLen = value.length();

    // While the key is found
    while (startIndex >= 0)
    {
      // Replace the key with value
      builder.replace(startIndex, startIndex + keyLen, value);

      // Update the start index to skip over the replaced value
      startIndex = builder.indexOf(key, startIndex + valueLen);
    }
  }

  public static void replaceAll(StringBuilder builder, String... replacements)
  {
    if (builder == null)
    {
      return;
    }

    if (replacements.length % 2 != 0)
    {
      throw new IllegalArgumentException("Number of replacements must be a multiple of 2");
    }

    for (int i = 0; i < replacements.length; i += 2)
    {
      String key = Objects.requireNonNull(replacements[i], "replacements[" + i + "]");
      String value = Objects.requireNonNull(replacements[i + 1], "replacements[" + (i + 1) + "]");

      replaceAll(builder, key, value);
    }
  }
}
