package com.example.users;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link ProfileInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    // The request code must be 0 or greater.
    //private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    //private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;
    //private PlusOneButton mPlusOneButton;

    private TextView mUsername;
    private ImageView mProfileImage;
    private TextView mGitUrl;
    private TextView mId;
    private String musername;
    private String mgiturl;
    private String mavatarstr;
    private String midstr;
    private OnFragmentInteractionListener mListener;

    public ProfileInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment ProfileInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileInfoFragment newInstance(Intent intent) {
        ProfileInfoFragment fragment = new ProfileInfoFragment();
       /* Intent intent = new Intent();*/

           Bundle intentExtras = intent.getExtras();
        String jsonstr = intentExtras.get("gituser").toString();
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonstr);
            JSONArray gitcontacts = jsonObj.getJSONArray("gituser");
            //HashMap<String, String> gituser = (HashMap<String, String>) intentExtras.get("gituser");
            for (int i = 0; i < gitcontacts.length(); i++) {
                JSONObject c = gitcontacts.getJSONObject(i);

                String id = c.getString("id");
                String name = c.getString("login");
                String avatar = c.getString("avatar_url");
                String address = c.getString("url");

                // tmp hash map for single contact
                HashMap<String, String> contact = new HashMap<>();

                // adding each child node to HashMap key => value
                contact.put("id", id);
                contact.put("login", name);
                contact.put("url", address);
                contact.put("avatar_url", avatar);
                //contact.put("mobile", mobile);

                // adding contact to contact list
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, name);
                args.putString(ARG_PARAM2, address);
                args.putString(ARG_PARAM3, avatar);
                args.putString(ARG_PARAM4, id);

                fragment.setArguments(args);
            }

            //Bundle intentExtras = intent.getExtras();


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            musername = getArguments().getString(ARG_PARAM1);
            mgiturl = getArguments().getString(ARG_PARAM2);
            mavatarstr = getArguments().getString(ARG_PARAM3);
            midstr = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);

        mGitUrl = (TextView)view.findViewById(R.id.git_url_textdetail);
        mId = (TextView)view.findViewById(R.id.git_id_textdetail);
        mUsername = (TextView)view.findViewById(R.id.username_textdetail);
        mProfileImage = (ImageView)view.findViewById(R.id.profile_image_viewdetail);

        mGitUrl.setText(mgiturl);
        mUsername.setText(musername);
        mId.setText(midstr);
        Picasso.with(getContext()).load(mavatarstr).into(mProfileImage);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
