package com.amazonaws.youruserpools;


public class RegistrationIntentService extends android.app.IntentService {

    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(android.content.Intent intent) {

        android.util.Log.d(TAG, "onHandleIntent");
        try {
            com.google.firebase.iid.FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new com.google.android.gms.tasks.OnCompleteListener<com.google.firebase.iid.InstanceIdResult>() {

                        @Override
                        public void onComplete(@androidx.annotation.NonNull com.google.android.gms.tasks.Task<com.google.firebase.iid.InstanceIdResult> task) {

                            android.util.Log.d(TAG, "onComplete");

                            if (!task.isSuccessful()) {
                                android.util.Log.w(TAG, "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();

                            SnSUtils sns = new SnSUtils();
                            sns.sendRegistrationToServer(token);
                        }
                    });

        } catch (Exception e) {
            android.util.Log.d(TAG, "Failed to complete token refresh", e);
        }
    }
}
