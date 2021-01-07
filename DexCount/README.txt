To clean:
        gradle clean --no-daemon

To compile:
        gradle assembleDebug  --no-daemon

To count:
	gradle :app:countDebugDexMethods

	> Task :app:countDebugDexMethods
	Total methods in app-debug.apk: 26298 (40.13% used)
	Total fields in app-debug.apk:  24093 (36.76% used)
	Total classes in app-debug.apk:  2916 (4.45% used)
	Methods remaining in app-debug.apk: 39237
	Fields remaining in app-debug.apk:  41442
	Classes remaining in app-debug.apk:  62619

