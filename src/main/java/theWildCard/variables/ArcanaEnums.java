package theWildCard.variables;

public class ArcanaEnums {
    public enum Arcana
    {
        PRIESTESS, EMPEROR, FOOL, JUDGEMENT, DEATH;
    }
    private static Arcana activeArcana;

    public static void changeArcana(Arcana arcana) {
        activeArcana = arcana;
    }

    public static Arcana getActiveArcana() {
        return activeArcana;
    }
}
