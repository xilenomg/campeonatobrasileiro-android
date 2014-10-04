package br.com.dataminas.cbrasileiro;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import br.com.dataminas.cbrasileiro.mainFragments.MainArtilhariaFragment;
import br.com.dataminas.cbrasileiro.mainFragments.MainClassificacaoFragment;
import br.com.dataminas.cbrasileiro.mainFragments.MainRodadasFragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class MainActivity  extends ActionBarActivity{
	private AdView adView;
	private static final String AD_UNIT_ID = "ca-app-pub-2766691437061191/9650949868";

	CBPagerAdapter mDemoCollectionPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDemoCollectionPagerAdapter = new CBPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.new_pager);
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.setAdapter(mDemoCollectionPagerAdapter);
		
		
		// Create an ad.
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.SMART_BANNER);
	    adView.setAdUnitId(AD_UNIT_ID);

	    // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
	    layout.addView(adView, 0);

	 // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	        .addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB")
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);
	    
	}


	public static class CBPagerAdapter extends FragmentStatePagerAdapter {

		android.support.v4.app.Fragment fragment, fragment1, fragment2;

		public CBPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int i) {


			switch(i){
			case 0: fragment = new MainRodadasFragment();
			return fragment;

			case 1: fragment1 = new MainClassificacaoFragment();
			return fragment1;

			case 2: fragment2 = new MainArtilhariaFragment();
			return fragment2;

			default: return null;
			}
		}



		@Override
		public CharSequence getPageTitle(int position) {
			switch(position){
			case 0: return "Rodadas";
			case 1: return "Classificação";
			case 2: return "Artilharia";
			default: return "";
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	}

}
