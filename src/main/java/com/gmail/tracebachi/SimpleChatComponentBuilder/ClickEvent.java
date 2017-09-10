package com.gmail.tracebachi.SimpleChatComponentBuilder;

import java.util.Objects;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
public class ClickEvent
{
  public enum Action
  {
    /**
     * Opens a browser URL
     */
    OPEN_URL,

    /**
     * Has the user run the command
     */
    RUN_COMMAND,

    /**
     * Replaces user text with the value
     */
    SUGGEST_COMMAND;

    public static Action valueOrDefault(String str, Action def)
    {
      switch (str.toUpperCase())
      {
        case "OPEN_URL":
          return OPEN_URL;
        case "RUN_COMMAND":
          return RUN_COMMAND;
        case "SUGGEST_COMMAND":
          return SUGGEST_COMMAND;
        default:
          return def;
      }
    }
  }

  private final Action action;
  private final String value;

  public ClickEvent(Action action, String value)
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
    builder.append("\"value\":\"").append(value).append("\"");
    builder.append("}");
  }
}
