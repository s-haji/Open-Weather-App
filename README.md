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


| ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/home1.png) | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/search1.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/search2.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/addCity2.png)  |![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/cityDetail.png)  |![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/cityList.png) |
| ------ | ------ |------ |------ |------ |------ |

| ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settings.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settingTheme.png)  |![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/deleteCities.png) | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settingsUnits.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settingsHelp.png)  | ![](https://raw.githubusercontent.com/suada-haji/Open-Weather-App/master/screenshots/settingsNoInternet.png)  |
| ------ | ------ |------ |------ |------ |------ |

Libraries Used
--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [Room][16] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][31] - Move widgets and transition between screens.
  * [Fragment][34] - A basic unit of composable UI.
  * [Layout][35] - Lay out widgets using different algorithms.
* Third party
  * [Glide][90] for image loading
  * [Kotlin Coroutines][91] for managing background threads with simplified code and reducing needs for callbacks
  * [Retrofit][92] for making asynchoronous network calls
  * [Dagger][93] for passing dependencies from external sources
  * [Lottie AirBnB][94] for rendering after effects animations natively on Android

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[16]: https://developer.android.com/topic/libraries/architecture/room
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[30]: https://developer.android.com/guide/topics/ui
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[90]: https://bumptech.github.io/glide/
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://github.com/square/retrofit
[93]: https://github.com/google/dagger
[94]: https://github.com/airbnb/lottie-android


##### With this app you  will be able to : 
  - See a list of cities you've bookmarked
  - Delete a city/cities
  - Add cities
  - Click on any city to see more details
  - Make sure to turn on your internet 
  
#### License
```sh
Copyright © 2020 Suada Haji

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied.
See the License for the specific language governing permissions and
limitations under the License.
```
