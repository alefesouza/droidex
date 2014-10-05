package aloogle.pokedex.other;

import android.content.Context;
import android.database.Cursor;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Database extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "pokedex_data";
    private static final int DATABASE_VERSION = 29;
    public static final String SPLIT = "ǁ";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade(29);
    }

    private Cursor getCursor(String query, String[] args) {
        return getReadableDatabase().rawQuery(query, args);
    }

    public String[] getPokemonList(String name, String generation, String color, String type,
                                   boolean isBaby, boolean hasGenderDiff) {
        String q_name, q_generation, q_color, q_type, q_baby, q_gender;
        q_name = name.isEmpty() ? "%" : "%" + name + "%";
        q_generation = generation.isEmpty() ? "" : " AND sp.generation_id IN (" + generation + ") ";
        q_color = color.isEmpty() ? "" : " AND sp.color_id IN (" + color + ") ";
        q_type = type.isEmpty() ? "" : " AND tp.type_id IN (" + type + ") ";
        q_baby = isBaby ? " AND sp.is_baby = 1 " : "";
        q_gender = hasGenderDiff ? " AND sp.has_gender_differences = 1 " : "";

        String query = "SELECT sp.id, nm.eng_name, nm.species, GROUP_CONCAT(tp.type_id,'ǁ') " +
                "FROM pokemon_species as sp " +
                "LEFT JOIN pokemon_name as nm ON sp.id = nm.pokemon_id " +
                "LEFT JOIN pokemon_type as tp ON tp.pokemon_id = sp.id " +
                "WHERE sp.identifier LIKE \"" + q_name + "\"" +
                q_generation + q_color + q_type + q_baby + q_gender +
                "GROUP BY tp.pokemon_id";

        Cursor c = getCursor(query, new String[] {});

        int length = c.getCount();
        String[] ID = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            ID[n] = c.getString(0) + SPLIT +
                    c.getString(1) + SPLIT +
                    c.getString(2) + SPLIT +
                    c.getString(3);
            c.moveToNext();
        }

        c.close();
        return ID;
    }

    public String getPokemonName(String id) {
        Cursor name = getCursor("" +
                "SELECT eng_name, jap_name, romaji_name, species " +
                "FROM pokemon_name " +
                "WHERE pokemon_id = ?",
                new String[] {id});

        String Name = null;
        if (name.moveToFirst())
            Name = name.getString(0) + SPLIT +
                    name.getString(1) + SPLIT +
                    name.getString(2) + SPLIT +
                    name.getString(3);

        name.close();
        return Name;
    }

    public String getPokemonHabitat(String id) {
        Cursor habitat = getCursor("" +
                "SELECT hb.name " +
                "FROM pokemon_species as sp LEFT JOIN pokemon_habitat as hb " +
                "ON sp.habitat_id = hb.pokemon_habitat_id " +
                "WHERE sp.id = ?",
                new String[] {id});

        String Habitat = null;
        if (habitat.moveToFirst()) Habitat = habitat.getString(0);

        habitat.close();
        return Habitat;
    }

    public String getPokemonType(String id) {
        Cursor type = getCursor("" +
                "SELECT type_id " +
                "FROM pokemon_type " +
                "WHERE pokemon_id = ? " +
                "ORDER BY slot",
                new String[] {id});

        String Type = null;
        if (type.moveToFirst()) {
            Type = type.getString(0) + SPLIT;

            if(type.moveToNext()) Type += type.getString(0);
            else Type += "0";
        }

        type.close();
        return Type;
    }

    public String getPokemonHWE(String id) {
        Cursor hwe = getCursor("" +
                "SELECT height, weight, base_experience " +
                "FROM pokemon " +
                "WHERE id = ?",
                new String[] {id});

        String HWE = null;
        if (hwe.moveToFirst())
            HWE = hwe.getString(0) + SPLIT +
                    hwe.getString(1) + SPLIT +
                    hwe.getString(2);

        hwe.close();
        return HWE;
    }

    public String getPokemonShape(String id) {
        Cursor shape = getCursor("" +
                "SELECT pokemon_shape.name, pokemon_shape.awesome_name " +
                "FROM pokemon_species LEFT JOIN pokemon_shape " +
                "ON pokemon_species.shape_id = pokemon_shape.pokemon_shape_id " +
                "WHERE pokemon_species.id = ?",new String[] {id});

        String Shape = null;
        if (shape.moveToFirst()) {
            if (shape.getString(1).length() > 12)
                Shape = shape.getString(0) + "\n(" + shape.getString(1) + ")";
            else Shape = shape.getString(0) + " (" + shape.getString(1) + ")";
        }

        shape.close();
        return Shape;
    }

    public String getPokemonGrowthRate(String id) {
        Cursor growth = getCursor("" +
                "SELECT growth_rate.name " +
                "FROM pokemon_species LEFT JOIN growth_rate " +
                "ON pokemon_species.growth_rate_id = growth_rate.growth_rate_id " +
                "WHERE pokemon_species.id = ?", new String[] {id});

        String Growth = null;
        if (growth.moveToFirst()) Growth = growth.getString(0);

        growth.close();
        return Growth;
    }

    public String getPokemonGCHHGF(String id) {
        Cursor gchhgf = getCursor("" +
                "SELECT " +
                "gender_rate, capture_rate, base_happiness, hatch_counter, " +
                "has_gender_differences, forms_switchable, evolution_chain_id " +
                "FROM pokemon_species " +
                "WHERE id = ?", new String[] {id});

        String GCHHGF = null;
        if (gchhgf.moveToFirst())
            GCHHGF = gchhgf.getString(0) + SPLIT +
                    gchhgf.getString(1) + SPLIT +
                    gchhgf.getString(2) + SPLIT +
                    gchhgf.getString(3) + SPLIT +
                    gchhgf.getString(4) + SPLIT +
                    gchhgf.getString(5) + SPLIT +
                    gchhgf.getString(6);

        gchhgf.close();
        return GCHHGF;
    }

    public String getPokemonEgg(String id) {
        Cursor egg = getCursor("" +
                "SELECT egg.name " +
                "FROM pokemon_egg_group as pegg LEFT JOIN egg " +
                "ON pegg.egg_group_id = egg.egg_group_id " +
                "WHERE pegg.pokemon_id = ?",new String[] {id});

        String Egg = null;
        if (egg.moveToFirst()) {
            Egg = egg.getString(0);
            while (egg.moveToNext()) Egg += ", " + egg.getString(0);
        }

        egg.close();
        return Egg;
    }

    public String getPokemonBaseEffort(String id) {
        Cursor e = getCursor("" +
                "SELECT stat.name, pstat.effort " +
                "FROM pokemon_stat as pstat LEFT JOIN stat " +
                "ON pstat.stat_id = stat.stat_id " +
                "WHERE pstat.pokemon_id = ?", new String[] {id});

        String Effort = null;
        if (e.moveToFirst()) {
            Effort = "";
            int length = e.getCount();
            for (int n = 0; n < length; n++) {
                if (!e.getString(1).equals("0")) {
                    Effort += (Effort.equals("") ? "" : "\n");
                    Effort += e.getString(1) + " " + e.getString(0);
                }
                e.moveToNext();
            }
        }

        e.close();
        return Effort;
    }

    public String[] getPokedexNumber(String id) {
        Cursor c = getCursor("" +
                "SELECT dex.name, dnum.pokedex_number " +
                "FROM pokemon_dex_number as dnum LEFT JOIN pokedex as dex " +
                "ON dnum.pokedex_id = dex.pokedex_id " +
                "WHERE dnum.species_id = ? " +
                "ORDER BY dnum.pokedex_id", new String[] {id});

        int length = c.getCount();
        String[] dexData = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            dexData[n] = c.getString(0) + SPLIT + c.getString(1);
            c.moveToNext();
        }

        c.close();
        return dexData;
    }

    public String[] getPokemonDescription(String id) {
        Cursor c = getCursor("" +
                "SELECT GROUP_CONCAT(ver.name,', '), des.description " +
                "FROM pokemon_description as des LEFT JOIN version as ver " +
                "ON des.version_id = ver.version_id " +
                "WHERE des.pokemon_id = ? " +
                "group by DES.DESCRIPTION " +
                "ORDER BY des.version_id",new String[] {id});

        int length = c.getCount();
        String[] Desc = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
                Desc[n] = c.getString(0) + SPLIT + c.getString(1);
                c.moveToNext();
            }

        c.close();
        return Desc;
    }

    public String[] getPokemonAbility(String id) {
        Cursor c = getCursor("" +
                "SELECT ability.name, pokemon_ability.is_hidden, ability.description " +
                "FROM pokemon_ability LEFT JOIN ability " +
                "ON pokemon_ability.ability_id = ability.ability_id " +
                "WHERE pokemon_ability.pokemon_id = ? " +
                "GROUP BY pokemon_ability.ability_id " +
                "ORDER by pokemon_ability.slot",new String[] {id});

        int length = c.getCount();
        String[] Ability = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
                Ability[n] = c.getString(0) + SPLIT +
                        c.getString(1) + SPLIT +
                        c.getString(2);

                c.moveToNext();
            }

        c.close();
        return Ability;
    }

    public String[] getPokemonStat(String id) {
        Cursor c = getCursor("" +
                "SELECT base_stat " +
                "FROM pokemon_stat " +
                "WHERE pokemon_id = ? AND stat_id IN (1,2,3,4,5,6) " +
                "ORDER BY pokemon_stat.stat_id", new String[] {id});

        int length = c.getCount();
        String[] stat = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
                stat[n] = c.getString(0);
                c.moveToNext();
            }

        c.close();
        return stat;
    }

    public String[] getPokemonMaxStat(String id, String[] baseStat) {
        int nStat = baseStat.length;
        String[] maxStat = new String[nStat];

        for (int n = 0; n < nStat; n++) {
            maxStat[n] = getPokemonMaxStat(id,n + 1,baseStat[n]);
        }

        return maxStat;
    }

    public String[] getPokemonEfficacy(String type1, String type2) {
        Cursor c = getCursor("SELECT " +
                "tab1.damage_type_id as type_id, " +
                "tab1.damage_factor * IFNULL(tab2.damage_factor,'100') / 100 as dmg " +
                "FROM " +
                "(SELECT damage_type_id, damage_factor FROM type_efficacy WHERE target_type_id = ?) as tab1 " +
                "LEFT JOIN " +
                "(SELECT damage_type_id, damage_factor FROM type_efficacy WHERE target_type_id = ?) as tab2 " +
                "ON tab1.damage_type_id = tab2.damage_type_id " +
                "WHERE NOT dmg = 100 " +
                "ORDER BY dmg DESC, type_id ASC", new String[] {type1,type2});

        int length = c.getCount();
        String[] efficacy = new String[length];

        if (c.moveToFirst()) {
            String value, symbol;
            for (int n = 0; n < length; n++) {
                value = c.getString(1);
                if (value.equals("50")) symbol = "½";
                else if (value.equals("25")) symbol = "¼";
                else symbol = String.valueOf(Integer.valueOf(value) / 100);

                efficacy[n] = c.getString(0) + SPLIT + symbol;
                c.moveToNext();
            }
        }

        c.close();
        return efficacy;
    }

    public String[] getPokemonForm(String id, String name) {
        Cursor c = getCursor("" +
                "SELECT f.id, " +
                "CASE LENGTH(nm.pokemon_name) WHEN 0 THEN \"" + name + "\" ELSE nm.pokemon_name END as Name, " +
                "tp1.type_id, IFNULL(tp2.type_id,'0'), f.pokemon_id " +
                "FROM pokemon as p " +
                "LEFT JOIN pokemon_form as f " +
                "ON p.id = f.pokemon_id " +
                "LEFT JOIN pokemon_form_name as nm " +
                "ON f.id = nm.pokemon_form_id " +
                "LEFT JOIN  " +
                "(SELECT pokemon_id, type_id FROM pokemon_type WHERE slot = 1) as tp1 " +
                "ON p.id = tp1.pokemon_id " +
                "LEFT JOIN  " +
                "(SELECT pokemon_id, type_id FROM pokemon_type WHERE slot = 2) as tp2 " +
                "ON p.id = tp2.pokemon_id " +
                "WHERE p.species_id = ? ",new String[] {id});

        int length = c.getCount();
        String[] ID = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            ID[n] = c.getString(0) + SPLIT +
                    c.getString(1) + SPLIT +
                    c.getString(2) + SPLIT +
                    c.getString(3) + SPLIT +
                    c.getString(4);
            c.moveToNext();
        }

        c.close();
        return ID;
    }

    public String[] getPokemonEvolution(String evolution_id) {
        Cursor c = getCursor("SELECT " +
                "sp.evolves_from_species_id, " +
                "sp.id, " +
                "(SELECT eng_name FROM pokemon_name WHERE pokemon_id = sp.id) as evolved_name, " +
                "ev.evolution_trigger_id, " +
                "(SELECT name FROM item_description WHERE item_id = ev.trigger_item_id) as trigger_item, " +
                "ev.minimum_level, " +
                "ev.gender_id, " +
                "loc.name, " +
                "(SELECT name FROM item_description WHERE item_id = ev.held_item_id) as held_item, " +
                "ev.time_of_day, " +
                "mv.name as known_move, " +
                "(SELECT name FROM type_name WHERE type_id = ev.known_move_type_id) as known_move_type, " +
                "ev.minimum_happiness, " +
                "ev.minimum_beauty, " +
                "ev.minimum_affection, " +
                "ev.relative_physical_stats, " +
                "(SELECT eng_name FROM pokemon_name WHERE pokemon_id = ev.party_species_id) as party_species, " +
                "(SELECT name FROM type_name WHERE type_id = ev.party_type_id) as party_type, " +
                "(SELECT eng_name FROM pokemon_name WHERE pokemon_id = ev.trade_species_id) as trade_species, " +
                "ev.needs_overworld_rain, " +
                "ev.turn_upside_down " +
                "FROM pokemon_species as sp " +
                "LEFT JOIN pokemon_evolution as ev ON sp.id = ev.evolved_species_id " +
                "LEFT JOIN location_name as loc ON ev.location_id = loc.location_id " +
                "LEFT JOIN move_name as mv ON mv.move_id = ev.known_move_id " +
                "WHERE sp.evolution_chain_id = ? " +
                "ORDER BY sp.[order]", new String[] {evolution_id});

        int length = c.getCount();
        int column_count = c.getColumnCount();
        String[] data = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            data[n] = c.getString(0);
            for (int a = 1; a < column_count; a++) data[n] += SPLIT + c.getString(a);
            c.moveToNext();
        }

        c.close();
        return data;
    }

    public String[] getPokemonVersion(String id) {
        Cursor c = getCursor("" +
                "SELECT DISTINCT e.version_id, v.name " +
                "FROM encounters as e " +
                "LEFT JOIN version as v ON e.version_id = v.version_id " +
                "WHERE e.pokemon_id = ? " +
                "ORDER BY e.version_id", new String[] {id});

        int length = c.getCount();
        String[] version = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            version[n] = c.getString(0) + SPLIT + c.getString(1);
            c.moveToNext();
        }

        c.close();
        return version;
    }

    public String[] getPokemonEncounterMethod(String id) {
        Cursor c = getCursor("" +
                "SELECT DISTINCT es.encounter_method_id, em.name " +
                "FROM encounters as e " +
                "LEFT JOIN encounter_slots as es ON e.encounter_slot_id = es.id " +
                "LEFT JOIN encounter_method as em ON es.encounter_method_id = em.encounter_method_id " +
                "WHERE e.pokemon_id = ?", new String[] {id});

        int length = c.getCount();
        String[] encounter_method = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            encounter_method[n] = c.getString(0) + SPLIT + c.getString(1);
            c.moveToNext();
        }

        c.close();
        return encounter_method;
    }

    public String[] getPokemonLocation(String id, String version_id, String method_id) {
        Cursor c = getCursor("" +
                "SELECT e.location_area_id, loc.name, " +
                "ar.name, MIN(e.min_level), MAX(e.max_level), " +
                "SUM(es.rarity) " +
                "FROM encounters AS e " +
                "LEFT JOIN location_area AS ar ON e.location_area_id = ar.id " +
                "LEFT JOIN location_name AS loc ON ar.location_id = loc.location_id " +
                "LEFT JOIN encounter_slots AS es ON e.encounter_slot_id = es.id " +
                "WHERE e.pokemon_id = ? AND e.version_id = ? AND es.encounter_method_id = ? " +
                "GROUP BY es.encounter_method_id, e.location_area_id " +
                "ORDER BY es.encounter_method_id, loc.name", new String[] {id, version_id, method_id});

        int length = c.getCount();
        String[] location = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            location[n] = c.getString(0) + SPLIT +
                    c.getString(1) + SPLIT +
                    c.getString(2) + SPLIT +
                    c.getString(3) + SPLIT +
                    c.getString(4) + SPLIT +
                    c.getString(5);
            c.moveToNext();
        }

        c.close();
        return location;
    }

    public String[] getPokemonDetailedLocation(String id, String version_id, String location_id, String method_id) {
        Cursor c = getCursor("" +
                "SELECT e.min_level, e.max_level, SUM(es.rarity) " +
                "FROM encounters as e  " +
                "LEFT JOIN encounter_slots AS es ON e.encounter_slot_id = es.id " +
                "WHERE e.pokemon_id = ? " +
                "AND e.version_id = ? " +
                "AND e.location_area_id = ? " +
                "AND es.encounter_method_id = ? " +
                "GROUP BY e.min_level, e.max_level",
                new String[] {id, version_id, location_id, method_id});

        int length = c.getCount();
        String[] location = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            location[n] = c.getString(0) + SPLIT +
                    c.getString(1) + SPLIT +
                    c.getString(2);
            c.moveToNext();
        }

        c.close();
        return location;
    }

    public String[] getPokemonVersionGroup(String id) {
        Cursor c = getCursor("" +
                "SELECT DISTINCT pm.version_group_id, vg.name " +
                "FROM pokemon_move as pm  " +
                "LEFT JOIN version_group as vg ON pm.version_group_id = vg.version_group_id " +
                "WHERE pm.pokemon_id = ? " +
                "ORDER by pm.version_group_id", new String[] {id});

        int length = c.getCount();
        String[] version_group = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            version_group[n] = c.getString(0) + SPLIT + c.getString(1);
            c.moveToNext();
        }

        c.close();
        return version_group;
    }

    public String[] getPokemonMoveMethod(String id) {
        Cursor c = getCursor("" +
                "SELECT DISTINCT pm.pokemon_move_method_id, pmm.name " +
                "FROM pokemon_move as pm  " +
                "LEFT JOIN pokemon_move_method as pmm " +
                "ON pm.pokemon_move_method_id = pmm.pokemon_move_method_id " +
                "WHERE pm.pokemon_id = ? " +
                "ORDER by pm.pokemon_move_method_id", new String[] {id});

        int length = c.getCount();
        String[] move_method = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            move_method[n] = c.getString(0) + SPLIT + c.getString(1);
            c.moveToNext();
        }

        c.close();
        return move_method;
    }

    public String[] getPokemonMoveParentList(String id, String ver_id, String method_id) {
        Cursor c = getCursor("" +
                "SELECT pm.move_id, mn.name, GROUP_CONCAT(pm.level,' / '), itm.name, mv.type_id " +
                "FROM pokemon_move AS pm " +
                "LEFT JOIN move_name AS mn ON pm.move_id = mn.move_id " +
                "LEFT JOIN move AS mv ON pm.move_id = mv.move_id " +
                "LEFT JOIN machine as mch ON mch.move_id = pm.move_id  " +
                "    AND mch.version_group_id = pm.version_group_id  " +
                "    AND pm.pokemon_move_method_id = 4 " +
                "LEFT JOIN item_description as itm ON mch.item_id = itm.item_id " +
                "WHERE pm.pokemon_id = ?  " +
                "AND pm.version_group_id = ?  " +
                "AND pm.pokemon_move_method_id = ? " +
                "GROUP BY pm.move_id " +
                "ORDER BY pm.pokemon_move_method_id, pm.level, pm.[order]",
                new String[] {id, ver_id, method_id});

        int length = c.getCount();
        String[] move_list = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            move_list[n] = c.getString(0) + SPLIT +
                            c.getString(1) + SPLIT +
                            c.getString(2) + SPLIT +
                            c.getString(3) + SPLIT +
                            c.getString(4);
            c.moveToNext();
        }

        c.close();
        return move_list;
    }

    public String[] getPokemonMoveChildList(String id, String ver_id, String method_id) {
        Cursor c = getCursor("" +
                "SELECT " +
                "pm.move_id, mv.type_id, ct.name,  " +
                "mv.power, mv.pp, mv.accuracy, mt.name, mdc.name, " +
                "mma.name, mm.ailment_chance, GROUP_CONCAT(md.description,'ǂ') " +
                "FROM pokemon_move AS pm " +
                "LEFT JOIN move AS mv ON pm.move_id = mv.move_id " +
                "LEFT JOIN move_meta AS mm on pm.move_id = mm.move_id " +
                "LEFT JOIN move_target AS mt on mv.target_id = mt.move_target_id " +
                "LEFT JOIN move_damage_class as mdc on mv.damage_class_id = mdc.move_damage_class_id " +
                "LEFT JOIN move_meta_ailment as mma on mm.meta_ailment_id = mma.move_meta_ailment_id " +
                "LEFT JOIN contest_type as ct on mv.contest_type_id = ct.contest_type_id " +
                "LEFT JOIN move_description as md on pm.move_id = md.move_id " +
                "WHERE pm.pokemon_id = ?  " +
                "AND pm.version_group_id = ?  " +
                "AND pm.pokemon_move_method_id = ? " +
                "GROUP BY pm.move_id " +
                "ORDER BY pm.pokemon_move_method_id, pm.level, pm.[order]",
                new String[] {id, ver_id, method_id});

        int length = c.getCount();
        int column_count = c.getColumnCount();
        String[] move_list = new String[length];
        if (c.moveToFirst()) for (int n = 0; n < length; n++) {
            move_list[n] = c.getString(0);
            for (int a = 1; a < column_count; a++) move_list[n] += SPLIT + c.getString(a);
            c.moveToNext();
        }

        c.close();
        return move_list;
    }

    private String getPokemonMaxStat(String id, int stat_id, String baseStat) {
        if (id.equals("292") && stat_id == 1) return "1";
        else {
            int base = Integer.parseInt(baseStat);
            int max;

            if (stat_id == 1)
                max = 31 + (2 * base) + (252/4) + 100 + 10;
            else
                max = (int)((31 + (2 * base) + (252/4) + 5) * 1.1);

            return String.valueOf(max);
        }
    }
}