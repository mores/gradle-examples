package com.amazonaws.youruserpools;

import android.util.Log;

import com.amazonaws.youruserpools.async.SubscribeTopicResponse;
import com.amazonaws.youruserpools.async.SubscribeTopicTask;

public class SnSUtils implements SubscribeTopicResponse {

    private static final String TAG = "SnSUtils";

    public void sendRegistrationToServer( String token )
    {
        android.util.Log.d(TAG, "sendRegistrationToServer" );

        SubscribeTopicTask task = new SubscribeTopicTask();
        task.setDelagate( this );
        task.setToken( token );
        String[] passing = {};
        task.execute( passing );
    }

    @Override
    public void processFinishSubscribeTopic(String stuff)
    {
        Log.d(TAG, " -- processFinishGetCredentials");
    }
}
