package com.example.sns;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFcmListenerService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(com.google.firebase.messaging.RemoteMessage remoteMessage) {
        android.util.Log.d(TAG, "Receive MSG From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            android.util.Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
    }
}
