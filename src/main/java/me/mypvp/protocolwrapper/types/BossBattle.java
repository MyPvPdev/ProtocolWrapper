package me.mypvp.protocolwrapper.types;

import com.comphenix.protocol.events.InternalStructure;
import com.comphenix.protocol.reflect.EquivalentConverter;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class BossBattle {

  private static final Class<?> bossBattleServerClass
      = MinecraftReflection.getMinecraftClass("server.level.BossBattleServer");

  private static final EquivalentConverter<BossBattle> converter = new EquivalentConverter<BossBattle>() {

    @Override
    public Object getGeneric(BossBattle bossBattle) {
      try {
        Object handle = bossBattleServerClass.getConstructors()[0].newInstance(null, null, null);
        InternalStructure structure = InternalStructure.getConverter().getSpecific(handle);
        structure.getUUIDs().write(0, bossBattle.uuid);
        structure.getChatComponents().write(0, bossBattle.chatComponent);
        structure.getFloat().write(0, bossBattle.progress);
        structure.getEnumModifier(BarColor.class, 6).write(0, bossBattle.barColor);
        structure.getEnumModifier(BarStyle.class, 7).write(0, bossBattle.barStyle);
        structure.getBooleans().write(1, bossBattle.darkenScreen);
        structure.getBooleans().write(2, bossBattle.playMusic);
        structure.getBooleans().write(3, bossBattle.createWorldFog);
        return structure.getHandle();
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public BossBattle getSpecific(Object handle) {
      InternalStructure structure = InternalStructure.getConverter().getSpecific(handle);
      UUID uuid = structure.getUUIDs().read(0);
      WrappedChatComponent chatComponent = structure.getChatComponents().read(0);
      float progress = structure.getFloat().read(0);
      BarColor barColor = structure.getEnumModifier(BarColor.class, 6).read(0);
      BarStyle barStyle = structure.getEnumModifier(BarStyle.class, 7).read(0);
      boolean darkenScreen = structure.getBooleans().read(1);
      boolean playMusic = structure.getBooleans().read(2);
      boolean createWorldFog = structure.getBooleans().read(3);
      return new BossBattle(uuid, chatComponent, progress, barColor, barStyle, darkenScreen, playMusic,
          createWorldFog);
    }

    @Override
    public Class<BossBattle> getSpecificType() {
      return BossBattle.class;
    }
  };

  private final UUID uuid;
  private final WrappedChatComponent chatComponent;
  private final float progress;
  private final BarColor barColor;
  private final BarStyle barStyle;
  private final boolean darkenScreen;
  private final boolean playMusic;
  private final boolean createWorldFog;

  public BossBattle(UUID uuid, WrappedChatComponent chatComponent, float progress, BarColor barColor,
      BarStyle barStyle) {
    this(uuid, chatComponent, progress, barColor, barStyle, false, false, false);
  }

  public BossBattle(UUID uuid, WrappedChatComponent chatComponent, float progress, BarColor barColor,
      BarStyle barStyle, boolean darkenScreen, boolean playMusic, boolean createWorldFog) {
    this.uuid = uuid;
    this.chatComponent = chatComponent;
    this.progress = progress;
    this.barColor = barColor;
    this.barStyle = barStyle;
    this.darkenScreen = darkenScreen;
    this.playMusic = playMusic;
    this.createWorldFog = createWorldFog;
  }

  public UUID getUUID() {
    return uuid;
  }

  public WrappedChatComponent getChatComponent() {
    return chatComponent;
  }

  public BarColor getBarColor() {
    return barColor;
  }

  public BarStyle getBarStyle() {
    return barStyle;
  }

  public float getProgress() {
    return progress;
  }

  public boolean isCreateWorldFog() {
    return createWorldFog;
  }

  public boolean isDarkenScreen() {
    return darkenScreen;
  }

  public boolean isPlayMusic() {
    return playMusic;
  }

  public enum BarColor {
    PINK,
    BLUE,
    RED,
    GREEN,
    YELLOW,
    PURPLE,
    WHITE
  }

  public enum BarStyle {
    PROGRESS,
    NOTCHED_6,
    NOTCHED_10,
    NOTCHED_12,
    NOTCHED_20
  }

  public static EquivalentConverter<BossBattle> getConverter() {
    return converter;
  }

}
