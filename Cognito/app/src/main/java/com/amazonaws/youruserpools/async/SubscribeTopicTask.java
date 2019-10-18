package com.amazonaws.youruserpools.async;

import android.os.AsyncTask;

public class SubscribeTopicTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "SubscribeTopicTask";

    private SubscribeTopicResponse delegate = null;
    private String token = null;

    public SubscribeTopicResponse getDelegate(){
        return delegate;
    }

    public void setDelagate( SubscribeTopicResponse delegate){
        this.delegate = delegate;
    }

    public String getToken() { return token;}

    public void setToken( String token ) { this.token = token; }

    @Override
    protected String doInBackground(String... urls) {

        com.amazonaws.auth.CognitoCachingCredentialsProvider credentialsProvider = new com.amazonaws.auth.CognitoCachingCredentialsProvider(
                com.amazonaws.youruserpools.MainApp.getContext(), // Context
                com.amazonaws.youruserpools.AppHelper.identityPoolId, // Identity Pool ID
                com.amazonaws.regions.Regions.US_WEST_2 // Region
        );

        String identityId = credentialsProvider.getIdentityId();
        android.util.Log.d(TAG, "my cognito ID is " + identityId);

        com.amazonaws.services.sns.AmazonSNS snsClient = new com.amazonaws.services.sns.AmazonSNSClient( credentialsProvider );
        snsClient.setRegion( com.amazonaws.regions.Region.getRegion( com.amazonaws.regions.Regions.US_WEST_2 ) );

        com.amazonaws.services.sns.model.CreatePlatformEndpointRequest createPlatformEndpointRequest = new com.amazonaws.services.sns.model.CreatePlatformEndpointRequest();
        createPlatformEndpointRequest.setToken( token );
        createPlatformEndpointRequest.setPlatformApplicationArn( com.amazonaws.youruserpools.AppHelper.platformApplicationArn );

        com.amazonaws.services.sns.model.CreatePlatformEndpointResult createPlatformEndpointResult = snsClient.createPlatformEndpoint(createPlatformEndpointRequest);
        String endpointArn = createPlatformEndpointResult.getEndpointArn();
        android.util.Log.d(TAG, "endpointArn: " + endpointArn);

        com.amazonaws.services.sns.model.SubscribeRequest subscribeRequest = new com.amazonaws.services.sns.model.SubscribeRequest( com.amazonaws.youruserpools.AppHelper.topicArn, "application", endpointArn);
        com.amazonaws.services.sns.model.SubscribeResult subscribeResult = snsClient.subscribe( subscribeRequest );
        android.util.Log.d(TAG, "subscribeResult: " + subscribeResult.getSubscriptionArn());

        return "";
    }

    @Override
    protected void onPostExecute( String stuff ){
        delegate.processFinishSubscribeTopic( stuff );
    }
}
