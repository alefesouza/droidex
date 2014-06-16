package aloogle.pokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Database;

public class ListAbilityAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] abilityData;
    private final int nAbility;

    public ListAbilityAdapter(Context context, String[] abilityData) {
        super(context, R.layout.row_ability, abilityData);
        this.context = context;
        this.abilityData = abilityData;
        this.nAbility = abilityData.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_ability, null);

        TextView txtAbilityName = (TextView) convertView.findViewById(R.id.txtAbilityName);
        TextView txtAbilityDesc = (TextView) convertView.findViewById(R.id.txtAbilityDescription);
        LinearLayout rowAbility = (LinearLayout) convertView.findViewById(R.id.rowAbility);

        String[] item = abilityData[position].split(Database.SPLIT);
        String label;

        if (item[1].equals("0")) label = "Ability : ";
        else label = "Hidden Ability : ";

        txtAbilityName.setText(label + item[0]);
        txtAbilityDesc.setText(item[2]);

        if (position == nAbility-1) rowAbility.setBackgroundResource(android.R.color.transparent);

        return convertView;
    }
}
