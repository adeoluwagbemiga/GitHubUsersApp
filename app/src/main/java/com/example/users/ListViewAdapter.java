package com.example.users;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


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
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.user_listview_item, parent, false);
            viewHolder.username = (TextView)convertView.findViewById(R.id.username_text);

            viewHolder.profileimageview = (ImageView)convertView.findViewById(R.id.profile_image_view);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.username.setText(gituser.get("login"));



        Picasso.with(getContext()).load(gituser.get("avatar_url")).fit().into(viewHolder.profileimageview, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap imageBitmap = ((BitmapDrawable)viewHolder.profileimageview.getDrawable()).getBitmap();
                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), imageBitmap);
                imageDrawable.setCircular(true);
                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight())/2.0f);
                viewHolder.profileimageview.setImageDrawable(imageDrawable);
            }

            @Override
            public void onError() {
                viewHolder.profileimageview.setImageResource(R.drawable.ic_menu_camera);
            }
        });

        return convertView;
    }


}
