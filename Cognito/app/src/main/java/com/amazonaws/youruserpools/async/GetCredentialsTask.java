package com.amazonaws.youruserpools.async;

import android.os.AsyncTask;

public class GetCredentialsTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {

        com.amazonaws.auth.CognitoCachingCredentialsProvider credentialsProvider = new com.amazonaws.auth.CognitoCachingCredentialsProvider(
                com.amazonaws.youruserpools.MainApp.getContext(), // Context
                com.amazonaws.youruserpools.AppHelper.identityPoolId, // Identity Pool ID
                com.amazonaws.regions.Regions.US_WEST_2 // Region
        );
        String identityId = credentialsProvider.getIdentityId();
        android.util.Log.d("LogTag", "my ID is " + identityId);

        return "";
    }

    @Override
    protected void onPostExecute( String stuff ){

    }
}
