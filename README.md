## Android Weather App 
A simple weather app illustrating Android Development best practices with **Android Jetpack**.

#### Getting Started
This project uses the Gradle build system. To build this project, use the gradlew build command or use **Import Project** in Android Studio.

#### Open Weather API Key
I wrote a short C++ code to store the API key securely, and access it from the  **C++** file. 
**Step 1: Install the required tools**
You'll need to install 3 tools in Android Studio via the SDK Manager:
* **NDK (Native Development Kit)**: This is a tool that is used to work with C/C++ code in Android. It also lets you access certain device components, such as sensors, touch input, etc.
* **LLDB (Low Level Debugger)**: This is a debugger for native code.
* **CMake**: This is the tool that builds your native C/C++ library.

![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/sdkmanager.png)

**Step 2: Create a native-lib.cpp file**
Create a new folder, cpp, inside _app/src/main_.
Once you've created it, right-click on the cpp folder, click on **New → C/C++ Source File** and name your file native-lib.cpp.

![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/sdkmanagertwo.png)

**Step 3: Store your API key inside the native-lib.cpp file**
Inside your native-lib.cpp, add the following code:
```
#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_suadahaji_weatherapp_util_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string api_key = "your_api_key_goes_here";
    return env->NewStringUTF(api_key.c_str());
}
```
Note: Don't forget to add your `native-lib.cpp` to your `.gitignore`. You do NOT want this file to be in your version control.

That's it and you'll be able to fetch data from the server.

Here’s a sample app that is setup for you to take a look at and get started [Secure API Key Store Playground](https://github.com/bapspatil/SecureAPIKeyStorePlayground)

### Screenshots


| ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/home1.png) | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/search1.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/search2.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/addCity2.png)  |
| ------ | ------ |------ |------ |

| ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/cityDetail.png)  |![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/cityList.png) | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settings.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settingTheme.png)  |
| ------ | ------ |------ |------ |


| ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/deleteCities.png) | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settingsUnits.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settingsHelp.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settingsNoInternet.png)  |
| ------ | ------ |------ |------ |


##### With this app you  will be able to : 

  - See a list of cities you've bookmarked
  - Delete a city/cities
  - Add cities
  - Click on any city to see more details
  - Make sure to turn on your internet 

