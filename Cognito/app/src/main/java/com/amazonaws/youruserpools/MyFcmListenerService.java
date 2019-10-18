package com.amazonaws.youruserpools;

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

    @Override
    public void onNewToken(String token) {
        android.util.Log.d(TAG, "onNewToken: " + token);

        /**
         * Called if InstanceID token is updated. This may occur if the security of
         * the previous token had been compromised. Note that this is called when the InstanceID token
         * is initially generated so this is where you would retrieve the token.
         */

        SnSUtils sns = new SnSUtils();
        sns.sendRegistrationToServer(token);
    }
}
