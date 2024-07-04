package de.darfnichtmehr.listener;

import de.darfnichtmehr.QuickCoordsAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;

public class ChatPlaceholderListener {

  private final QuickCoordsAddon ADDON;

  public ChatPlaceholderListener(QuickCoordsAddon addon) {
    this.ADDON = addon;
  }

  @Subscribe
  public void onChatSend(ChatMessageSendEvent event) {
    if (!ADDON.configuration().enableChatPlaceholder().get()
        || ADDON.configuration().chatPlaceholder().get().isEmpty()
        || !event.getMessage().contains(ADDON.configuration().chatPlaceholder().get())
    ) 
      return;
    

    String message = event.getMessage()
        .replace(ADDON.configuration().chatPlaceholder().get(), ADDON.getFormattedCoordinates());
    event.changeMessage(message);
  }
}
