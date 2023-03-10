package me.mypvp.protocolwrapper.game;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import me.mypvp.protocolwrapper.AbstractPacket;
import me.mypvp.protocolwrapper.PacketField;
import org.jetbrains.annotations.NotNull;

public class ClientboundSetBorderSizePacket extends AbstractPacket {

  public static final PacketType TYPE = Server.SET_BORDER_SIZE;

  private final PacketField<Double> sizeField = new PacketField<>(container().getDoubles(), 0);

  public ClientboundSetBorderSizePacket() {
  }

  public ClientboundSetBorderSizePacket(@NotNull Object handle) {
    super(handle);
  }

  public ClientboundSetBorderSizePacket(@NotNull PacketContainer packetContainer) {
    super(packetContainer);
  }

  @Override
  public PacketType type() {
    return TYPE;
  }

  public ClientboundSetBorderSizePacket size(double size) {
    sizeField.write(size);
    return this;
  }

  public double size() {
    return sizeField.read();
  }

}
