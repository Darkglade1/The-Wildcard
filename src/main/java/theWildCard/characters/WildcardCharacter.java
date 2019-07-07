package theWildCard.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theWildCard.WildcardMod;
import theWildCard.cards.Arcana.ArcanaArtistry;
import theWildCard.cards.Arcana.ArcaneArts;
import theWildCard.cards.Arcana.ArchaicAssault;
import theWildCard.cards.Arcana.FinalHour;
import theWildCard.cards.Arcana.MagicMettle;
import theWildCard.cards.Arcana.MagicalMight;
import theWildCard.cards.Arcana.SeveringSlash;
import theWildCard.cards.Arcana.StingingStrike;
import theWildCard.cards.Arcana.TheArcanaUnleashed;
import theWildCard.cards.Attack.Common.SacrificialStroke;
import theWildCard.cards.Persona.Amaterasu;
import theWildCard.cards.Persona.Arsene;
import theWildCard.cards.Persona.Caesar;
import theWildCard.cards.Persona.Lucifer;
import theWildCard.cards.Persona.Satanael;
import theWildCard.cards.Persona.Thanatos;
import theWildCard.cards.Skill.Common.Patience;
import theWildCard.cards.Skill.Common.Proficiency;
import theWildCard.cards.Skill.Common.Unburden;
import theWildCard.cards.Skill.Rare.Deicide;
import theWildCard.cards.Skill.Uncommon.Impatience;
import theWildCard.cards.Skill.Uncommon.MaskChange;
import theWildCard.cards.Skill.Uncommon.ShieldOfMany;
import theWildCard.relics.BlankContractRelic;
import theWildCard.relics.DefaultClickableRelic;
import theWildCard.relics.PlaceholderRelic;
import theWildCard.relics.PlaceholderRelic2;
import theWildCard.relics.VelvetContractRelic;

import java.util.ArrayList;

import static theWildCard.WildcardMod.*;
import static theWildCard.characters.WildcardCharacter.Enums.COLOR_BLUE;

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in WildcardMod-character-Strings.json in the resources

public class WildcardCharacter extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(WildcardMod.class.getName());

    // =============== CHARACTER ENUMERATORS =================
    // These are enums for your Characters color (both general color and for the card library) as well as
    // an enum for the name of the player class - IRONCLAD, THE_SILENT, DEFECT, YOUR_CLASS ...
    // These are all necessary for creating a character. If you want to find out where and how exactly they are used
    // in the basegame (for fun and education) Ctrl+click on the PlayerClass, CardColor and/or LibraryType below and go down the
    // Ctrl+click rabbit hole

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_DEFAULT;
        @SpireEnum(name = "DEFAULT_BLUE_COLOR") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor COLOR_BLUE;
        @SpireEnum(name = "DEFAULT_BLUE_COLOR") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    // =============== CHARACTER ENUMERATORS  =================


    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 60;
    public static final int MAX_HP = 60;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 3;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("DefaultCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "theWildCardResources/images/char/defaultCharacter/orb/layer1.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer2.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer3.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer4.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer5.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer6.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer1d.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer2d.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer3d.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer4d.png",
            "theWildCardResources/images/char/defaultCharacter/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    // =============== CHARACTER CLASS START =================

    public WildcardCharacter(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "theWildCardResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpriterAnimation(
                        "theWildCardResources/images/char/defaultCharacter/Spriter/theDefaultAnimation.scml"));


        // =============== TEXTURES, ENERGY, LOADOUT =================  

        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in WildcardMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                THE_DEFAULT_SHOULDER_1, // campfire pose
                THE_DEFAULT_SHOULDER_2, // another campfire pose
                THE_DEFAULT_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================  

        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");
//        retVal.add(SeveringSlash.ID);
//        retVal.add(SeveringSlash.ID);
//        retVal.add(StingingStrike.ID);
//        retVal.add(StingingStrike.ID);
//        retVal.add(StingingStrike.ID);
//        retVal.add(ArcaneArts.ID);
//        retVal.add(ArcaneArts.ID);
//        retVal.add(ArcaneArts.ID);
        //retVal.add(ArcanaArtistry.ID);
        //retVal.add(ArcanaArtistry.ID);
//        retVal.add(MagicMettle.ID);
//        retVal.add(MagicMettle.ID);
//        retVal.add(ArchaicAssault.ID);
        //retVal.add(ArchaicAssault.ID);
        //retVal.add(MagicalMight.ID);
        //retVal.add(MagicalMight.ID);
        //retVal.add(FinalHour.ID);
        retVal.add(FinalHour.ID);
        //retVal.add(PowerOfTheArcana.ID);
        //retVal.add(PowerOfTheArcana.ID);
        //retVal.add(TheArcanaUnleashed.ID);
        retVal.add(TheArcanaUnleashed.ID);
        retVal.add(Deicide.ID);
        //retVal.add(MaskChange.ID);
        retVal.add(ShieldOfMany.ID);
        //retVal.add(Impatience.ID);
        //retVal.add(Unburden.ID);
        //retVal.add(Patience.ID);
        retVal.add(Proficiency.ID);
        //retVal.add(SacrificialStroke.ID);


        //retVal.add(Arsene.ID);
        //retVal.add(Michael.ID);
        //retVal.add(PaleRider.ID);
        //retVal.add(Polydeuces.ID);
        //retVal.add(Sakuya.ID);
        //retVal.add(Scathach.ID);
        //retVal.add(Odin.ID);
        //retVal.add(Loki.ID);
        //retVal.add(Metatron.ID);
        //retVal.add(Alice.ID);
        retVal.add(Amaterasu.ID);
        retVal.add(Caesar.ID);
        retVal.add(Satanael.ID);
        retVal.add(Lucifer.ID);
        retVal.add(Thanatos.ID);
        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(PlaceholderRelic.ID);
        retVal.add(BlankContractRelic.ID);
        //retVal.add(PlaceholderRelic2.ID);
        retVal.add(DefaultClickableRelic.ID);

        UnlockTracker.markRelicAsSeen(PlaceholderRelic.ID);
        UnlockTracker.markRelicAsSeen(PlaceholderRelic2.ID);
        UnlockTracker.markRelicAsSeen(DefaultClickableRelic.ID);

        return retVal;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 3;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_BLUE;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return WildcardMod.DEFAULT_BLUE;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Arsene();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new WildcardCharacter(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return WildcardMod.DEFAULT_BLUE;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return WildcardMod.DEFAULT_BLUE;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

}
