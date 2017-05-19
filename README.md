## Guide

Beachfront.io is the easist way monetize your app using video Ads. This document shows you how to integrate Beachfront.io into your Android app.

## What You'll Need

* BeachFront IO app id - [Get it from here](http://beachfront.io/join)
* BeachFront SDK Jar - [Get it from here](https://github.com/beachfront/beachfront-io-android-sdk/tree/master/bfio-android-sdk)

## Supported Platforms
* Android Phone & Tablets having android 1.5 or above
* Google TV

## Installing the SDK
1. [Get a Beachfront.io account](http://beachfront.io/join) if you don't already have one.
2. Login to the dashboard and create a new app.
3. Click 'Edit App' and you will see your App ID (copy for later).
4. Download the SDK and copy the .jar into the /lib folder of your Android Project. 
5. For Eclipe Users: Make sure the jar files in the lib folder are selected in the 'Order and Export' tab of the Java Build Path panel.
3. Add an Activity in the application node of AndroidManifest.xml:

```
	<activity
	android:name="com.bfio.ad.BFIOActivity"
	android:configChanges="keyboardHidden|orientation|screenSize" />
```

* Make sure android:targetSdkVersion is at least 13 in the manifest

```
 <uses-sdk android:targetSdkVersion="13" />
```

* Add the following required permissions in your AndroidManifest.xml

```
  <uses-permission android:name="android.permission.READ_PHONE_STATE" />
  <uses-permission android:name="android.permission.INTERNET" />
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />  
```


## Showing interstitial Ads
Wherever you want to show an interstitial ad, declare a BFIOInterstitial instance variable, register your activity as the interstitial's BFIOInterstitial.InterstitialListener, and instantiate the instance in the onCreate(Bundle savedInstanceState) method, as shown below:

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

Your application is now ready to display an Interstitial ad. Now when you are ready to show the Ad, call the requestInterstitial method, passing in your appId.

```
interstitial.requestInterstitial("appid"); 
```

If an Ad is available it will call back to

```
public void onReceiveInterstitial(BFIOInterstitalAd ad);
```

If no Ad is available it will call back to

```
public void onInterstitialFailed(BFIOErrorCode errorCode);
```

To actually show the returned Ad call
 
```
interstitial.showInterstitial(ad);
```

## Callback Events
Here are all available callback methods:

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

## Examples
Check out the sample app included in the SDK bundle -- it contains examples of requesting an app and listening for callback events. And here is a simple sample as well:

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

## Showing Pre Roll Ads

Wherever you want to show an Pre Roll Video ad, declare a BFIOPreRoll instance variable and instantiate the instance in the onCreate(Bundle savedInstanceState) method, as shown below:

```
	public class MainActivity extends Activity  {

	BFIOPreRoll preRoll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	preRoll = new BFIOPreRoll(this);
	}
```

If you want to see the pre roll call back events. You can instantiate in a differen way :

```
	public class MainActivity extends Activity implements
	PreRollAdListener  {

	BFIOPreRoll preRoll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	preRoll = new BFIOPreRoll(this, this);
	}
```
You need to use IO Video View to show pre roll video ads. IO Video View in an android custom view & can be declare in layout xml files : 

```
 	<com.bfio.ad.VideoViewIO
        android:id="@+id/io_video_view"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent" />
```

IO Video View can also build programmatically using java code:

```
  VideoViewIO videoViewIO = new VideoViewIO(context);
```

Your application is now ready to display an Pre Roll ad. Now when you are ready to play a video with pre roll, call the playWithPreRoll method, passing in your appId, adUnitId & IOVideoView:

```
 // Set the url of actual video
 ioVideoView.setVideoURI(uri);
 preRoll.playWithPreRoll("<appID>", ioVideoView); 
```

In case of sucessfull fill, it will play the pre roll first then the actual video, otherwise will play the actual video.

## Callback Events
Here are all available callback methods:

```
	/**
	 * PreRoll did not found
	 * 
	 * @param errorCode
	 */
	public void onFailed(BFIOErrorCode errorCode);

	/**
	 * PreRoll Ad started
	 * 
	 */
	public void onPreRollStarted();

	/**
	 * PreRoll clicked
	 * 
	 */
	public void onPreRollClicked();

	/**
	 * PreRoll completed
	 * 
	 */
	public void onPreRollCompleted();

```

## Showing In-Feed Ads
Wherever you want to show an In-Feed Video ad, declare a BFIOInFeedAdapter instance variable and instantiate the instance, as shown below:

```
	public class MainActivity extends Activity  {

	BFIOInFeedAdapter bfioInFeedAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	bfioInFeedAdapter = new BFIOInFeedAdapter(this, originalAdapter, adPosition,”appId”);
	}
```

Wrap your listView adaptor with BFIOInFeedAdapter.

In case, you want to clear all content of the original Adapter use the following code :

```
		bfioInFeedAdapter.clear();
		bfioInFeedAdapter.notifyDataSetChanged();
```


## Support


Have an issue? Please [contact us](mailto:udit@beachfrontmedia.com) or [create an issue on GitHub](https://github.com/beachfront/beachfront-io-android-sdk/issues)

