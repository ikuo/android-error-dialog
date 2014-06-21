import android.Keys._

android.Plugin.androidBuildAar

name := "android-error-dialog"

organization := "com.github.ikuo"

version := "0.1.2-SNAPSHOT"

scalaVersion := "2.10.3"

minSdkVersion in Android := 15

targetSdkVersion in Android := 19

platformTarget in Android :=
  "android-" + (targetSdkVersion in Android).value.toString

publishTo := Some(Resolver.file("file", new File(Path.userHome.absolutePath + "/.m2/repository")))

pomExtra := (
  <url>https://github.com/ikuo/android-error-dialog</url>
  <licenses>
    <license>
      <name>The Apache Software License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:ikuo/android-error-dialog.git</url>
    <connection>scm:git:git@github.com:ikuo/android-error-dialog.git</connection>
  </scm>
  <developers>
    <developer>
      <id>ikuo</id>
      <name>Ikuo Matsumura</name>
      <url>https://github.com/ikuo/</url>
    </developer>
  </developers>)
