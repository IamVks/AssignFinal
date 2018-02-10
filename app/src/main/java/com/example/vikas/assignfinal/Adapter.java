package com.example.vikas.assignfinal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by VIKAS on 2/9/2018.
 */

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataSet> DataList;
    ImageLoader imageLoader = Controller.getPermission().getImageLoader();

    public Adapter(Activity activity, List<DataSet> MyItems) {
        this.activity = activity;
        this.DataList = MyItems;
    }

    @Override
    public int getCount() {
        return DataList.size();
    }

    @Override
    public Object getItem(int location) {
        return DataList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = Controller.getPermission().getImageLoader();
        CircularNetworkImageView thumbNail = (CircularNetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView skills = (TextView) convertView.findViewById(R.id.skills);
        DataSet m = DataList.get(position);
        if (m.getImage().equals("")){

            thumbNail.setImageResource(R.mipmap.img2);
        }else {
            thumbNail.setImageUrl(m.getImage(), imageLoader);
        }
        name.setText(m.getName());
        skills.setText(m.getSkills());

        return convertView;
    }

}

