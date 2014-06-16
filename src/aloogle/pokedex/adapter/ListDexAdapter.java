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

public class ListDexAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] dexData;
    private final int nMax;

    public ListDexAdapter(Context context, String[] dexData) {
        super(context, R.layout.row_dex_number, dexData);
        this.context = context;
        this.dexData = dexData;
        nMax = dexData.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_dex_number, null);

        LinearLayout rowDex = (LinearLayout) convertView.findViewById(R.id.rowDex);
        TextView txtDexName = (TextView) convertView.findViewById(R.id.txtDexName);
        TextView txtDexNumber = (TextView) convertView.findViewById(R.id.txtDexNumber);

        String[] item = dexData[position].split(Database.SPLIT);
        txtDexName.setText(item[0]);
        txtDexNumber.setText(item[1]);

        if (position == nMax-1) rowDex.setBackgroundResource(android.R.color.transparent);

        return convertView;
    }
}
