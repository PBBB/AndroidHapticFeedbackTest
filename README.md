# AndroidHapticFeedbackTest
An app to test haptic feedback on Android. If you need to compose haptics feedbacks or vibration effects, this app is a good start.

Note that haptics feedbacks and vibration effects behave dramatically differently on devices from different vendors with Pixel series showing the best behaviors. Be sure to test on the devices that your products will support and this is the main reason why I started this app.

## Haptics

1. Uses [View#performHapticFeedback](https://developer.android.com/reference/android/view/View#performHapticFeedback(int))
2. You can test each one of [HapticFeedbackConstants](https://developer.android.com/reference/android/view/HapticFeedbackConstants)
3. You can test hidden haptics using HapticFeedback ID (can be found in the HapticFeedbackConstants.java, such as [this one](https://cs.android.com/android/platform/superproject/+/master:frameworks/base/core/java/android/view/HapticFeedbackConstants.java))

## Vibrator
1. Uses [Vibrator](https://developer.android.com/reference/android/os/Vibrator)
2. Supports OneShot, Predefined and Waveform [vibration effects](https://developer.android.com/reference/android/os/VibrationEffect).

## Some Notes
This app doesn't have any data validation, so the app just crashes if the data can't be parsed. I'm very new to Android development and Kotlin, and this app is just for testing purposes, so this won't be optimized in the near future.


## Screenshots
<img src="https://raw.githubusercontent.com/PBBB/AndroidHapticFeedbackTest/master/readme/Haptics.png" width="360">   <img src="https://raw.githubusercontent.com/PBBB/AndroidHapticFeedbackTest/master/readme/Vibrator.png" width="360">
