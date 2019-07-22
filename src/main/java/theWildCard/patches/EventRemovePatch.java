package theWildCard.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.characters.WildcardCharacter;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "initializeCardPools"
)
//Removes the mod's events if the player is not playing as the Wild Card
public class EventRemovePatch {
    @SpirePrefixPatch
    public static void RemoveEvent(AbstractDungeon dungeon_instance) {
        if (!(AbstractDungeon.player instanceof WildcardCharacter)) {
            dungeon_instance.eventList.remove("VelvetRoom");
        }
    }
}
