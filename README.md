# Android Error Dialog
An Android library to show error dialog with stack trace (Throwable).

## Setup
```
$ git clone git@github.com:ikuo/android-error-dialog.git
$ cd android-error-dialog/
$ sbt
> publish
```

## Usage
In [android-sdk-plugin](https://github.com/pfn/android-sdk-plugin) based project:

Add the following to build.sbt:
```
resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"

libraryDependencies += aar("com.github.ikuo" % "android-error-dialog_2.10" % "0.1.3-SNAPSHOT")
```

# License
Apache License 2.0

Copyright (c) 2014, Ikuo Matsumura
