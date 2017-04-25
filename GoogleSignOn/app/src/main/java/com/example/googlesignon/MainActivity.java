package com.example.googlesignon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "GoogleSignOn::MainActivity";

    private static final int RC_GET_AUTH_CODE = 9003;

    @butterknife.BindView( R.id.button1 )
    protected android.widget.Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	android.util.Log.i( TAG, "onCreate" );
	butterknife.ButterKnife.setDebug(true);
	butterknife.ButterKnife.bind( this );

	String serverClientId = "<Client ID from console.developers.google.com>";
	com.google.android.gms.auth.api.signin.GoogleSignInOptions gso = new com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder(com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN)
		.requestIdToken(serverClientId)
		.requestEmail()
		.build();

	final com.google.android.gms.common.api.GoogleApiClient mGoogleApiClient = new com.google.android.gms.common.api.GoogleApiClient.Builder(this)
		.enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
		.addApi(com.google.android.gms.auth.api.Auth.GOOGLE_SIGN_IN_API, gso)
		.build();

	button1.setOnClickListener( new android.view.View.OnClickListener()
	{
		@Override
		public void onClick( android.view.View v )
		{
			android.util.Log.i( TAG, "I have been pushed !" );

			android.content.Intent signInIntent = com.google.android.gms.auth.api.Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
			startActivityForResult(signInIntent, RC_GET_AUTH_CODE);
		}
	} );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

	android.util.Log.i( TAG, "onActivityResult" );

        if (requestCode == RC_GET_AUTH_CODE) {
 
            android.util.Log.i( TAG, "RC_GET_AUTH_CODE" );
            com.google.android.gms.auth.api.signin.GoogleSignInResult result = com.google.android.gms.auth.api.Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            android.util.Log.d(TAG, "onActivityResult:GET_AUTH_CODE:success:" + result.getStatus().isSuccess());

            if (result.isSuccess()) {
		android.util.Log.i( TAG, "Success !!" );
                com.google.android.gms.auth.api.signin.GoogleSignInAccount acct = result.getSignInAccount();
		String idToken = acct.getIdToken();
		android.util.Log.i( TAG, "idToken: " + idToken );

		ValidateAuthCodeTask validateAuthCodeTask = new ValidateAuthCodeTask( idToken );
		String[] passing = {};
		validateAuthCodeTask.execute( passing );

            } else {
		android.util.Log.i( TAG, "Failure" );
            }
        }
    }

    @Override
    public void onConnectionFailed( com.google.android.gms.common.ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        android.util.Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
}
