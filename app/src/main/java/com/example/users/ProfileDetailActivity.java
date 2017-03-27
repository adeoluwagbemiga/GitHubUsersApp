package com.example.users;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;



public class ProfileDetailActivity extends AppCompatActivity {
    private TextView mUsername;
    private ImageView mProfileImage;
    private TextView mGitUrl;
    private TextView mId;
    private Button mButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle intentExtras = intent.getExtras();
        final String username = intentExtras.getString("login");
        final String giturl = intentExtras.getString("url");
        String avatarstr = intentExtras.getString("avatar_url");
        String idstr = intentExtras.getString("id");

        mGitUrl = (TextView)findViewById(R.id.git_url_textdetail);
        //mId = (TextView)findViewById(R.id.git_id_textdetail);
        mUsername = (TextView)findViewById(R.id.username_textdetail);
        mProfileImage = (ImageView)findViewById(R.id.profile_image_viewdetail);
        mButton = (Button)findViewById(R.id.share_button);

        mGitUrl.setText(giturl);
        mUsername.setText(username);
        //mId.setText(idstr);
        Picasso.with(getApplicationContext()).load(avatarstr).fit().into(mProfileImage, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap imageBitmap = ((BitmapDrawable)mProfileImage.getDrawable()).getBitmap();
                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(), imageBitmap);
                imageDrawable.setCircular(true);
                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight())/2.0f);
                mProfileImage.setImageDrawable(imageDrawable);
            }

            @Override
            public void onError() {
                mProfileImage.setImageResource(R.drawable.ic_menu_camera);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareintent = new Intent(Intent.ACTION_SEND);
                String sharemessage = String.format("Check out this awesome developer @%s, %s.", username, giturl);
                shareintent.setType("text/plain");
                shareintent.putExtra(Intent.EXTRA_TEXT, sharemessage);
                startActivity(shareintent);
            }
        });
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
