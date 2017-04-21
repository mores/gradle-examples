Download and install sdk-tools-linux-3859397.zip ( or newer version ) from https://developer.android.com/studio/index.html#downloads

mkdir android
unzip
echo 'count=0' > ~/.android/repositories.cfg
cd tools/bin
./sdkmanager "platforms;android-25" "build-tools;25.0.2" 

export ANDROID_HOME=android

To Clean:
	gradle clean --no-daemon

	
To Build Debug:
	gradle assembleDebug --no-daemon

	APK can be found here: app/build/outputs/apk

To Install
	adb connect <ipaddress>
	adb devices
	adb install app/build/outputs/apk/app-debug.apk

To Uninstall
	adb uninstall <package.name>

Other useful items
-----------------
	adb logcat
	adb shell
