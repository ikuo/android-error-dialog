import android.Keys._

android.Plugin.androidBuild

name := "android-error-dialog"

minSdkVersion in Android := 15

targetSdkVersion in Android := 19

platformTarget in Android :=
  "android-" + (targetSdkVersion in Android).value.toString
