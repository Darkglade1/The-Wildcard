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
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
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
import theWildCard.cards.Attack.Common.CallToAdventure;
import theWildCard.cards.Attack.Common.EnergizedStroke;
import theWildCard.cards.Attack.Common.SacrificialStroke;
import theWildCard.cards.Attack.Common.StalwartBlade;
import theWildCard.cards.Attack.Rare.AllOutAttack;
import theWildCard.cards.Attack.Rare.Devastate;
import theWildCard.cards.Attack.Rare.Overwhelm;
import theWildCard.cards.Attack.Rare.Ruination;
import theWildCard.cards.Attack.Uncommon.CryOfAgony;
import theWildCard.cards.Attack.Uncommon.IdentityTheft;
import theWildCard.cards.Attack.Uncommon.LadyLuck;
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
import theWildCard.cards.Skill.Uncommon.Lock;
import theWildCard.cards.Skill.Uncommon.MaskChange;
import theWildCard.cards.Skill.Uncommon.ShieldOfMany;
import theWildCard.cards.Skill.Uncommon.UnendingRitual;
import theWildCard.characters.WildcardCharacter;
import theWildCard.events.IdentityCrisisEvent;
import theWildCard.relics.BlankContractRelic;
import theWildCard.relics.VelvetContractRelic;
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
        OnStartBattleSubscriber {

    public static final Logger logger = LogManager.getLogger(WildcardMod.class.getName());
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true; // The boolean we'll be setting on/off (true/false)

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "The Wild Card";
    private static final String AUTHOR = "Darkglade"; // And pretty soon - You!
    private static final String DESCRIPTION = "A character mod inspired by the Persona Series.";
    
    // =============== INPUT TEXTURE LOCATION =================
    
    // Colors (RGB)
    // Character Color
    public static final Color DEFAULT_BLUE = CardHelper.getColor(11.0f, 11.0f, 96.0f);
  
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
    private static final String THE_DEFAULT_BUTTON = "theWildCardResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "theWildCardResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "theWildCardResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "theWildCardResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "theWildCardResources/images/char/defaultCharacter/corpse.png";
    
    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "theWildCardResources/images/Badge.png";
    
    // Atlas and JSON files for the Animations
    public static final String THE_DEFAULT_SKELETON_ATLAS = "theWildCardResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "theWildCardResources/images/char/defaultCharacter/skeleton.json";
    
    // =============== MAKE IMAGE PATHS =================
    
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    
    // =============== /MAKE IMAGE PATHS/ =================
    
    // =============== /INPUT TEXTURE LOCATION/ =================
    
    
    // =============== SUBSCRIBE, CREATE THE COLOR_BLUE, INITIALIZE =================
    
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
        
        
        logger.info("Adding mod settings");
        // This loads the mod settings.
        // The actual mod Button is added below in receivePostInitialize()
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE"); // This is the default setting. It's actually set...
        try {
            SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings); // ...right here
            // the "fileName" parameter is the name of the file MTS will create where it will save our setting.
            config.load(); // Load the setting and set the boolean to equal it
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
        
    }
    
    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP
    
    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = WildcardMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT
        logger.info("You are attempting to set your mod ID as: " + ID); // NO WHY
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
        logger.info("Success! ID is " + modID); // WHY WOULD U WANT IT NOT TO LOG?? DON'T EDIT THIS.
    } // NO
    
    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH
    
    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NNOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = WildcardMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = WildcardMod.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    
    // ====== YOU CAN EDIT AGAIN ======
    
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        WildcardMod defaultmod = new WildcardMod();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }
    
    // ============== /SUBSCRIBE, CREATE THE COLOR_BLUE, INITIALIZE/ =================
    
    
    // =============== LOAD THE CHARACTER =================
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + WildcardCharacter.Enums.THE_DEFAULT.toString());
        
        BaseMod.addCharacter(new WildcardCharacter("the Default", WildcardCharacter.Enums.THE_DEFAULT),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, WildcardCharacter.Enums.THE_DEFAULT);

        logger.info("Added " + WildcardCharacter.Enums.THE_DEFAULT.toString());
    }
    
    // =============== /LOAD THE CHARACTER/ =================
    
    
    // =============== POST-INITIALIZE =================
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        
        // Create the on/off button:
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                enablePlaceholder, // Boolean it uses
                settingsPanel, // The mod panel in which this button will be in
                (label) -> {}, // thing??????? idk
                (button) -> { // The actual button:
            
            enablePlaceholder = button.enabled; // The boolean true/false will be whether the button is enabled or not
            try {
                // And based on that boolean, set the settings and save them
                SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        settingsPanel.addUIElement(enableNormalsButton); // Add the button to the settings panel. Button is a go.
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        
        // =============== EVENTS =================
        
        // This event will be exclusive to the City (act 2). If you want an event that's present at any
        // part of the game, simply don't include the dungeon ID
        // If you want to have a character-specific event, look at slimebound (CityRemoveEventPatch).
        // Essentially, you need to patch the game and say "if a player is not playing my character class, remove the event from the pool"
        BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);
        
        // =============== /EVENTS/ =================
        logger.info("Done loading badge Image and mod options");
    }
    
    // =============== / POST-INITIALIZE/ =================
    
    

    
    
    // ================ ADD RELICS ===================
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        
        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        BaseMod.addRelicToCustomPool(new BlankContractRelic(), WildcardCharacter.Enums.COLOR_BLUE);
        BaseMod.addRelicToCustomPool(new VelvetContractRelic(), WildcardCharacter.Enums.COLOR_BLUE);

        logger.info("Done adding relics!");
    }
    
    // ================ /ADD RELICS/ ===================
    
    
    // ================ ADD CARDS ===================
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        //Ignore this
        pathCheck();
        // Add the Custom Dynamic Variables
        logger.info("Add variabls");
        // Add the Custom Dynamic variabls
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
        BaseMod.addCard(new Lock());
        BaseMod.addCard(new MaskChange());
        BaseMod.addCard(new UnendingRitual());
        BaseMod.addCard(new ShieldOfMany());
        BaseMod.addCard(new Impatience());
        BaseMod.addCard(new Unburden());
        BaseMod.addCard(new Patience());
        BaseMod.addCard(new Proficiency());
        BaseMod.addCard(new AllOutAttack());
        BaseMod.addCard(new Overwhelm());
        BaseMod.addCard(new Devastate());
        BaseMod.addCard(new Ruination());
        BaseMod.addCard(new LadyLuck());
        BaseMod.addCard(new IdentityTheft());
        BaseMod.addCard(new Salvation());
        BaseMod.addCard(new Providence());
        BaseMod.addCard(new CryOfAgony());
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
        UnlockTracker.unlockCard(Lock.ID);
        UnlockTracker.unlockCard(MaskChange.ID);
        UnlockTracker.unlockCard(UnendingRitual.ID);
        UnlockTracker.unlockCard(ShieldOfMany.ID);
        UnlockTracker.unlockCard(Impatience.ID);
        UnlockTracker.unlockCard(Unburden.ID);
        UnlockTracker.unlockCard(Patience.ID);
        UnlockTracker.unlockCard(Proficiency.ID);
        UnlockTracker.unlockCard(AllOutAttack.ID);
        UnlockTracker.unlockCard(Overwhelm.ID);
        UnlockTracker.unlockCard(Devastate.ID);
        UnlockTracker.unlockCard(Ruination.ID);
        UnlockTracker.unlockCard(LadyLuck.ID);
        UnlockTracker.unlockCard(IdentityTheft.ID);
        UnlockTracker.unlockCard(Salvation.ID);
        UnlockTracker.unlockCard(Providence.ID);
        UnlockTracker.unlockCard(CryOfAgony.ID);
        UnlockTracker.unlockCard(SacrificialStroke.ID);
        UnlockTracker.unlockCard(EnergizedStroke.ID);
        UnlockTracker.unlockCard(StalwartBlade.ID);
        UnlockTracker.unlockCard(CallToAdventure.ID);
        UnlockTracker.unlockCard(BasicStrike.ID);
        UnlockTracker.unlockCard(BasicDefend.ID);
        
        logger.info("Done adding cards!");
    }
    
    // There are better ways to do this than listing every single individual card, but I do not want to complicate things
    // in a "tutorial" mod. This will do and it's completely ok to use. If you ever want to clean up and
    // shorten all the imports, go look take a look at other mods, such as Hubris.
    
    // ================ /ADD CARDS/ ===================
    
    
    // ================ LOAD THE TEXT ===================
    
    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        
        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/WildcardMod-Card-Strings.json");
        
        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/WildcardMod-Power-Strings.json");
        
        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/WildcardMod-Relic-Strings.json");
        
        // Event Strings
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/WildcardMod-Event-Strings.json");
        
        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/WildcardMod-Potion-Strings.json");
        
        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/WildcardMod-Character-Strings.json");
        
        // OrbStrings
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/WildcardMod-Orb-Strings.json");
        
        logger.info("Done edittting strings");
    }
    
    // ================ /LOAD THE TEXT/ ===================
    
    // ================ LOAD THE KEYWORDS ===================
    
    @Override
    public void receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword
        
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/WildcardMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom var1) {
        ArcanaEnums.changeArcana(null); //clears the active Arcana before battle
        AbstractPersonaCard.changePersona(null); //clears the active Persona before battle
        AbstractPersonaCard.canChangePersona = true; //clears the effect from Attunement if present
    }
    
    // ================ /LOAD THE KEYWORDS/ ===================    
    
    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
