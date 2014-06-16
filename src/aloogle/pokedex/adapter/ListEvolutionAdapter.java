package aloogle.pokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Database;
import aloogle.pokedex.other.Other;
import aloogle.pokedex.other.Other.pokemonInterface;

public class ListEvolutionAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] listEvolution;
    private final int nMax;
    private final String current_id;
    private pokemonInterface pokemonInterface;

    public ListEvolutionAdapter(Context context, String[] listEvolution, String current_id) {
        super(context, R.layout.row_pokemon_evolution, listEvolution);

        this.context = context;
        this.listEvolution = listEvolution;
        this.current_id = current_id;
        pokemonInterface = (pokemonInterface) context;
        nMax = listEvolution.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_pokemon_evolution, null);

        LinearLayout rowEvolution = (LinearLayout) convertView.findViewById(R.id.rowEvolution);
        ImageView imgFrom = (ImageView) convertView.findViewById(R.id.imgFrom);
        ImageView imgEvolved = (ImageView) convertView.findViewById(R.id.imgEvolved);
        TextView txtArrow = (TextView) convertView.findViewById(R.id.txtArrow);
        TextView txtName = (TextView) convertView.findViewById(R.id.txtRowName);
        TextView txtReason = (TextView) convertView.findViewById(R.id.txtRowReason);

        final String[] dataRow = listEvolution[position].split(Database.SPLIT);

        if (dataRow[0].isEmpty()) {
            imgFrom.setVisibility(View.GONE);
            txtArrow.setVisibility(View.GONE);
            txtReason.setVisibility(View.GONE);
        } else {
            Other.setImage(imgFrom,
                    "sprites/normal/front/nf_" + dataRow[0] + ".png",
                    R.drawable.unknown_small);

            String evolution_trigger = "";
            if (!isEmpty(dataRow[3])) {
                switch (Integer.valueOf(dataRow[3])) {
                    case 1 : evolution_trigger = "Level up"; break;
                    case 2 : evolution_trigger = "Trade"; break;
                    case 3 : evolution_trigger = "Use"; break;
                    case 4 : evolution_trigger =
                            "Shedinja will consume a Poké Ball and appear in a free party slot"; break;
                }
            }

            String using_item = "";
            if (!isEmpty(dataRow[4])) using_item = " " + dataRow[4];

            String minimum_level = "";
            if (!isEmpty(dataRow[5])) minimum_level = ", starting at level " + dataRow[5];

            String gender = "";
            if (!isEmpty(dataRow[6])) {
                switch (Integer.valueOf(dataRow[6])) {
                    case 1 : gender = ", female only"; break;
                    case 2 : gender = ", male only"; break;
                }
            }

            String location = "";
            if (!isEmpty(dataRow[7])) location = ", around " + dataRow[7];

            String held_item = "";
            if (!isEmpty(dataRow[8])) held_item = ", while holding " + dataRow[8];

            String time_of_day = "";
            if (!isEmpty(dataRow[9])) time_of_day = ", during the " + dataRow[9];

            String known_move = "";
            if (!isEmpty(dataRow[10])) known_move = ", knowing " + dataRow[10];

            String known_move_type = "";
            if (!isEmpty(dataRow[11])) known_move_type = ", knowing a " + dataRow[11] + "-type move";

            String minimum_happiness = "";
            if (!isEmpty(dataRow[12])) minimum_happiness = ", with at least " + dataRow[12] + " happiness";

            String minimum_beauty = "";
            if (!isEmpty(dataRow[13])) minimum_beauty = ", with at least " + dataRow[13] + " beauty";

            String minimum_affection = "";
            if (!isEmpty(dataRow[14])) minimum_affection = ", with at least " + dataRow[14] + " affection " +
                    "in Pokémon-Amie";

            String physical_stats = "";
            if (!isEmpty(dataRow[15])) {
                physical_stats = ", when ";
                switch (Integer.valueOf(dataRow[15])) {
                    case 1 : physical_stats += "Attack > Defence"; break;
                    case -1 : physical_stats += "Attack < Defence"; break;
                    case 0 : physical_stats += "Attack = Defence"; break;
                }
            }

            String party_species = "";
            if (!isEmpty(dataRow[16])) party_species = ", with " + dataRow[16] + " in the party";

            String party_type = "";
            if (!isEmpty(dataRow[17])) party_type = ", with a " + dataRow[17] + "-type Pokémon in the party";

            String trade_species = "";
            if (!isEmpty(dataRow[18])) trade_species = ", in exchange for " + dataRow[18];

            String overworld_rain = "";
            if (!isEmpty(dataRow[19]) && dataRow[19].equals("1"))
                overworld_rain = ", while it is raining outside of battle";

            String upside_down = "";
            if (!isEmpty(dataRow[20]) && dataRow[20].equals("1")) {
                upside_down = ", with the 3DS turned upside-down";
            }

            String reason = evolution_trigger + using_item +
                    minimum_level + gender + location +
                    held_item + time_of_day + known_move +
                    known_move_type + minimum_happiness +
                    minimum_beauty + minimum_affection +
                    physical_stats + party_species +
                    party_type + trade_species +
                    overworld_rain + upside_down;
            txtReason.setText(reason);
        }

        Other.setImage(imgEvolved,
                "sprites/normal/front/nf_" + dataRow[1] + ".png",
                R.drawable.unknown_small);

        txtName.setText(dataRow[2]);

        if (position == nMax-1) rowEvolution.setBackgroundResource(R.drawable.red_filter_title);

        if (!dataRow[1].equals(current_id)) {
            rowEvolution.setClickable(true);
            rowEvolution.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokemonInterface.pokemonSelected(dataRow[1]);
                }
            });
        }

        return convertView;
    }

    private boolean isEmpty(String s) {
        return s.isEmpty() || s.equals("null");
    }
}