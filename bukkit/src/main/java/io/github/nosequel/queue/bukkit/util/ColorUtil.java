package io.github.nosequel.queue.bukkit.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ColorUtil {

    public String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
