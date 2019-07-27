package theWildCard.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theWildCard.WildcardMod;
import theWildCard.cards.Attack.Common.BasicStrike;
import theWildCard.cards.Attack.Common.CallToAdventure;
import theWildCard.cards.Persona.Arsene;
import theWildCard.cards.Skill.Common.BasicDefend;
import theWildCard.relics.VelvetContract;

import java.util.ArrayList;

import static theWildCard.WildcardMod.*;
import static theWildCard.characters.WildcardCharacter.Enums.COLOR_BLUE;


public class WildcardCharacter extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(WildcardMod.class.getName());

    //Enums for character color and class
    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_WILD_CARD;
        @SpireEnum(name = "DEFAULT_BLUE_COLOR") // These two MUST have the same name.
        public static AbstractCard.CardColor COLOR_BLUE;
        @SpireEnum(name = "DEFAULT_BLUE_COLOR") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    //Character stats
    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 60;
    public static final int MAX_HP = 60;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;


    private static final String ID = makeID("WildCardCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;


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


    public WildcardCharacter(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "theWildCardResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpriterAnimation(
                        "theWildCardResources/images/char/defaultCharacter/Spriter/WildCardCharacter.scml"));



        initializeClass(null, // required call to load textures and setup energy/loadout.
                THE_DEFAULT_SHOULDER_1, // campfire pose
                THE_DEFAULT_SHOULDER_2, // another campfire pose
                THE_DEFAULT_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));


        // set location for text bubbles
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);

    }


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

        retVal.add(BasicStrike.ID);
        retVal.add(BasicStrike.ID);
        retVal.add(BasicStrike.ID);
        retVal.add(BasicStrike.ID);
        retVal.add(BasicDefend.ID);
        retVal.add(BasicDefend.ID);
        retVal.add(BasicDefend.ID);
        retVal.add(BasicDefend.ID);
        retVal.add(CallToAdventure.ID);
        retVal.add(Arsene.ID);

        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(VelvetContract.ID);
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

    // How much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher.
    @Override
    public int getAscensionMaxHPLoss() {
        return 3;
    }

    // The card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_BLUE;
    }

    // A color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return WildcardMod.DEFAULT_BLUE;
    }

    // A BitmapFont object that customizes how
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // The class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //The card that should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new Arsene();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    // Returns a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new WildcardCharacter(name, chosenClass);
    }

    // Returns a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return WildcardMod.DEFAULT_BLUE;
    }

    // Returns a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return WildcardMod.DEFAULT_BLUE;
    }

    // Returns an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Returns a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    // The text displayed during the vampire event
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

}
