package com.amazonaws.youruserpools;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFcmListenerService extends FirebaseMessagingService {

    static protected int id = 0;

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(com.google.firebase.messaging.RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        android.util.Log.d(TAG, "Receive MSG From: " + remoteMessage.getFrom());
        android.util.Log.d(TAG, "MessageType: " + remoteMessage.getMessageType());
        android.util.Log.d(TAG, "CollapseKey: " + remoteMessage.getCollapseKey());
        android.util.Log.d(TAG, "MessageId: " + remoteMessage.getMessageId());
        android.util.Log.d(TAG, "To: " + remoteMessage.getTo());


        com.google.firebase.messaging.RemoteMessage.Notification noti = remoteMessage.getNotification();
        if( noti != null )
        {
            android.util.Log.d(TAG, "Title: " + noti.getTitle() );
            android.util.Log.d(TAG, "Body: " + noti.getBody() );
        }

        try {
            androidx.core.app.NotificationCompat.Builder mBuilder = new androidx.core.app.NotificationCompat.Builder(this);

            mBuilder.setContentTitle("Title");

            android.content.res.Resources res = MainApp.getContext().getPackageManager().getResourcesForApplication("com.amazonaws.youruserpools.CognitoYourUserPoolsDemo");
            int resId = res.getIdentifier("add", "drawable", "com.amazonaws.youruserpools.CognitoYourUserPoolsDemo");

            mBuilder.setSmallIcon(resId);

            String message = remoteMessage.getData().get("default");
            mBuilder.setContentText(message);

            android.content.Intent resultIntent = new android.content.Intent(this, MainActivity.class);
            android.app.PendingIntent resultPendingIntent =
                    android.app.PendingIntent.getActivity(
                            this,
                            0,
                            resultIntent,
                            android.app.PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);

            android.app.NotificationManager mNotificationManager = (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            mNotificationManager.notify(id++, mBuilder.build());
        }
        catch( Exception e)
        {
            android.util.Log.e(TAG, "Error");
        }

        if (remoteMessage.getData().size() > 0) {
            java.util.Map<String,String> data = remoteMessage.getData();

            for (java.util.Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                android.util.Log.d(TAG, "Message data: " + key + " " + value );
            }
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
