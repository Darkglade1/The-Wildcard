package theWildCard.cards;

import basemod.abstracts.CustomCard;
import theWildCard.WildcardMod;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

//A class to automatically fill in the name and descriptions of cards
public abstract class AbstractDefaultCard extends CustomCard {

    public int defaultSecondMagicNumber;        // A second modifiable stat
    public int defaultBaseSecondMagicNumber;    // The base value the stat will reset to by default.
    public boolean upgradedDefaultSecondMagicNumber; // Whether the number has been upgraded or not.
    public boolean isDefaultSecondMagicNumberModified; // Whether the number has been modified or not, for coloring purposes. (red/green)

    public AbstractDefaultCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isDefaultSecondMagicNumberModified = false;

        if (WildcardMod.enableAltCardArt) {
            if (type == CardType.ATTACK) {
                this.setBackgroundTexture("theWildCardResources/images/512/bg_attack_original.png", "theWildCardResources/images/1024/bg_attack_original.png");
            }
            if (type == CardType.SKILL) {
                this.setBackgroundTexture("theWildCardResources/images/512/bg_skill_original.png", "theWildCardResources/images/1024/bg_skill_original.png");
            }
            if (type == CardType.POWER) {
                this.setBackgroundTexture("theWildCardResources/images/512/bg_power_original.png", "theWildCardResources/images/1024/bg_power_original.png");
            }
        }
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedDefaultSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isDefaultSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void upgradeDefaultSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        defaultBaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedDefaultSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }
}