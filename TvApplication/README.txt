To Clean:
	gradle clean --no-daemon

	
To Build Debug:
	gradle assembleDebug --no-daemon

	APK can be found here: app/build/outputs/apk

To Install
	adb connect <ipaddress>
	adb devices
	adb -d install app/build/outputs/apk/app-debug.apk

To Uninstall
	adb uninstall <package.name>

Other useful items
-----------------
	adb logcat
	adb shell
