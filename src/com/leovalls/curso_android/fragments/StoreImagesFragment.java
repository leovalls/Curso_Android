package com.leovalls.curso_android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.data.StoreImagePagerAdapter;

public class StoreImagesFragment extends Fragment{
	
	ViewPager viewPager;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        StoreImagePagerAdapter adapter = new StoreImagePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);             
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_store_image, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
       
        return view;
    }

}
