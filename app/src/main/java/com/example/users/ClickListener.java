package com.example.users;

import android.view.View;

/**
 * Created by Merlyne on 3/9/2017.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
