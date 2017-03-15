package com.example.users;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class ProfileDetailActivity extends AppCompatActivity implements ProfileInfoFragment.OnFragmentInteractionListener {
    private TextView mUsername;
    private ImageView mProfileImage;
    private TextView mGitUrl;
    private TextView mId;
    private Button mButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        Picasso.with(getApplicationContext()).load(avatarstr).into(mProfileImage);

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

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
