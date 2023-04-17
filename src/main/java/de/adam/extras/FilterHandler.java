package de.adam.extras;

import de.adam.main.ZockerWorldCBV1;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FilterHandler {

    public static List<String> getIllegalWords() {
        return (List<String>) ZockerWorldCBV1.getPlugin().getConfig().getStringList("Filter");
    }

    public static List<String> getIllegalWordsInString(String string) {
        final ArrayList<String> caughtWords = new ArrayList<String>();
        string = string.toLowerCase();
        for (String illegalWord : getIllegalWords()) {
            illegalWord = illegalWord.toLowerCase();
            if (string.contains(illegalWord)) {
                caughtWords.add(illegalWord);
            }
        }
        return caughtWords;
    }

    public static List<String> getIllegalWordsInItemName(final ItemStack item) {
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return new ArrayList<String>();
        }
        return getIllegalWordsInString(item.getItemMeta().getDisplayName());
    }

}
