# AndroidHapticFeedbackTest
An app to test haptic feedbacks on Android. If you need to compose haptic feedbacks or vibration effects, this app is a good start.

Note that haptic feedbacks and vibration effects behave dramatically differently on devices from different vendors with Pixel series showing the best behaviors. Be sure to test on the devices that your products will support and this is the main reason why I started this app.

## Read First
There are official Android documents talking about haptics in detail: [Haptics](https://source.android.com/devices/input/haptics). You should read them first to know the basics about haptics and what you can do with those APIs, before using this app to get hands on the actual feeling of the haptics.

There are three ways to orchestrate haptic patterns: 
- **Haptic Feedback**: suitable for input events (like long press, or swipe), or UI elements (like keyboard).
- **OneShot & Waveform**: suitable for more refined vibration patterns that last over time.
- **Composition**: According to the documents, compositions enable stringing together sequences of more nuanced haptics or vibrations. However, none of the devices I tested supports this feature including Pixel 5, so in this app you can only try the primitive haptics instead of composing them.

## Haptics
- Uses [View#performHapticFeedback](https://developer.android.com/reference/android/view/View#performHapticFeedback(int)).
- You can test each one of [HapticFeedbackConstants](https://developer.android.com/reference/android/view/HapticFeedbackConstants).
- You can test hidden haptics using HapticFeedback ID (can be found in the HapticFeedbackConstants.java, such as [this one](https://cs.android.com/android/platform/superproject/+/master:frameworks/base/core/java/android/view/HapticFeedbackConstants.java)).

## OneShot & Waveform
- Uses [VibrationEffect](https://developer.android.com/reference/android/os/VibrationEffect).
- Supports OneShot, Predefined and Waveform effects.

## Composition
- Uses [VibrationEffect.Composition](https://developer.android.com/reference/kotlin/android/os/VibrationEffect.Composition).
- You can test all haptic primitives.

## Some Notes
This app doesn't have any data validation, so the app just crashes if the data can't be parsed. And the code is really a mess as a result of my unfamiliarity with Android development and Kotlin.

## Screenshots
<img src="https://raw.githubusercontent.com/PBBB/AndroidHapticFeedbackTest/master/readme/Haptics.png" width="360">   <img src="https://raw.githubusercontent.com/PBBB/AndroidHapticFeedbackTest/master/readme/OneShotWaveform.png" width="360">   <img src="https://raw.githubusercontent.com/PBBB/AndroidHapticFeedbackTest/master/readme/Composition.png" width="360">