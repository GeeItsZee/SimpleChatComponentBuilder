package com.gmail.tracebachi.SimpleChatComponentBuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
class ClickEventUnitTests
{
  @Test
  void generatesJsonForRunCommand()
  {
    ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/testcommand");
    StringBuilder builder = new StringBuilder();
    clickEvent.appendTo(builder);

    String generatedStr = builder.toString();
    assertEquals(generatedStr, "{\"action\":\"run_command\",\"value\":\"/testcommand\"}");
  }

  @Test
  void generatesJsonForOpenUrl()
  {
    ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "foobar.com");
    StringBuilder builder = new StringBuilder();
    clickEvent.appendTo(builder);

    String generatedStr = builder.toString();
    assertEquals(generatedStr, "{\"action\":\"open_url\",\"value\":\"foobar.com\"}");
  }

  @Test
  void generatesJsonForSuggestCommand()
  {
    ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/testcommand");
    StringBuilder builder = new StringBuilder();
    clickEvent.appendTo(builder);

    String generatedStr = builder.toString();
    assertEquals(generatedStr, "{\"action\":\"suggest_command\",\"value\":\"/testcommand\"}");
  }
}
