package com.gmail.tracebachi.SimpleChatComponentBuilder;

import java.util.List;
import java.util.Objects;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
public class HoverEvent
{
  public enum Action
  {
    /**
     * Shows text to the user
     */
    SHOW_TEXT,

    /**
     * Shows an item to the user when the value is valid NBT
     */
    SHOW_ITEM,

    /**
     * Shows entity information
     */
    SHOW_ENTITY;

    public static Action valueOrDefault(String str, Action def)
    {
      switch (str.toUpperCase())
      {
        case "SHOW_TEXT":
          return SHOW_TEXT;
        case "SHOW_ITEM":
          return SHOW_ITEM;
        case "SHOW_ENTITY":
          return SHOW_ENTITY;
        default:
          return def;
      }
    }
  }

  private final Action action;
  private final String value;

  public HoverEvent(Action action, String value)
  {
    this.action = Objects.requireNonNull(action, "action");
    this.value = Objects.requireNonNull(value, "value");
  }

  public Action getAction()
  {
    return action;
  }

  public String getValue()
  {
    return value;
  }

  public void appendTo(StringBuilder builder)
  {
    String actionName = action.name().toLowerCase();

    builder.append("{");
    builder.append("\"action\":\"").append(actionName).append("\",");
    builder.append("\"value\":");

    if (action == Action.SHOW_ENTITY || action == Action.SHOW_ITEM)
    {
      builder.append("\"").append(value).append("\"}");
      return;
    }

    List<MinecraftTextComponent> mcTextComponents = MinecraftTextComponent.translateFromText(value);

    if (mcTextComponents.size() == 0)
    {
      builder.append("\"\"}");
      return;
    }

    builder.append("[");

    for (MinecraftTextComponent mcTextComponent : mcTextComponents)
    {
      mcTextComponent.appendTo(builder);
      builder.append(",");
    }

    // Replace the last comma with a square bracket
    builder.setCharAt(builder.length() - 1, ']');

    builder.append("}");
  }
}
