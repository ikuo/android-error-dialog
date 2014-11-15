# Android Error Dialog
An Android library to show an error dialog with stack trace of a Throwable.

It supports Android SDK version 15+.

## Usage
In [android-sdk-plugin](https://github.com/pfn/android-sdk-plugin) based project:

Add the following to build.sbt:
```
resolvers += "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",

libraryDependencies += aar("com.github.ikuo" % "android-error-dialog_2.10" % "0.2.0-SNAPSHOT")
```

Then catch error and show error dialog as follows:

```scala
import com.github.ikuo.android_error_dialog.ErrorDialogFragment

class MyActivity extends FragmentActivity {
  ...

  def f1: Unit = {
    try {
      ...
    } catch {
      case ex: Throwable => {
        val dialog = ErrorDialogFragment(ex)

        val fm = getFragmentManager
        val ft = fm.beginTransaction
        Option(fm.findFragmentByTag("error_dialog")).map { prev => ft.remove(prev) }
        dialogFragment.show(ft, "error_dialog")
      }
    }
  }
}
```

## Building

```
$ git clone git@github.com:ikuo/android-error-dialog.git
$ cd android-error-dialog
$ android update project -p . -t android-19
$ sbt
> compile
> publish-local
```

## License
Apache License 2.0

Copyright (c) 2014, Ikuo Matsumura
