## Beachfront Android SDK usage guide

## Overview
This document details the process of integrating the Beachfront AD SDK with your Android application. 

## Requirements

* BeachFront IO app id & Ad Unit id - [Get it from here](http://beachfront.io/join)
* BeachFront SDK Jar
* Android 1.5 and above

## Installation
1. Access the beachfront.io Console and register your application to get your App ID & Ad unit Id;
2. Download the BeachFront Android AD SDK jar, copy into the /lib folder of your Android Project. Make sure the jar files in lib is selected in the Order and Export tab of the Java Build Path panel.
3. Add the BF activity in the application node of AndroidManifesh.xml:

```
	<activity
	android:name="com.bfio.ad.BFIOActivity"
	android:configChanges="keyboardHidden|orientation|screenSize" />
```

* Make sure to have android:targetSdkVersion should be equal or greater then 13 in the manifest

```
 <uses-sdk android:targetSdkVersion="11" />
```

   Add following required permession in your AndroidManifest.xml

```
  <uses-permission android:name="android.permission.READ_PHONE_STATE" />
  <uses-permission android:name="android.permission.INTERNET" />
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />  
```

Once you've completed the above steps, you can start displaying ads in your application by following the simple instructions for Interstitial Ad as below :

In your Activity class (the one from which you want to show the ad), declare a BFIOInterstitial instance variable, register your activity as the interstitial's BFIOInterstitial.InterstitialListener and instantiate it in the onCreate(Bundle savedInstanceState) method.

```
	public class MainActivity extends Activity implements
	BFIOInterstitial.InterstitialListener {

	BFIOInterstitial interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	interstitial = new BFIOInterstitial(MainActivity.this, this);
	}
```

Your application is now ready to display an Interstitial ad, 

Call requestInterstitial method to request an Interstitial ad. You have to pass the appId & adUnitId

```
	interstitial.requestInterstitial("appid", // appID
	"addUnitId"); // adUnitId
```

In case Ad Server find a AD it will return to 
```
	public void onReceiveInterstitial(BFIOInterstitalAd ad);
```

Otherwise will return to:
```
	public void onInterstitialFailed(BFIOErrorCode errorCode);
```

To start the Interstitial Call the following method 
```
	interstitial.showInterstitial(ad);
```

Following are callbacks methods of an Interstitial ad:

```
	/**
	 * On Interstitial Failed
	 * 
	 * @param errorCode
	 */
	 public void onInterstitialFailed(BFIOErrorCode errorCode);

	/**
	 * Interstitial Displaying on the screen
	 * 
	 */
	 public void onInterstitialStarted();

	/**
	 * Interstitial clicked
	 * 
	 */
	 public void onInterstitialClicked();

	/**
	 * Interstitial dismissed
	 * 
	 */
	 public void onInterstitialDismissed();

	/**
	 * Interstitial completed
	 * 
	 */
	 public void onInterstitialCompleted();

	 /**
	  * On Interstitial Received
	  * 
	  */
	 public void onReceiveInterstitial(BFIOInterstitalAd ad);
```

Example Code:
```
public class MainActivity extends Activity implements
		BFIOInterstitial.InterstitialListener {

	private BFIOInterstitial interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		interstitial = new BFIOInterstitial(MainActivity.this, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		interstitial.onDestroy();
		super.onDestroy();
	}

	@Override
	public void onInterstitialFailed(BFIOErrorCode errorCode) {
		Toast.makeText(MainActivity.this, "Interstitial not received",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onInterstitialClicked() {
		Toast.makeText(MainActivity.this, "Interstitial Clicked",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onInterstitialDismissed() {
		Toast.makeText(MainActivity.this, "Interstitial dismissed",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onReceiveInterstitial(BFIOInterstitalAd ad) {
		Toast.makeText(MainActivity.this, "Received interstitial",
				Toast.LENGTH_SHORT).show();
		interstitial.showInterstitial(ad);

	}

	@Override
	public void onInterstitialCompleted() {
		Toast.makeText(MainActivity.this, "Interstitial play completed",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onInterstitialStarted() {
		Toast.makeText(MainActivity.this, "Interstitial started",
				Toast.LENGTH_SHORT).show();

	}

}

```


Have a bug? Please [create an issue on GitHub](https://github.com/beachfront/beachfront-io-android-sdk/issues)!


