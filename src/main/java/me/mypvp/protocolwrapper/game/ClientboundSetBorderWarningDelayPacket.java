package me.mypvp.protocolwrapper.game;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import me.mypvp.protocolwrapper.AbstractPacket;
import me.mypvp.protocolwrapper.PacketField;
import org.jetbrains.annotations.NotNull;

public class ClientboundSetBorderWarningDelayPacket extends AbstractPacket {

  public static final PacketType TYPE = Server.SET_BORDER_WARNING_DELAY;

  private final PacketField<Integer> warningTimeField = new PacketField<>(container().getIntegers(), 0);

  public ClientboundSetBorderWarningDelayPacket() {
  }

  public ClientboundSetBorderWarningDelayPacket(@NotNull Object handle) {
    super(handle);
  }

  public ClientboundSetBorderWarningDelayPacket(@NotNull PacketContainer packetContainer) {
    super(packetContainer);
  }

  @Override
  public PacketType type() {
    return TYPE;
  }

  public ClientboundSetBorderWarningDelayPacket warningTime(int time) {
    warningTimeField.write(time);
    return this;
  }

  public int warningTime() {
    return warningTimeField.read();
  }

}
