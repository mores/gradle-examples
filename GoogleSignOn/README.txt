Setup:
	https://developers.google.com/mobile/add

	Platform: Android
	App Name: GoogleSignOn
	Package Name: com.example.googlesignon	

	Add: Google Sign on
		( Provide your SHA1 key from: keytool -printcert -jarfile app/build/outputs/apk/app-debug.apk )

	Enable Google Sign on

	Navigate to: https://console.developers.google.com/apis/credentials

	Grab the Web application client id, put it in app/src/main/java/com/example/googlesignon/MainActivity.java 

To clean:
	gradle clean --no-daemon

To install:
	gradle installDebug  --no-daemon
