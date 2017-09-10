package com.gmail.tracebachi.SimpleChatComponentBuilder;

import java.util.List;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
public class TextChatComponent
{
  private final String text;
  private final ClickEvent clickEvent;
  private final HoverEvent hoverEvent;

  public TextChatComponent(
    String text, ClickEvent clickEvent, HoverEvent hoverEvent)
  {
    this.text = text == null ? "" : text;
    this.clickEvent = clickEvent;
    this.hoverEvent = hoverEvent;
  }

  public String getText()
  {
    return text;
  }

  public ClickEvent getClickEvent()
  {
    return clickEvent;
  }

  public HoverEvent getHoverEvent()
  {
    return hoverEvent;
  }

  public String toJsonString()
  {
    StringBuilder builder = new StringBuilder();
    appendTo(builder);
    return builder.toString();
  }

  public void appendTo(StringBuilder builder)
  {
    List<MinecraftTextComponent> mcTextComponents = MinecraftTextComponent.translateFromText(text);

    // If there are no components, the text was either empty or just formatting codes.
    if (mcTextComponents.size() == 0)
    {
      builder.append("{\"text\":\"\"}");
      return;
    }

    if (mcTextComponents.size() == 1)
    {
      // Use the main text field since there is only one component
      mcTextComponents.get(0).appendTo(builder);

      // Remove the last curly brace in case there are click or hover events
      builder.setLength(builder.length() - 1);
    }
    else
    {
      // Use the extra field for each component
      builder.append("{\"text\":\"\",").append("\"extra\":[");

      for (MinecraftTextComponent mcTextComponent : mcTextComponents)
      {
        mcTextComponent.appendTo(builder);
        builder.append(",");
      }

      // Replace the last comma with a square bracket
      builder.setCharAt(builder.length() - 1, ']');
    }

    if (clickEvent != null)
    {
      builder.append(",\"clickEvent\":");
      clickEvent.appendTo(builder);
    }

    if (hoverEvent != null)
    {
      builder.append(",\"hoverEvent\":");
      hoverEvent.appendTo(builder);
    }

    builder.append("}");
  }
}
