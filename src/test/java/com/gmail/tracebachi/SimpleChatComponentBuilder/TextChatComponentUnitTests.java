package com.gmail.tracebachi.SimpleChatComponentBuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
class TextChatComponentUnitTests
{
  @Test
  void generatesForEmptyColoredTextComponent()
  {
    TextChatComponent component = new TextChatComponent("&4", null, null);

    assertEquals(component.toJsonString(), "{\"text\":\"\"}");
  }

  @Test
  void generatesForSingleTextComponent()
  {
    TextChatComponent component = new TextChatComponent("H&4ello", null, null);

    assertEquals(component.toJsonString(), "{\"text\":\"\",\"extra\":[{\"text\":\"H\"},{\"text\":\"ello\",\"color\":\"dark_red\"}]}");
  }

  @Test
  void generatesForTextComponentsWithEvents()
  {
    TextChatComponent component = new TextChatComponent(
      "H&4ello",
      new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "suggest this command"),
      new HoverEvent(HoverEvent.Action.SHOW_TEXT, "John Doe"));

    StringBuilder builder = new StringBuilder();
    component.appendTo(builder);

    assertEquals(builder.toString(),
      "{\"text\":\"\"," +
        "\"extra\":[{\"text\":\"H\"},{\"text\":\"ello\",\"color\":\"dark_red\"}]," +
        "\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"suggest this command\"}," +
        "\"hoverEvent\":{\"action\":\"show_text\",\"value\":[{\"text\":\"John Doe\"}]}}");
  }

  @Test
  void generatesForTextComponentsWithColoredHoverText()
  {
    TextChatComponent component = new TextChatComponent(
      "H&4ello",
      null,
      new HoverEvent(HoverEvent.Action.SHOW_TEXT, "&1John &2Doe&3"));

    StringBuilder builder = new StringBuilder();
    component.appendTo(builder);

    assertEquals(builder.toString(),
      "{\"text\":\"\",\"extra\":[{\"text\":\"H\"},{\"text\":\"ello\",\"color\":\"dark_red\"}]," +
        "\"hoverEvent\":{\"action\":\"show_text\",\"value\":" +
        "[" +
          "{\"text\":\"John \",\"color\":\"dark_blue\"}," +
          "{\"text\":\"Doe\",\"color\":\"dark_green\"}" +
        "]}}");
  }
}
