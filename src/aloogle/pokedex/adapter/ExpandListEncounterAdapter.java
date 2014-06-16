package aloogle.pokedex.adapter;

import android.content.Context;
import android.text.Html;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Database;

public class ExpandListEncounterAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String[] listEncounter;
    private SparseArray<String[]> childData;

    public ExpandListEncounterAdapter(Context context, String[] listEncounter, SparseArray<String[]> child) {
        this.context = context;
        this.listEncounter = listEncounter;
        this.childData = child;
    }

    private static class viewParentHolder {
        public TextView txtLocation;
        public TextView txtLevel;
        public ImageView imgShowChild;
    }

    private static class viewChildHolder {
        public TextView txtLevel;
        public TextView txtChance;
    }

    @Override
    public int getGroupCount() {
        return listEncounter.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData.get(groupPosition).length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listEncounter[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupPosition)[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        viewParentHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_encounter_parent, null);

            holder = new viewParentHolder();
            holder.txtLocation = (TextView) convertView.findViewById(R.id.txtLocation);
            holder.txtLevel = (TextView) convertView.findViewById(R.id.txtLevel);
            holder.imgShowChild = (ImageView) convertView.findViewById(R.id.imgShowChild);

            convertView.setTag(holder);
        } else holder = (viewParentHolder) convertView.getTag();

        String data = (String) getGroup(groupPosition);
        String[] dataRow = data.split(Database.SPLIT);

        String location;
        if (!dataRow[2].isEmpty()) location = "<b>" + dataRow[1] + "</b><small>, " + dataRow[2] + "</small>";
        else location = "<b>" + dataRow[1] + "</b>";

        holder.txtLocation.setText(Html.fromHtml(location));

        String level = dataRow[3].equals(dataRow[4]) ? dataRow[3] : dataRow[3] + "-" + dataRow[4];
        holder.txtLevel.setText("Lvl. " + level + ", with " + dataRow[5] + "% chance");

        if (isExpanded) holder.imgShowChild.setImageResource(R.drawable.ic_min_pokedex);
        else holder.imgShowChild.setImageResource(R.drawable.ic_max_pokedex);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        viewChildHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_encounter_child, null);

            holder = new viewChildHolder();
            holder.txtLevel = (TextView) convertView.findViewById(R.id.txtLevelChild);
            holder.txtChance = (TextView) convertView.findViewById(R.id.txtChanceChild);

            convertView.setTag(holder);
        } else holder = (viewChildHolder) convertView.getTag();

        String data = (String) getChild(groupPosition, childPosition);
        String[] dataRow = data.split(Database.SPLIT);

        String level = dataRow[0].equals(dataRow[1]) ? dataRow[0] : dataRow[0] + "-" + dataRow[1];
        holder.txtLevel.setText("Lvl. " + level);
        holder.txtChance.setText(dataRow[2] + "%");

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}