package theWildCard;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.SetUnlocksSubscriber;
import basemod.interfaces.StartGameSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theWildCard.cards.Arcana.ArcanaArtistry;
import theWildCard.cards.Arcana.ArcaneArts;
import theWildCard.cards.Arcana.ArchaicAssault;
import theWildCard.cards.Arcana.FinalHour;
import theWildCard.cards.Arcana.MagicMettle;
import theWildCard.cards.Arcana.MagicalMight;
import theWildCard.cards.Arcana.PowerOfTheArcana;
import theWildCard.cards.Arcana.SeveringSlash;
import theWildCard.cards.Arcana.StingingStrike;
import theWildCard.cards.Arcana.TheArcanaUnleashed;
import theWildCard.cards.Attack.Common.BasicStrike;
import theWildCard.cards.Attack.Common.BladeOfGenerations;
import theWildCard.cards.Attack.Common.CallToAdventure;
import theWildCard.cards.Attack.Common.EnergizedStroke;
import theWildCard.cards.Attack.Common.SacrificialStroke;
import theWildCard.cards.Attack.Common.StalwartBlade;
import theWildCard.cards.Attack.Rare.AllOutAttack;
import theWildCard.cards.Attack.Rare.Desolation;
import theWildCard.cards.Attack.Rare.Overwhelm;
import theWildCard.cards.Attack.Rare.Ruination;
import theWildCard.cards.Attack.Uncommon.CryOfAgony;
import theWildCard.cards.Attack.Uncommon.EnergyTheft;
import theWildCard.cards.Attack.Uncommon.IdentityTheft;
import theWildCard.cards.Attack.Uncommon.TheGreatSeal;
import theWildCard.cards.Skill.Rare.ChangingDestiny;
import theWildCard.cards.Skill.Uncommon.LadyLuck;
import theWildCard.cards.Attack.Uncommon.Providence;
import theWildCard.cards.Attack.Uncommon.Salvation;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.cards.Persona.Alice;
import theWildCard.cards.Persona.Amaterasu;
import theWildCard.cards.Persona.Arsene;
import theWildCard.cards.Persona.Caesar;
import theWildCard.cards.Persona.Loki;
import theWildCard.cards.Persona.Lucifer;
import theWildCard.cards.Persona.Metatron;
import theWildCard.cards.Persona.Michael;
import theWildCard.cards.Persona.Odin;
import theWildCard.cards.Persona.PaleRider;
import theWildCard.cards.Persona.Polydeuces;
import theWildCard.cards.Persona.Sakuya;
import theWildCard.cards.Persona.Satanael;
import theWildCard.cards.Persona.Scathach;
import theWildCard.cards.Persona.Thanatos;
import theWildCard.cards.Power.Rare.FuelForTheFire;
import theWildCard.cards.Power.Rare.TheUniverse;
import theWildCard.cards.Power.Uncommon.Abstinence;
import theWildCard.cards.Power.Uncommon.Attunement;
import theWildCard.cards.Power.Uncommon.ManOfManyFaces;
import theWildCard.cards.Power.Uncommon.Safeguard;
import theWildCard.cards.Skill.Common.BasicDefend;
import theWildCard.cards.Skill.Common.Patience;
import theWildCard.cards.Skill.Common.Proficiency;
import theWildCard.cards.Skill.Common.Unburden;
import theWildCard.cards.Skill.Rare.Deicide;
import theWildCard.cards.Skill.Uncommon.Impatience;
import theWildCard.cards.Skill.Common.MaskChange;
import theWildCard.cards.Skill.Uncommon.MasterPlan;
import theWildCard.cards.Skill.Uncommon.ShieldOfMany;
import theWildCard.cards.Skill.Uncommon.UnderLockAndKey;
import theWildCard.cards.Skill.Uncommon.UnendingRitual;
import theWildCard.characters.WildcardCharacter;
import theWildCard.events.VelvetRoom;
import theWildCard.relics.Evoker;
import theWildCard.relics.FigureOfDeath;
import theWildCard.relics.MemoriesOfPhantasm;
import theWildCard.relics.VelvetContract;
import theWildCard.relics.Wildcard;
import theWildCard.util.IDCheckDontTouchPls;
import theWildCard.util.TextureLoader;
import theWildCard.variables.ArcanaEnums;
import theWildCard.variables.DefaultCustomVariable;
import theWildCard.variables.DefaultSecondMagicNumber;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class WildcardMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber,
        StartGameSubscriber,
        SetUnlocksSubscriber {

    public static final Logger logger = LogManager.getLogger(WildcardMod.class.getName());
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties wildCardDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enableAltCardArt";
    public static boolean enableAltCardArt = false; // The boolean we'll be setting on/off (true/false)

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "The Wild Card";
    private static final String AUTHOR = "Darkglade";
    private static final String DESCRIPTION = "A character mod inspired by the Persona Series.";

    
    // Colors (RGB)
    // Character Color
    public static final Color DEFAULT_BLUE = CardHelper.getColor(11, 11, 96);
  
    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_BLUE = "theWildCardResources/images/512/bg_attack_blue.png";
    private static final String SKILL_BLUE = "theWildCardResources/images/512/bg_skill_blue.png";
    private static final String POWER_BLUE = "theWildCardResources/images/512/bg_power_blue.png";
    
    private static final String ENERGY_ORB_BLUE = "theWildCardResources/images/512/card_orb.png";
    private static final String CARD_ENERGY_ORB = "theWildCardResources/images/512/card_small_orb.png";
    
    private static final String ATTACK_BLUE_PORTRAIT = "theWildCardResources/images/1024/bg_attack_blue.png";
    private static final String SKILL_BLUE_PORTRAIT = "theWildCardResources/images/1024/bg_skill_blue.png";
    private static final String POWER_BLUE_PORTRAIT = "theWildCardResources/images/1024/bg_power_blue.png";
    private static final String ENERGY_ORB_BLUE_PORTRAIT = "theWildCardResources/images/1024/card_orb.png";
    
    // Character assets
    private static final String THE_DEFAULT_BUTTON = "theWildCardResources/images/charSelect/WildCardCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "theWildCardResources/images/charSelect/WildCardBackground.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "theWildCardResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "theWildCardResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "theWildCardResources/images/char/defaultCharacter/corpse.png";
    
    //Mod Badge - A small icon that appears in the mod settings menu next to the mod.
    public static final String BADGE_IMAGE = "theWildCardResources/images/Badge.png";
    

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    
    public WildcardMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);

        setModID("theWildCard");
        
        logger.info("Done subscribing");
        
        logger.info("Creating the color " + WildcardCharacter.Enums.COLOR_BLUE.toString());
        
        BaseMod.addColor(WildcardCharacter.Enums.COLOR_BLUE, DEFAULT_BLUE, DEFAULT_BLUE, DEFAULT_BLUE,
                DEFAULT_BLUE, DEFAULT_BLUE, DEFAULT_BLUE, DEFAULT_BLUE,
                ATTACK_BLUE, SKILL_BLUE, POWER_BLUE, ENERGY_ORB_BLUE,
                ATTACK_BLUE_PORTRAIT, SKILL_BLUE_PORTRAIT, POWER_BLUE_PORTRAIT,
                ENERGY_ORB_BLUE_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");
        loadConfigData();
    }

    
    public static void setModID(String ID) {
        Gson coolG = new Gson();
        InputStream in = WildcardMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }
    
    public static String getModID() {
        return modID;
    }
    
    private static void pathCheck() {
        Gson coolG = new Gson();
        InputStream in = WildcardMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = WildcardMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }
    
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Mod. =========================");
        WildcardMod defaultmod = new WildcardMod();
        logger.info("========================= /Mod Initialized/ =========================");
    }
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + WildcardCharacter.Enums.THE_WILD_CARD.toString());
        
        BaseMod.addCharacter(new WildcardCharacter("the Default", WildcardCharacter.Enums.THE_WILD_CARD),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, WildcardCharacter.Enums.THE_WILD_CARD);

        logger.info("Added " + WildcardCharacter.Enums.THE_WILD_CARD.toString());
    }

    public static void loadConfigData() {
        try {
            SpireConfig config = new SpireConfig("thewildcard", "wildCardConfig", wildCardDefaultSettings);
            config.load();
            enableAltCardArt = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        }
        catch(Exception e) {
            e.printStackTrace();
            saveData();
        }
    }


    public static void saveData() {
        try {
            SpireConfig config = new SpireConfig("thewildcard", "wildCardConfig", wildCardDefaultSettings);
            config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enableAltCardArt);

            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        // Create the on/off button:
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("Use alternate card background art.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                enableAltCardArt, // Boolean it uses
                settingsPanel, // The mod panel in which this button will be in
                (label) -> {}, // thing??????? idk
                (button) -> { // The actual button:

                    enableAltCardArt = button.enabled; // The boolean true/false will be whether the button is enabled or not
                    saveData();
                });

        settingsPanel.addUIElement(enableNormalsButton); // Add the button to the settings panel. Button is a go.


        //Add events
        BaseMod.addEvent(VelvetRoom.ID, VelvetRoom.class, Exordium.ID);

        logger.info("Done loading badge Image and mod options");
    }

    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        
        //Adds character specific relics
        BaseMod.addRelicToCustomPool(new VelvetContract(), WildcardCharacter.Enums.COLOR_BLUE);
        BaseMod.addRelicToCustomPool(new MemoriesOfPhantasm(), WildcardCharacter.Enums.COLOR_BLUE);
        BaseMod.addRelicToCustomPool(new Evoker(), WildcardCharacter.Enums.COLOR_BLUE);
        BaseMod.addRelicToCustomPool(new Wildcard(), WildcardCharacter.Enums.COLOR_BLUE);
        BaseMod.addRelicToCustomPool(new FigureOfDeath(), WildcardCharacter.Enums.COLOR_BLUE);

        logger.info("Done adding relics!");
    }
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        pathCheck();
        logger.info("Add variables");
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        
        logger.info("Adding cards");
        // Add the cards
        BaseMod.addCard(new SeveringSlash());
        BaseMod.addCard(new StingingStrike());
        BaseMod.addCard(new ArcaneArts());
        BaseMod.addCard(new ArcanaArtistry());
        BaseMod.addCard(new MagicMettle());
        BaseMod.addCard(new ArchaicAssault());
        BaseMod.addCard(new MagicalMight());
        BaseMod.addCard(new FinalHour());
        BaseMod.addCard(new PowerOfTheArcana());
        BaseMod.addCard(new TheArcanaUnleashed());
        BaseMod.addCard(new Arsene());
        BaseMod.addCard(new Michael());
        BaseMod.addCard(new PaleRider());
        BaseMod.addCard(new Polydeuces());
        BaseMod.addCard(new Sakuya());
        BaseMod.addCard(new Scathach());
        BaseMod.addCard(new Odin());
        BaseMod.addCard(new Loki());
        BaseMod.addCard(new Metatron());
        BaseMod.addCard(new Alice());
        BaseMod.addCard(new Amaterasu());
        BaseMod.addCard(new Caesar());
        BaseMod.addCard(new Satanael());
        BaseMod.addCard(new Lucifer());
        BaseMod.addCard(new Thanatos());
        BaseMod.addCard(new TheUniverse());
        BaseMod.addCard(new FuelForTheFire());
        BaseMod.addCard(new ManOfManyFaces());
        BaseMod.addCard(new Safeguard());
        BaseMod.addCard(new Attunement());
        BaseMod.addCard(new Abstinence());
        BaseMod.addCard(new Deicide());
        BaseMod.addCard(new ChangingDestiny());
        BaseMod.addCard(new MasterPlan());
        BaseMod.addCard(new LadyLuck());
        BaseMod.addCard(new UnderLockAndKey());
        BaseMod.addCard(new MaskChange());
        BaseMod.addCard(new UnendingRitual());
        BaseMod.addCard(new ShieldOfMany());
        BaseMod.addCard(new Impatience());
        BaseMod.addCard(new Unburden());
        BaseMod.addCard(new Patience());
        BaseMod.addCard(new Proficiency());
        BaseMod.addCard(new AllOutAttack());
        BaseMod.addCard(new Overwhelm());
        BaseMod.addCard(new Desolation());
        BaseMod.addCard(new Ruination());
        BaseMod.addCard(new EnergyTheft());
        BaseMod.addCard(new IdentityTheft());
        BaseMod.addCard(new TheGreatSeal());
        BaseMod.addCard(new Salvation());
        BaseMod.addCard(new Providence());
        BaseMod.addCard(new CryOfAgony());
        BaseMod.addCard(new BladeOfGenerations());
        BaseMod.addCard(new SacrificialStroke());
        BaseMod.addCard(new EnergizedStroke());
        BaseMod.addCard(new StalwartBlade());
        BaseMod.addCard(new CallToAdventure());
        BaseMod.addCard(new BasicStrike());
        BaseMod.addCard(new BasicDefend());
        
        logger.info("Making sure the cards are unlocked.");
        // Unlock the cards
        UnlockTracker.unlockCard(SeveringSlash.ID);
        UnlockTracker.unlockCard(StingingStrike.ID);
        UnlockTracker.unlockCard(ArcaneArts.ID);
        UnlockTracker.unlockCard(ArcanaArtistry.ID);
        UnlockTracker.unlockCard(MagicMettle.ID);
        UnlockTracker.unlockCard(ArchaicAssault.ID);
        UnlockTracker.unlockCard(MagicalMight.ID);
        UnlockTracker.unlockCard(FinalHour.ID);
        UnlockTracker.unlockCard(PowerOfTheArcana.ID);
        UnlockTracker.unlockCard(TheArcanaUnleashed.ID);
        UnlockTracker.unlockCard(Arsene.ID);
        UnlockTracker.unlockCard(Michael.ID);
        UnlockTracker.unlockCard(PaleRider.ID);
        UnlockTracker.unlockCard(Polydeuces.ID);
        UnlockTracker.unlockCard(Sakuya.ID);
        UnlockTracker.unlockCard(Scathach.ID);
        UnlockTracker.unlockCard(Odin.ID);
        UnlockTracker.unlockCard(Loki.ID);
        UnlockTracker.unlockCard(Metatron.ID);
        UnlockTracker.unlockCard(Alice.ID);
        UnlockTracker.unlockCard(Amaterasu.ID);
        UnlockTracker.unlockCard(Caesar.ID);
        UnlockTracker.unlockCard(Satanael.ID);
        UnlockTracker.unlockCard(Lucifer.ID);
        UnlockTracker.unlockCard(Thanatos.ID);
        UnlockTracker.unlockCard(TheUniverse.ID);
        UnlockTracker.unlockCard(FuelForTheFire.ID);
        UnlockTracker.unlockCard(ManOfManyFaces.ID);
        UnlockTracker.unlockCard(Safeguard.ID);
        UnlockTracker.unlockCard(Attunement.ID);
        UnlockTracker.unlockCard(Abstinence.ID);
        UnlockTracker.unlockCard(Deicide.ID);
        UnlockTracker.unlockCard(ChangingDestiny.ID);
        UnlockTracker.unlockCard(MasterPlan.ID);
        UnlockTracker.unlockCard(LadyLuck.ID);
        UnlockTracker.unlockCard(UnderLockAndKey.ID);
        UnlockTracker.unlockCard(MaskChange.ID);
        UnlockTracker.unlockCard(UnendingRitual.ID);
        UnlockTracker.unlockCard(ShieldOfMany.ID);
        UnlockTracker.unlockCard(Impatience.ID);
        UnlockTracker.unlockCard(Unburden.ID);
        UnlockTracker.unlockCard(Patience.ID);
        UnlockTracker.unlockCard(Proficiency.ID);
        UnlockTracker.unlockCard(AllOutAttack.ID);
        UnlockTracker.unlockCard(Overwhelm.ID);
        UnlockTracker.unlockCard(Desolation.ID);
        UnlockTracker.unlockCard(Ruination.ID);
        UnlockTracker.unlockCard(EnergyTheft.ID);
        UnlockTracker.unlockCard(IdentityTheft.ID);
        UnlockTracker.unlockCard(TheGreatSeal.ID);
        UnlockTracker.unlockCard(Salvation.ID);
        UnlockTracker.unlockCard(Providence.ID);
        UnlockTracker.unlockCard(CryOfAgony.ID);
        UnlockTracker.unlockCard(BladeOfGenerations.ID);
        UnlockTracker.unlockCard(SacrificialStroke.ID);
        UnlockTracker.unlockCard(EnergizedStroke.ID);
        UnlockTracker.unlockCard(StalwartBlade.ID);
        UnlockTracker.unlockCard(CallToAdventure.ID);
        UnlockTracker.unlockCard(BasicStrike.ID);
        UnlockTracker.unlockCard(BasicDefend.ID);
        
        logger.info("Done adding cards!");
    }
    
    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings for mod with ID: " + getModID());

        loadLocFiles(Settings.GameLanguage.ENG);
        if (Settings.language != Settings.GameLanguage.ENG) {
            loadLocFiles(Settings.language);
        }
        
        logger.info("Done editing strings");
    }

    private static String makeLocPath(Settings.GameLanguage language, String filename)
    {
        String ret = "localization/";
        switch (language) {
            case RUS:
                ret += "rus/";
                break;
            case ZHS:
                ret += "zhs/";
                break;
            default:
                ret += "eng/";
                break;
        }
        return getModID() + "Resources/" + (ret + filename + ".json");
    }

    private void loadLocFiles(Settings.GameLanguage language)
    {
        BaseMod.loadCustomStringsFile(CardStrings.class, makeLocPath(language, "WildcardMod-Card-Strings"));
        BaseMod.loadCustomStringsFile(EventStrings.class, makeLocPath(language, "WildcardMod-Event-Strings"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, makeLocPath(language, "WildcardMod-Character-Strings"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, makeLocPath(language, "WildcardMod-Relic-Strings"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, makeLocPath(language, "WildcardMod-Power-Strings"));
    }

    private void loadLocKeywords(Settings.GameLanguage language)
    {
        Gson gson = new Gson();
        String json = Gdx.files.internal(makeLocPath(language, "WildcardMod-Keyword-Strings")).readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    
    @Override
    public void receiveEditKeywords() {
        loadLocKeywords(Settings.GameLanguage.ENG);
        if (Settings.language != Settings.GameLanguage.ENG) {
            loadLocKeywords(Settings.language);
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom var1) {
        ArcanaEnums.changeArcana(null); //clears the active Arcana before battle in case post battle didn't trigger
        AbstractPersonaCard.changePersona(null); //clears the active Persona before battle in case post battle didn't trigger
        AbstractPersonaCard.canChangePersona = true; //clears the effect from Attunement if present in case post battle didn't trigger
    }

    @Override
    public void receivePostBattle(AbstractRoom var1) {
        ArcanaEnums.changeArcana(null); //clears the active Arcana before battle
        AbstractPersonaCard.changePersona(null); //clears the active Persona before battle
        AbstractPersonaCard.canChangePersona = true; //clears the effect from Attunement if present
    }

    @Override
    public void receiveStartGame() {
        //used to clear these variables in case the player saves and quits then continues
        ArcanaEnums.changeArcana(null);
        AbstractPersonaCard.changePersona(null);
        AbstractPersonaCard.canChangePersona = true;
    }

    @Override
    public void receiveSetUnlocks() {
        //used to clear these variables in case the player saves and quits then views the card library
        ArcanaEnums.changeArcana(null);
        AbstractPersonaCard.changePersona(null);
        AbstractPersonaCard.canChangePersona = true;
    }
    
    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
