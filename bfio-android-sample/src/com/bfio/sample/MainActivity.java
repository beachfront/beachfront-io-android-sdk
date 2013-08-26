package com.bfio.sample;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.bfio.ad.BFIOErrorCode;
import com.bfio.ad.BFIOInterstitial;
import com.bfio.ad.BFIOPreRoll;
import com.bfio.ad.BFIOPreRoll.PreRollAdListener;
import com.bfio.ad.VideoViewIO;
import com.bfio.ad.model.BFIOInterstitalAd;

public class MainActivity extends Activity implements
		BFIOInterstitial.InterstitialListener, OnClickListener,
		PreRollAdListener, OnCompletionListener,
		MediaPlayer.OnPreparedListener, OnErrorListener,
		OnSeekBarChangeListener, OnTouchListener {

	BFIOInterstitial interstitial;

	BFIOPreRoll preRoll;

	VideoViewIO videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.request).setOnClickListener(this);
		findViewById(R.id.request_preroll).setOnClickListener(this);
		videoView = (VideoViewIO) findViewById(R.id.io_video_view);
		videoView.setVisibility(View.VISIBLE);
		videoView.setOnPreparedListener(this);
		videoView.setOnCompletionListener(this);
		videoView.setOnErrorListener(this);
		videoView.setOnTouchListener(this);
		videoView.setMediaController(new MediaController(this));
		videoView.requestFocus();
		String path = "android.resource://" + getPackageName() + "/"
				+ R.raw.sample;
		Uri uri = Uri.parse(path);
		videoView.setVideoURI(uri);
		interstitial = new BFIOInterstitial(MainActivity.this, this);
		// Pre Roll Listener is optional
		preRoll = new BFIOPreRoll(this);
		//Pre Roll Listener can be passed at second param
		//preRoll = new BFIOPreRoll(this, this);
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.request:
			// interactive
			interstitial.requestInterstitial(
					"e04fd6b0-4eb2-4dc8-b8d3-accfb7cf8043", // appID
					"e4471497-53ec-42f8-af58-cba7464d9e5a"); // adUnitId

			// non interactive

			// interstitial.requestInterstitial(
			// "fd298c64-e28b-4a28-f3b6-410d24be3b73", // appID
			// "2f529a17-97ff-4b34-b02d-a75cdaa0f1a9"); // adUnitId
			Toast.makeText(MainActivity.this, "Interstitial request sent",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.request_preroll:
			preRoll.playWithPreRoll("e04fd6b0-4eb2-4dc8-b8d3-accfb7cf8043", // appID
					"e4471497-53ec-42f8-af58-cba7464d9e5a", videoView); // adUnitId
			Toast.makeText(MainActivity.this, "PreRoll request initiated",
					Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}

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

	@Override
	public void onFailed(BFIOErrorCode errorCode) {
		Toast.makeText(MainActivity.this,
				"PreRoll did not filled with due to " + errorCode,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onPreRollStarted() {
		Toast.makeText(MainActivity.this, "Pre Roll Started",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onPreRollClicked() {
		Toast.makeText(MainActivity.this, "Pre Roll Clicked",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onPreRollCompleted() {
		Toast.makeText(MainActivity.this, "Pre Roll Completed",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Toast.makeText(MainActivity.this, "On Error called", Toast.LENGTH_SHORT)
				.show();
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Toast.makeText(MainActivity.this, "On Prepared called",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Toast.makeText(MainActivity.this, "On Completed called",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Toast.makeText(MainActivity.this, "On Touch called", Toast.LENGTH_SHORT)
				.show();
		if (videoView.isPlaying()) {
			videoView.pause();
		} else {
			videoView.start();
		}
		return false;
	}

}
