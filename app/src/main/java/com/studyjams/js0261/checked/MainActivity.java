package com.studyjams.js0261.checked;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;

import cn.studyjams.js0261.checked.R;

public class MainActivity extends Activity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickTime();
                startActivity(new Intent(getApplicationContext(),ListActivity.class));
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        mainTimes();
    }

    private void buttonClickTime() {
        String id =  "Main";
        String name = "Button_Click";

        // [START image_view_event]
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "button");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        // [END image_view_event]
    }


    private void mainTimes() {
        mFirebaseAnalytics.setCurrentScreen(this, MainActivity.class.getSimpleName(), null /* class override */);
    }
}
