package com.example.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Merlyne on 3/10/2017.
 */

public class ListViewAdapter extends ArrayAdapter<HashMap<String, String>>{
    public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> gituserslist){
        super(context, R.layout.user_listview_item ,gituserslist);
    }
    private static class ViewHolder{
        TextView username;
        TextView id;
        TextView avatarurl;
        ImageView profileimageview;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        HashMap<String, String> gituser = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.user_listview_item, parent, false);
            viewHolder.username = (TextView)convertView.findViewById(R.id.username_text);
            viewHolder.avatarurl = (TextView)convertView.findViewById(R.id.git_avatarurl_text);
            viewHolder.profileimageview = (ImageView)convertView.findViewById(R.id.profile_image_view);
            viewHolder.id = (TextView)convertView.findViewById(R.id.git_id_text);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.id.setText(gituser.get("id"));
        viewHolder.avatarurl.setText(gituser.get("avatar_url"));
        viewHolder.username.setText(gituser.get("login"));

        Picasso.with(getContext()).load(gituser.get("avatar_url")).into(viewHolder.profileimageview);

        return convertView;
    }
    /*@Override
    public HashMap<String, String> getItem(int position){
         HashMap<String, String> gituser = getItem(position);
        return gituser;
    }

    @Override
    public long getItemId(int position){
        return position;
    }*/

}
