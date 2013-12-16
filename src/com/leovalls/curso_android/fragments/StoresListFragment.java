package com.leovalls.curso_android.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.activity.StoreActivity;
import com.leovalls.curso_android.utils.Utils;

public class StoresListFragment extends Fragment implements OnItemClickListener {
	
	List<HashMap<String,String>> stores = new ArrayList<HashMap<String,String>>();
	ListView storeListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 View view = inflater.inflate(R.layout.fragment_stores_list, container, false);
		storeListView = (ListView)view.findViewById(R.id.list);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    stores = Utils.getStores();	    
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), stores, android.R.layout.simple_list_item_2, 
				new String[]{Utils.STORE, Utils.DESCRIPTION}, 
				new int[]{android.R.id.text1, android.R.id.text2});
		
		storeListView.setAdapter(adapter);
		storeListView.setOnItemClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		Intent intent = new Intent(getActivity(),StoreActivity.class);
		HashMap<String,String> map  =   (HashMap<String, String>) adapterView.getItemAtPosition(position);
		intent.putExtra(Utils.ID, map.get(Utils.ID) );
		startActivity(intent);
		
	}


	

}
