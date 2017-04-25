package com.example.googlesignon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "GoogleSignOn::MainActivity";

    @butterknife.BindView( R.id.button1 )
    protected android.widget.Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	android.util.Log.i( TAG, "onCreate" );
	butterknife.ButterKnife.setDebug(true);
	butterknife.ButterKnife.bind( this );

	button1.setOnClickListener( new android.view.View.OnClickListener()
	{
		@Override
		public void onClick( android.view.View v )
		{
			android.util.Log.i( TAG, "I have been pushed !" );
		}
	} );
    }
}
