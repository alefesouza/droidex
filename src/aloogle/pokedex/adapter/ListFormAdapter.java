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

public class ListFormAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] listForm;
    private final int nForm;
    private final boolean formSwitchable;
    private pokemonInterface pokemonInterface;

    public ListFormAdapter(Context context, String[] listForm, boolean formSwitchable) {
        super(context, R.layout.row_pokemon_form, listForm);

        this.context = context;
        this.listForm = listForm;
        this.formSwitchable = formSwitchable;
        pokemonInterface = (pokemonInterface) context;
        nForm = listForm.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_pokemon_form, null);

        LinearLayout rowForm = (LinearLayout) convertView.findViewById(R.id.rowForm);
        TextView txtRowName = (TextView) convertView.findViewById(R.id.txtRowNama);
        TextView txtRowType1 = (TextView) convertView.findViewById(R.id.txtRowType1);
        TextView txtRowType2 = (TextView) convertView.findViewById(R.id.txtRowType2);
        ImageView imgRow = (ImageView) convertView.findViewById(R.id.imgRowIcon);

        final String[] dataRow = listForm[position].split(Database.SPLIT);
        txtRowName.setText(dataRow[1]);

        int type1 = Integer.valueOf(dataRow[2]);
        txtRowType1.setText(Other.getTypeName(type1));
        txtRowType1.setTextColor(Other.getTypeColor(type1));
        txtRowType1.setCompoundDrawablesWithIntrinsicBounds(Other.getTypeImage(type1),0,0,0);

        int type2 = Integer.valueOf(dataRow[3]);
        if (type2 > 0) {
            txtRowType2.setText(Other.getTypeName(type2));
            txtRowType2.setTextColor(Other.getTypeColor(type2));
            txtRowType2.setCompoundDrawablesWithIntrinsicBounds(Other.getTypeImage(type2),0,0,0);
        }

        Other.setImage(imgRow,"sprites/normal/front/nf_" + dataRow[0] + ".png",R.drawable.unknown_small);

        if (position == nForm-1) rowForm.setBackgroundResource(R.drawable.red_filter_title);

        if (position != 0) {
            rowForm.setClickable(true);
            rowForm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokemonInterface.formSelected(dataRow[4], dataRow[0], dataRow[1], formSwitchable);
                }
            });
        }

        return convertView;
    }
}