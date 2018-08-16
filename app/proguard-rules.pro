# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class sun.misc.Unsafe { *; }
-keepattributes *Annotation*
-keep class com.google.**
-keep class org.gson.**

-dontwarn com.mongodb.**
-dontwarn org.apache.**
-dontwarn com.squareup.okhttp.**
-dontwarn org.slf4j.**
-dontwarn kotlin.reflect.jvm.internal.**
-dontwarn com.google.**
-dontwarn org.apache.http.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn org.gson.**
-dontwarn org.bson.**
-dontwarn retrofit2.**
-dontwarn org.jetbrains.anko.internals.AnkoInternals

-dontwarn org.seamless.**
-dontwarn org.fourthline.**
-dontwarn java.lang.invoke.**