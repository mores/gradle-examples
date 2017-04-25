package com.example.googlesignon;

// Params, Progrsss, Result
public class ValidateAuthCodeTask extends android.os.AsyncTask<String, Void, AsyncTaskResult<String>>
{
	private static final String TAG = "GoogleSignOn::ValidateAuthCodeTask";	

	private String authCode;

	public ValidateAuthCodeTask( String authCode )
	{
		this.authCode = authCode;
	}

	@Override
	protected AsyncTaskResult<String> doInBackground( String... passing )
	{
		android.util.Log.i( TAG, "doInBackground" );

		try
                {
                        java.net.URL url = new java.net.URL( "<url of your backend service>" );
                        java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
                        conn.setRequestProperty( "token", authCode );
                        conn.setRequestMethod( "GET" );
                        conn.setDoInput( true );
                        conn.connect();

                        int responseCode = conn.getResponseCode();
                        android.util.Log.i( TAG, "httpCode: " + responseCode );
			
                }
                catch( Exception e )
                {
                        java.io.StringWriter sw = new java.io.StringWriter();
                        java.io.PrintWriter pw = new java.io.PrintWriter( sw );
                        e.printStackTrace( pw );
                        android.util.Log.e( TAG, "Error: " + sw.toString() );
                }

		return new AsyncTaskResult<>( "return" );
	}
}
