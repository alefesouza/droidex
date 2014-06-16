package aloogle.pokedex.object;

import android.content.Context;
import aloogle.pokedex.other.Database;

public class Pokemon  {
    public String ID, EnglishName, JapaneseName, RomajiName, Species,
            Habitat, Type1, Type2, Height, Weight, BodyShape, Gender,
            EggGroups, HatchCounter, GrowthRate, CaptureRate,
            BaseExperience, BaseEffort, BaseHappiness, EvolutionChainId;

    public String[] DexNumber, Description, Ability, Stats, MaxStats,
            TypeEfficacy, VersionGroup, MoveMethod,
            OtherForm, Evolution, Version, EncounterMethod;

    public Boolean hasGenderDifferences, hasDexNumber, hasDescription,
            hasAbility, hasStats, hasEfficacy, formSwitchable,
            hasOtherForm, hasEvolution, hasLocation, hasMove,
            hasBaseData, hasData;

    public Pokemon(Context context, String ID) {
        this.ID = ID;
        Database DB = new Database(context);

//      Pokemon's name
        String[] array_name;
        String name = DB.getPokemonName(ID);

        if (name != null) array_name = name.split(Database.SPLIT);
        else array_name = new String[] {"","","",""};

        EnglishName = array_name[0];
        JapaneseName = array_name[1];
        RomajiName = array_name[2];
        Species = array_name[3].isEmpty() ? "" :
                array_name[3] + " Pokémon";

        hasData = !JapaneseName.isEmpty() && JapaneseName != null;

//      Pokemon's habitat
        String habitat = DB.getPokemonHabitat(ID);
        Habitat = habitat != null ? habitat : "";

//      Pokemon's type
        String[] array_type;
        String type = DB.getPokemonType(ID);

        if (type != null) array_type = type.split(Database.SPLIT);
        else array_type = new String[] {"0","0"};

        Type1 = array_type[0];
        Type2 = array_type[1];

//      Pokemon's height, weight, and base_experience
        String[] array_HWE;
        String HWE = DB.getPokemonHWE(ID);

        if (HWE != null) array_HWE = HWE.split(Database.SPLIT);
        else array_HWE = new String[] {"0","0","0"};

        Height = getHeight(Double.parseDouble(array_HWE[0]));
        Weight = getWeight(Double.parseDouble(array_HWE[1]));
        BaseExperience = array_HWE[2];

//      Pokemon's body shape
        String shape = DB.getPokemonShape(ID);
        BodyShape = shape != null ? shape : "Unknown";

//      Pokemon's growth rate
        String growth = DB.getPokemonGrowthRate(ID);
        GrowthRate = growth != null ? growth : "Unknown";

//      Pokemon's gender ratio, capture rate, base happiness, etc
        String[] array_GCHHGF;
        String GCHHGF = DB.getPokemonGCHHGF(ID);

        if (GCHHGF != null) array_GCHHGF = GCHHGF.split(Database.SPLIT);
        else array_GCHHGF = new String[] {"-2","0","0","0","0","0","0"};

        Gender = getGenderRatio(Integer.valueOf(array_GCHHGF[0]));
        CaptureRate = getCaptureRate(Integer.valueOf(array_GCHHGF[1]));
        BaseHappiness = array_GCHHGF[2];
        HatchCounter = getHatchCounter(Integer.valueOf(array_GCHHGF[3]));
        hasGenderDifferences = array_GCHHGF[4].equals("1");
        formSwitchable = array_GCHHGF[5].equals("1");
        EvolutionChainId = array_GCHHGF[6];

//      Pokemon's egg group
        String egg = DB.getPokemonEgg(ID);
        EggGroups = egg != null ? egg : "Unknown";

//      Pokemon's base effort
        String effort = DB.getPokemonBaseEffort(ID);
        hasBaseData = !effort.isEmpty() && effort != null;
        BaseEffort = hasBaseData ? effort : "-";

//      Pokemon's dex number
        DexNumber = DB.getPokedexNumber(ID);
        hasDexNumber = DexNumber.length > 0;

//      Pokemon's description
        Description = DB.getPokemonDescription(ID);
        hasDescription = Description.length > 0;

//      Pokemon's ability
        Ability = DB.getPokemonAbility(ID);
        hasAbility = Ability.length > 0;

//      Pokemon stats
        Stats = DB.getPokemonStat(ID);
        MaxStats = DB.getPokemonMaxStat(ID, Stats);
        hasStats = Stats.length > 0;

//      Pokemon's efficacy
        TypeEfficacy = DB.getPokemonEfficacy(Type1, Type2);
        hasEfficacy = TypeEfficacy.length > 0;

//      Pokemon's alternative form
        OtherForm = DB.getPokemonForm(ID, EnglishName);
        hasOtherForm = OtherForm.length > 1;

//      Pokemon's evolution
        Evolution = DB.getPokemonEvolution(EvolutionChainId);
        hasEvolution = Evolution.length > 1;

//      Pokemon's location
        Version = DB.getPokemonVersion(ID);
        EncounterMethod = DB.getPokemonEncounterMethod(ID);
        hasLocation = Version.length > 0;

//      Pokemon's move
        VersionGroup = DB.getPokemonVersionGroup(ID);
        MoveMethod = DB.getPokemonMoveMethod(ID);
        hasMove = VersionGroup.length > 0;

        DB.close();
    }

    private String getHeight(double height) {
        int cm = (int) (height * 10);
        int ft = (int) (cm / 30.48);
        double inch = (cm - (ft * 30.48)) / 2.54;

        String metric;
        if (cm < 100) metric = String.valueOf(cm) + " cm";
        else metric = String.format("%s", cm/100d) + " m";

        String british = String.valueOf(ft) + "' " +
                String.format("%.1f", inch) + "\"";

        return metric + "\n" + british;
    }

    private String getWeight(double weight) {
        double kg = weight / 10;
        double lb = kg * 2.20462262;
        return String.format("%s", kg) + " kg\n" +
                String.format("%.2f",lb) + " lbs";
    }

    private String getGenderRatio(int value) {
        int male = 8 - value;

        if (value == -2) return "Unknown";
        else if (value == -1) return "Genderless";
        else return String.format("%s", male / 8d * 100) + "% ♂, "
                + String.format("%s", value / 8d * 100) + "% ♀";
    }

    public String getCaptureRate(int value) {
        if (value == - 1) return "Unknown";
        else {
            double percent = value / 765d * 100;
            return String.valueOf(value) +
                    " (" + String.format("%.2f",percent) + "%) " +
                    "Pokéball, full HP";
        }
    }

    public String getHatchCounter(int value) {
        int steps = value * 255;

        return String.valueOf(value) + " (" +
                String.valueOf(steps) + " steps)";
    }
}
