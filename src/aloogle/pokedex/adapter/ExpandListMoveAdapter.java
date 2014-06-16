package aloogle.pokedex.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Database;
import aloogle.pokedex.other.Other;

import java.util.Random;

public class ExpandListMoveAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String[] listMove;
    private String[] listChildMove;

    public ExpandListMoveAdapter(Context context, String[] listMove, String[] listChildMove) {
        this.context = context;
        this.listMove = listMove;
        this.listChildMove = listChildMove;
    }

    private static class viewParentHolder {
        public TextView txtMoveName;
        public ImageView imgShowChild;
    }

    private static class viewChildHolder {
        public TextView txtMoveDescription;
        public TextView txtMoveType;
        public TextView txtMoveContestType;
        public TextView txtMovePower;
        public TextView txtMovePP;
        public TextView txtMoveAccuracy;
        public TextView txtMoveTarget;
        public TextView txtMoveDamageClass;
        public TextView txtMoveAilment;
    }

    @Override
    public int getGroupCount() {
        return listMove.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listMove[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChildMove[groupPosition];
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
            convertView = inflater.inflate(R.layout.row_move_parent, null);

            holder = new viewParentHolder();
            holder.txtMoveName = (TextView) convertView.findViewById(R.id.txtMoveName);
            holder.imgShowChild = (ImageView) convertView.findViewById(R.id.imgShowChild);

            convertView.setTag(holder);
        } else holder = (viewParentHolder) convertView.getTag();

        String data = (String) getGroup(groupPosition);
        String[] dataRow = data.split(Database.SPLIT);

        String name = "<b>" + dataRow[1] + "</b>";
        String detail;
        if (dataRow[2].equals("0")) {
            if (dataRow[3].equals("null") || dataRow[3].isEmpty()) detail = "";
            else detail = "<small>, " + dataRow[3] + "</small>";
        } else detail = "<small>, lvl. " + dataRow[2] + "</small>";

        holder.txtMoveName.setText(Html.fromHtml(name + detail));
        holder.txtMoveName.setCompoundDrawablesWithIntrinsicBounds(
                Other.getTypeImage(Integer.valueOf(dataRow[4])), 0, 0, 0);

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
            convertView = inflater.inflate(R.layout.row_move_child, null);

            holder = new viewChildHolder();
            holder.txtMoveDescription = (TextView) convertView.findViewById(R.id.txtMoveDescription);
            holder.txtMoveType = (TextView) convertView.findViewById(R.id.txtMoveType);
            holder.txtMoveContestType = (TextView) convertView.findViewById(R.id.txtMoveContestType);
            holder.txtMovePower = (TextView) convertView.findViewById(R.id.txtMovePower);
            holder.txtMovePP = (TextView) convertView.findViewById(R.id.txtMovePP);
            holder.txtMoveAccuracy = (TextView) convertView.findViewById(R.id.txtMoveAccuracy);
            holder.txtMoveTarget = (TextView) convertView.findViewById(R.id.txtMoveTarget);
            holder.txtMoveDamageClass = (TextView) convertView.findViewById(R.id.txtMoveDamageClass);
            holder.txtMoveAilment = (TextView) convertView.findViewById(R.id.txtMoveAilment);

            convertView.setTag(holder);
        } else holder = (viewChildHolder) convertView.getTag();

        String data = (String) getChild(groupPosition, childPosition);
        String[] dataRow = data.split(Database.SPLIT);

        int type_id = Integer.valueOf(dataRow[1]);
        String type = context.getResources().getString(Other.getTypeName(type_id));
        String accuracy = dataRow[5];
        String ailment = dataRow[8];

        holder.txtMoveType.setText(Html.fromHtml("<b>Type : </b>" + type));
        holder.txtMoveContestType.setText(Html.fromHtml("<b>Contest : </b>" + dataRow[2]));
        holder.txtMovePower.setText(Html.fromHtml("<b>Power : </b>" + dataRow[3]));
        holder.txtMovePP.setText(Html.fromHtml("<b>PP : </b>" + dataRow[4]));

        if (accuracy.isEmpty()) holder.txtMoveAccuracy.setText(Html.fromHtml("<b>Acc : </b>0"));
        else holder.txtMoveAccuracy.setText(Html.fromHtml("<b>Acc : </b>" + accuracy + "%"));

        holder.txtMoveTarget.setText(Html.fromHtml("<b>Move Target : </b>" + dataRow[6]));
        holder.txtMoveDamageClass.setText(Html.fromHtml("<b>Damage Class : </b>" + dataRow[7]));

        if (ailment.equals("none"))
            holder.txtMoveAilment.setText(Html.fromHtml("<b>Ailment : </b>None"));
        else holder.txtMoveAilment.setText(Html.fromHtml
                ("<b>Ailment : </b>" + ailment + "<small> " + dataRow[9] + "%</small>"));

        String[] description = dataRow[10].split("ǂ");
        int n_desc = description.length;

        if (n_desc == 0) holder.txtMoveDescription.setVisibility(View.GONE);
        else holder.txtMoveDescription.setText(description[new Random().nextInt(n_desc)]);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
