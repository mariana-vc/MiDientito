package com.example.violeta.nurse;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by Christian on 17/11/15.
 */
public class App extends Application  {

    @Override
    public void onCreate() {
        super.onCreate();
        if(getApplicationContext()!=null) {
            Parse.enableLocalDatastore(getApplicationContext());
            Parse.initialize(getApplicationContext(), "qY1eD09TH335iRd0J0bQ4rt1AGIUozOmXwVzvDri", "dg5dhbf9iXvVhy15jn0EgaDae3uwsv0SRHJ35QFN");
            ParsePush.subscribeInBackground("", new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                    } else {
                        Log.e("com.parse.push", "failed to subscribe for push", e);
                    }
                }
            });
            ParseInstallation.getCurrentInstallation().saveInBackground();
        }

    }

}
