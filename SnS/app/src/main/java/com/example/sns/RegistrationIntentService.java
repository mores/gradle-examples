package com.example.sns;

import android.app.IntentService;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(android.content.Intent intent) {
        try {
            String token = com.google.firebase.iid.FirebaseInstanceId.getInstance().getToken();
            android.util.Log.i(TAG, "FCM Registration Token: " + token);
            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token);
        } catch (Exception e) {
            android.util.Log.d(TAG, "Failed to complete token refresh", e);
        }
    }

    private void sendRegistrationToServer(String token) {
        // TODO: send the token to SNS and create platform endpoint
    }
}
