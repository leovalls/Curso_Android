package com.leovalls.curso_android.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leovalls.curso_android.R;

public class StoresContentFragment extends Fragment implements TabListener {
	
	List<HashMap<String,String>> stores = new ArrayList<HashMap<String,String>>();
	private Fragment[] fragments = new Fragment[] {new StoresListFragment(),
													new MapFragment()};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.store_content_activity, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);	
		
		ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab()
				.setText(getResources().getString(R.string.title_fragment_list))
				.setTabListener(this));
	
		actionBar.addTab(actionBar.newTab()
				.setText(getResources().getString(R.string.title_fragment_map))
				.setTabListener(this));
		

	    FragmentManager manager = getActivity().getSupportFragmentManager();
		manager.beginTransaction()
				.add(R.id.storeContentFrame, fragments[0])
				.add(R.id.storeContentFrame, fragments[1])  
				.hide(fragments[1])
				.commit();
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		setContent(tab.getPosition());
	}

	public void setContent(int tab) {                
		Fragment toHide = null;
		Fragment toShow = null;
		switch (tab) {
			case 0:
				toHide = fragments[1];
				toShow = fragments[0];
				break;
			case 1:
				toHide = fragments[0];
				toShow = fragments[1];
				break;
		}
		
		FragmentManager manager = getActivity().getSupportFragmentManager();
		manager.beginTransaction()
				.hide(toHide)
				.show(toShow)
				.commit();
	}


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
}
