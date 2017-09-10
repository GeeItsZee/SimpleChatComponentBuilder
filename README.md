# SimpleChatComponentBuilder
Build Minecraft JSON chat components from legacy strings

## How to Use
```java
class Example {
  void foo()  {
    ClickEvent clickEvent = new ClickEvent(
      ClickEvent.Action.SUGGEST_COMMAND, 
      "/suggest this command")
    HoverEvent hoverEvent = new HoverEvent(
      HoverEvent.Action.SHOW_TEXT, 
      "&cHover &4works! \n With multiple lines!");
    TextChatComponent component = new TextChatComponent(
      "&7Hello &6&lWorld!",
      clickEvent,
      hoverEvent);
    
    // Appends the component to the StringBuilder
    StringBuilder sb = new StringBuilder();
    component.appendTo(sb);
    sb.toString();
    
    // Another way to do the same thing
    component.toJsonString();
  }
}
```

## License (MIT)
```
SimpleChatComponentBuilder
Copyright (C) 2017 Trace Bachi (tracebachi@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
