package me.mypvp.protocolwrapper.game;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import me.mypvp.protocolwrapper.AbstractPacket;
import me.mypvp.protocolwrapper.PacketField;
import org.jetbrains.annotations.NotNull;

public class ClientboundClearTitlesPacket extends AbstractPacket {

  public static final PacketType TYPE = Server.CLEAR_TITLES;

  private final PacketField<Boolean> resetTimesField = new PacketField<>(container().getBooleans(), 0);

  public ClientboundClearTitlesPacket() {
  }

  public ClientboundClearTitlesPacket(@NotNull Object handle) {
    super(handle);
  }

  public ClientboundClearTitlesPacket(
      @NotNull PacketContainer packetContainer) {
    super(packetContainer);
  }

  @Override
  public PacketType type() {
    return TYPE;
  }

  public ClientboundClearTitlesPacket resetTimes(boolean state) {
    resetTimesField.write(state);
    return this;
  }

  public boolean resetTimes() {
    return resetTimesField.read();
  }
}
