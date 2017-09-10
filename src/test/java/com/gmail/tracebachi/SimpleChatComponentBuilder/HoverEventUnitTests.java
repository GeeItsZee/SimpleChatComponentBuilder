package com.gmail.tracebachi.SimpleChatComponentBuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author GeeItsZee (tracebachi@gmail.com)
 */
class HoverEventUnitTests
{
  @Test
  void generatesJsonForShowEntity()
  {
    HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ENTITY, "sometext");
    StringBuilder builder = new StringBuilder();
    hoverEvent.appendTo(builder);

    String generatedStr = builder.toString();
    assertEquals(generatedStr, "{\"action\":\"show_entity\",\"value\":\"sometext\"}");
  }

  @Test
  void generatesJsonForShowItem()
  {
    HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_ITEM, "sometext");
    StringBuilder builder = new StringBuilder();
    hoverEvent.appendTo(builder);

    String generatedStr = builder.toString();
    assertEquals(generatedStr, "{\"action\":\"show_item\",\"value\":\"sometext\"}");
  }

  @Test
  void generatesJsonForShowText_Simple()
  {
    HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, "sometext");
    StringBuilder builder = new StringBuilder();
    hoverEvent.appendTo(builder);

    String generatedStr = builder.toString();
    assertEquals(generatedStr, "{\"action\":\"show_text\",\"value\":[{\"text\":\"sometext\"}]}");
  }

  @Test
  void generatesJsonForShowText_Complex()
  {
    HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, "&1One\\n&r&2Two&lThree");
    StringBuilder builder = new StringBuilder();
    hoverEvent.appendTo(builder);

    String generatedStr = builder.toString();
    assertEquals(generatedStr,
      "{\"action\":\"show_text\",\"value\":[" +
        "{\"text\":\"One\\n\",\"color\":\"dark_blue\"}," +
        "{\"text\":\"Two\",\"color\":\"dark_green\"}," +
        "{\"text\":\"Three\",\"color\":\"dark_green\",\"bold\":\"true\"}]}");
  }
}
