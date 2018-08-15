# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android_SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable
-keepattributes Signature
# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose

-dontwarn javax.annotation.**
-dontwarn android.app.**
-dontwarn android.support.**
-dontwarn android.view.**
-dontwarn android.widget.**


-keep class android.support.v7.widget.SearchView { *; }

-keepclasseswithmembernames class * {
    native <methods>;
}



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
-dontwarn com.qtsoftware.qtconnect.model.*


-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**


# for DexGuard only - endable if we need multidex
# -keepresourcexmlelements manifest/application/meta-data@value=GlideModule
