package aloogle.pokedex.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Database;

public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {
    private final Context context;
    private final String[] version;
    private final String addition;

    public CustomSpinnerAdapter(Context context, String[] version, String addition) {
        this.context = context;
        this.version = version;
        this.addition = addition;
    }

    @Override
    public int getCount() {
        return version.length;
    }

    @Override
    public Object getItem(int position) {
        return version[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtItem = new TextView(context);
        String item = version[position].split(Database.SPLIT)[1];
        txtItem.setText(addition + " " + item);
        txtItem.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        return txtItem;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txtItem = new TextView(context);
        String item = version[position].split(Database.SPLIT)[1];
        txtItem.setText(addition + " " + item);
        txtItem.setGravity(Gravity.CENTER);
        txtItem.setPadding(0, 18, 0, 18);
        txtItem.setBackgroundResource(R.drawable.red_filter_title);
        return txtItem;
    }
}
