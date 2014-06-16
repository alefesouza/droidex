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
import aloogle.pokedex.other.Other;

public class ListEfficacyAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] efficacyData;
    private final int nType;

    public ListEfficacyAdapter(Context context, String[] efficacyData) {
        super(context, R.layout.row_ability, efficacyData);
        this.context = context;
        this.efficacyData = efficacyData;
        nType = efficacyData.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String[] item = efficacyData[position].split(Database.SPLIT);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_type, null);

        LinearLayout rowType = (LinearLayout) convertView.findViewById(R.id.rowType);
        TextView txtType = (TextView) convertView.findViewById(R.id.txtTypeName);
        TextView txtValue = (TextView) convertView.findViewById(R.id.txtTypeValue);

        int type_id = Integer.valueOf(item[0]);
        String type_name = context.getString(Other.getTypeName(type_id));

        txtType.setText(type_name);
        txtType.setCompoundDrawablesWithIntrinsicBounds(0, 0, Other.getTypeImage(type_id), 0);
        txtValue.setText("× " + item[1]);
        txtValue.setTextColor(Other.getTypeColor(type_id));

        if (position == nType-1) rowType.setBackgroundResource(android.R.color.transparent);
        return convertView;
    }
}