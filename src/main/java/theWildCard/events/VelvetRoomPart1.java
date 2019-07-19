package theWildCard.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.Alice;
import theWildCard.cards.Persona.Arsene;
import theWildCard.cards.Persona.Loki;
import theWildCard.cards.Persona.Metatron;
import theWildCard.cards.Persona.Michael;
import theWildCard.cards.Persona.Odin;
import theWildCard.cards.Persona.PaleRider;
import theWildCard.cards.Persona.Polydeuces;
import theWildCard.cards.Persona.Sakuya;
import theWildCard.cards.Persona.Scathach;

import java.util.ArrayList;

import static theWildCard.WildcardMod.makeEventPath;

public class VelvetRoomPart1 extends AbstractImageEvent {


    public static final String ID = WildcardMod.makeID("VelvetRoomPart1");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("VelvetRoom.png");

    private int screenNum = 0; // The initial screen we will see when encountering the event - screen 0;
    private  ArrayList<AbstractCard> personaList = new ArrayList<>();

    public VelvetRoomPart1() {
        super(NAME, DESCRIPTIONS[0], IMG);
        imageEventText.setDialogOption(OPTIONS[6]);
    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        AbstractCard c;
        switch (screenNum) {
            case 0:
                this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                screenNum = 1;
                break;
            case 1:
                this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                screenNum = 2;
                this.imageEventText.optionList.clear();
                createOptions();
                break;
            case 2: // While you are on screen number 0 (The starting screen)
                switch (i) {
                    case 0: // If you press button the first button (Button at index 0)
                        c = personaList.get(i).makeCopy();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));

                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 3;
                        break;
                    case 1: // If you press button the second button (Button at index 1), in this case: Deinal
                        c = personaList.get(i).makeCopy();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));

                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 3;
                        break;
                    case 2: // If you press button the third button (Button at index 2)
                        c = personaList.get(i).makeCopy();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));

                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 3;
                        break;
                    case 3: // If you press button the fourth button (Button at index 3),
                        this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 3;
                        break;
                }
                break;
            case 3: // Welcome to screenNum = 3;
                switch (i) {
                    case 0: // If you press the first (and this should be the only) button,
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
        }
    }

    private void createOptions() {
        ArrayList<Integer> chooseArcanas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            chooseArcanas.add(i);
        }
        chooseArcanas.remove(AbstractDungeon.miscRng.random(chooseArcanas.size() - 1));
        chooseArcanas.remove(AbstractDungeon.miscRng.random(chooseArcanas.size() - 1));

        if (chooseArcanas.contains(0)) {
            ArrayList<AbstractCard> cards = new ArrayList();
            cards.add(new Sakuya());
            cards.add(new Scathach());
            AbstractCard personaCard = cards.remove(AbstractDungeon.miscRng.random(cards.size() - 1));
            personaList.add(personaCard);
            imageEventText.setDialogOption(OPTIONS[0] + personaCard.name, personaCard); // Gain Priestess Persona
        }
        if (chooseArcanas.contains(1)) {
            ArrayList<AbstractCard> cards = new ArrayList();
            cards.add(new Polydeuces());
            cards.add(new Odin());
            AbstractCard personaCard = cards.remove(AbstractDungeon.miscRng.random(cards.size() - 1));
            personaList.add(personaCard);
            imageEventText.setDialogOption(OPTIONS[1] + personaCard.name, personaCard); // Gain Emperor Persona
        }
        if (chooseArcanas.contains(2)) {
            ArrayList<AbstractCard> cards = new ArrayList();
            cards.add(new Arsene());
            cards.add(new Loki());
            AbstractCard personaCard = cards.remove(AbstractDungeon.miscRng.random(cards.size() - 1));
            personaList.add(personaCard);
            imageEventText.setDialogOption(OPTIONS[2] + personaCard.name, personaCard); // Gain Fool Persona
        }
        if (chooseArcanas.contains(3)) {
            ArrayList<AbstractCard> cards = new ArrayList();
            cards.add(new Michael());
            cards.add(new Metatron());
            AbstractCard personaCard = cards.remove(AbstractDungeon.miscRng.random(cards.size() - 1));
            personaList.add(personaCard);
            imageEventText.setDialogOption(OPTIONS[3] + personaCard.name, personaCard); // Gain Judgement Persona
        }
        if (chooseArcanas.contains(4)) {
            ArrayList<AbstractCard> cards = new ArrayList();
            cards.add(new PaleRider());
            cards.add(new Alice());
            AbstractCard personaCard = cards.remove(AbstractDungeon.miscRng.random(cards.size() - 1));
            personaList.add(personaCard);
            imageEventText.setDialogOption(OPTIONS[4] + personaCard.name, personaCard); // Gain Death Persona
        }

        imageEventText.setDialogOption(OPTIONS[5]); // Leave
    }
}
