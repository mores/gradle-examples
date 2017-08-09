package com.example.onedrivepickertestcase;

/*
 * https://components.xamarin.com/gettingstarted/onedrivepicker
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.net.*;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import com.microsoft.onedrivesdk.saver.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity {

    private Logger log = LoggerFactory.getLogger(MainActivity.class);

    private ISaver mSaver;
    private String ONEDRIVE_APP_ID = "fiil-in-your-own-id"; // Get app id here: https://account.live.com/developers/applications

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log.info("onCreate");

	Button button = (Button)findViewById(R.id.button);
	button.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			log.info("onClick");
			final File file = new File("/sdcard/logback/log.txt");

			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String destinationFilename = dateFormat.format(new Date());

			log.info("sizeOfFile: " + file.length());
			if (file.length() > 0) {
			    // create and launch the saver
			    mSaver = Saver.createSaver(ONEDRIVE_APP_ID);
			    mSaver.startSaving(MainActivity.this, destinationFilename, Uri.fromFile(file));
			}
		}
	} );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            mSaver.handleSave(requestCode, resultCode, data);
        } catch (final SaverException e) {
            // Log error information
            log.error(e.getErrorType().toString()); // Provides one of the SaverError enum
            log.debug(e.getDebugErrorInfo()); // Detailed debug error message
        }
    }
}
