package com.leovalls.curso_android.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.fragments.ImageFragment;

public class StoreImagePagerAdapter extends FragmentPagerAdapter {

	private int[] arrayFlags = new int[]{R.drawable.store1,
											R.drawable.store2,
											R.drawable.store3,
											R.drawable.store4,
											R.drawable.store5};
	
	public StoreImagePagerAdapter(FragmentManager fm) {
        super(fm);
	}
	
	@Override
	public Fragment getItem(int item) {
		Fragment fragment = new ImageFragment();
		Bundle args = new Bundle();
		args.putInt(ImageFragment.RESOURCE, arrayFlags[item]);
		fragment.setArguments(args);
		
		return fragment;                
	}

	
	 @Override
	 public int getCount() {
	         return arrayFlags.length;
	 }

}
