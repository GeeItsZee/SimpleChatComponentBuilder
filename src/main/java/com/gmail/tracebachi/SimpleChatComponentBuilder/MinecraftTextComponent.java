package com.gmail.tracebachi.SimpleChatComponentBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
public class MinecraftTextComponent
{
  private boolean obfuscated;
  private boolean bold;
  private boolean strikethrough;
  private boolean underlined;
  private boolean italic;
  private String color;
  private String text;

  public boolean isObfuscated()
  {
    return obfuscated;
  }

  public void setObfuscated(boolean obfuscated)
  {
    this.obfuscated = obfuscated;
  }

  public boolean isBold()
  {
    return bold;
  }

  public void setBold(boolean bold)
  {
    this.bold = bold;
  }

  public boolean isStrikethrough()
  {
    return strikethrough;
  }

  public void setStrikethrough(boolean strikethrough)
  {
    this.strikethrough = strikethrough;
  }

  public boolean isUnderlined()
  {
    return underlined;
  }

  public void setUnderlined(boolean underlined)
  {
    this.underlined = underlined;
  }

  public boolean isItalic()
  {
    return italic;
  }

  public void setItalic(boolean italic)
  {
    this.italic = italic;
  }

  public String getColor()
  {
    return color;
  }

  public void setColor(String color)
  {
    this.color = color;
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public void appendTo(StringBuilder builder)
  {
    builder.append("{\"text\":\"").append(text).append("\"");

    if (color != null)
    {
      builder.append(",\"color\":\"").append(color).append("\"");
    }

    if (obfuscated)
    {
      builder.append(",\"obfuscated\":\"true\"");
    }

    if (bold)
    {
      builder.append(",\"bold\":\"true\"");
    }

    if (strikethrough)
    {
      builder.append(",\"strikethrough\":\"true\"");
    }

    if (underlined)
    {
      builder.append(",\"underline\":\"true\"");
    }

    if (italic)
    {
      builder.append(",\"italic\":\"true\"");
    }

    builder.append("}");
  }

  public static List<MinecraftTextComponent> translateFromText(String text)
  {
    // Treat null text as empty strings
    text = text == null ? "" : text;

    int length = text.length();
    StringBuilder builder = new StringBuilder(length * 2);
    List<MinecraftTextComponent> result = new ArrayList<>(2);

    boolean obfuscated = false;
    boolean bold = false;
    boolean strikethrough = false;
    boolean underline = false;
    boolean italic = false;
    String color = null;

    for (int i = 0; i < length; i++)
    {
      char first = text.charAt(i);
      char second = (i + 1 == length) ? first : text.charAt(i + 1);

      if (first != '&' && first != '\u00A7')
      {
        builder.append(first);
        continue;
      }

      if (first == second || !isChatFormattingCode(second))
      {
        builder.append(first);
        continue;
      }

      // A valid formatting code has been found
      // Skip over the next character
      i++;

      // Save the text in the buffer with the current formatting
      if (builder.length() != 0)
      {
        MinecraftTextComponent mcTextComponent = new MinecraftTextComponent();
        mcTextComponent.setObfuscated(obfuscated);
        mcTextComponent.setBold(bold);
        mcTextComponent.setStrikethrough(strikethrough);
        mcTextComponent.setUnderlined(underline);
        mcTextComponent.setItalic(italic);
        mcTextComponent.setColor(color);
        mcTextComponent.setText(builder.toString());

        result.add(mcTextComponent);

        // Clear the buffer
        builder.setLength(0);
      }

      String newColor = getColorName(second);

      if (newColor != null)
      {
        color = newColor;
      }
      else if (second == 'r')
      {
        obfuscated = false;
        bold = false;
        strikethrough = false;
        underline = false;
        italic = false;
        color = null;
      }
      else if (second == 'k')
      {
        obfuscated = true;
      }
      else if (second == 'l')
      {
        bold = true;
      }
      else if (second == 'm')
      {
        strikethrough = true;
      }
      else  if (second == 'n')
      {
        underline = true;
      }
      else if (second == 'o')
      {
        italic = true;
      }
    }

    // Save the text in the buffer with the current formatting
    if (builder.length() != 0)
    {
      MinecraftTextComponent mcTextComponent = new MinecraftTextComponent();
      mcTextComponent.setObfuscated(obfuscated);
      mcTextComponent.setBold(bold);
      mcTextComponent.setStrikethrough(strikethrough);
      mcTextComponent.setUnderlined(underline);
      mcTextComponent.setItalic(italic);
      mcTextComponent.setColor(color);
      mcTextComponent.setText(builder.toString());

      result.add(mcTextComponent);

      // Clear the buffer
      builder.setLength(0);
    }

    return result;
  }

  private static boolean isChatFormattingCode(char charCode)
  {
    switch (charCode)
    {
      // Color codes
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
      case 'a':
      case 'b':
      case 'c':
      case 'd':
      case 'e':
      case 'f':
        // Non-color codes
      case 'k':
      case 'l':
      case 'm':
      case 'n':
      case 'o':
      case 'r':
        return true;
      default:
        return false;
    }
  }

  private static String getColorName(char charCode)
  {
    switch (charCode)
    {
      case '0':
        return "black";
      case '1':
        return "dark_blue";
      case '2':
        return "dark_green";
      case '3':
        return "dark_aqua";
      case '4':
        return "dark_red";
      case '5':
        return "dark_purple";
      case '6':
        return "gold";
      case '7':
        return "gray";
      case '8':
        return "dark_gray";
      case '9':
        return "blue";
      case 'a':
        return "green";
      case 'b':
        return "aqua";
      case 'c':
        return "red";
      case 'd':
        return "light_purple";
      case 'e':
        return "yellow";
      case 'f':
        return "white";
      default:
        return null;
    }
  }
}
