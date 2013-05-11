# Beachfront Android SDK usage guide

## Overview
This document details the process of integrating the Beachfront Android A SDK with your Android application. 

## Requirements

* BeachFront IO app id & Ad Unit id
* BeachFront SDK SDK Jar
* Android 1.5 and above
* Android Support Library

## Installation
1. Access the beachfront.io Console and register your application to get your App ID & Ad unit Id;
2. Download the BeachFront Android AD SDK, copy into the /lib folder of your Android Project. Make sure the lib is selected in the Order and Export tab of the Java Build Path panel.
3. Add following required permession in your AndroidManifest.xml

```  
  <uses-permission android:name="android.permission.READ_PHONE_STATE" />
  <uses-permission android:name="android.permission.INTERNET" />
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
  
```
4. Add the BF activity in the application node of AndroidManifesh.xml:

```
<activity
            android:name="com.bfio.ad.BFIOActivity"
            android:configChanges="keyboardHidden|orientation|screenSize" />
```

## Example Code

Following methods can be called from VideoSDK object which is a singleton class and instance of VideoSDK can be get as following:

If you have any questions, don't hesitate to email us at support@beachfrontmedia.com.

## Issues and questions
Have a bug? Please [create an issue on GitHub](https://github.com/actolap/android-sdk-sample/issues)!


