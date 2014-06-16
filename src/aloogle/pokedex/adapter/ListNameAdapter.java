package aloogle.pokedex.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import aloogle.pokedex.R;
import aloogle.pokedex.other.Database;
import aloogle.pokedex.other.Other;

public class ListNameAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] listName;

    public ListNameAdapter(Context context, String[] listName) {
        super(context, R.layout.row_pokemon_name, listName);

        this.context = context;
        this.listName = listName;
    }

    private static class viewHolder {
        public TextView txtRowID;
        public TextView txtRowName;
        public TextView txtRowSpecies;
        public TextView txtRowType1;
        public TextView txtRowType2;
        public ImageView imgRow;
        public makeList makeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_pokemon_name, null);

            holder = new viewHolder();
            holder.txtRowID = (TextView) convertView.findViewById(R.id.txtRowID);
            holder.txtRowName = (TextView) convertView.findViewById(R.id.txtRowNama);
            holder.txtRowSpecies = (TextView) convertView.findViewById(R.id.txtRowSpecies);
            holder.txtRowType1 = (TextView) convertView.findViewById(R.id.txtRowType1);
            holder.txtRowType2 = (TextView) convertView.findViewById(R.id.txtRowType2);
            holder.imgRow = (ImageView) convertView.findViewById(R.id.imgRowIcon);

            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
            holder.makeList.cancel(true);
        }

        String[] dataRow = listName[position].split(Database.SPLIT);
        holder.makeList = new makeList(holder,dataRow);
        holder.makeList.execute();

        return convertView;
    }

    private class makeList extends AsyncTask<Void,Void,Void> {
        private viewHolder holder;
        private String[] data;
        private Bitmap imgRow;
        private int type1_id, type2_id;
        private int type1_image, type2_image;
        private int type1_name, type1_color;
        private int type2_name, type2_color;

        public makeList(viewHolder holder, String[] data) {
            this.holder = holder;
            this.data = data;
        }

        @Override
        protected void onPreExecute() {
            holder.txtRowID.setText(data[0] + ".");
            holder.txtRowName.setText(data[1]);
            holder.txtRowSpecies.setText(data[2] + " Pokémon");
            type1_id = Integer.valueOf(data[3]);
            type2_id = data.length == 4 ? 0 : Integer.valueOf(data[4]);
        }

        @Override
        protected Void doInBackground(Void... params) {
            imgRow = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() +
                    "/DroiDex/sprites/normal/front/nf_" + data[0] + ".png");

            type1_image = Other.getTypeImage(type1_id);
            type1_color = Other.getTypeColor(type1_id);
            type1_name = Other.getTypeName(type1_id);
            
            type2_image = Other.getTypeImage(type2_id);
            type2_color = Other.getTypeColor(type2_id);
            type2_name = Other.getTypeName(type2_id);

            return null;
        }

        @Override
        protected void onPostExecute(Void res) {
            Other.setImage(holder.imgRow,imgRow,R.drawable.unknown_small);

            holder.txtRowType1.setText(type1_name);
            holder.txtRowType1.setTextColor(type1_color);
            holder.txtRowType1.setCompoundDrawablesWithIntrinsicBounds(0,0,type1_image,0);
            
            if (type2_id == 0) holder.txtRowType2.setVisibility(View.GONE);
            else {
                holder.txtRowType2.setText(type2_name);
                holder.txtRowType2.setTextColor(type2_color);
                holder.txtRowType2.setCompoundDrawablesWithIntrinsicBounds(0,0,type2_image,0);
                holder.txtRowType2.setVisibility(View.VISIBLE);
            }
        }
    }
}
