package com.leovalls.curso_android.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.activity.StoreActivity;
import com.leovalls.curso_android.data.StoresListAdapter;
import com.leovalls.curso_android.model.Store;
import com.leovalls.curso_android.utils.ParseUtils;
import com.leovalls.curso_android.utils.Utils;

public class StoresListFragment extends Fragment implements OnItemClickListener {
	
	List<Store> stores = new ArrayList<Store>();
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
	    
	    stores = ParseUtils.getStoresFromParse(getActivity());	    
	    
	    ListAdapter adapter = new StoresListAdapter(getActivity() , stores );
	    
		storeListView.setAdapter(adapter);
		storeListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
		Intent intent = new Intent(getActivity(),StoreActivity.class);
		Store store = (Store) adapterView.getItemAtPosition(position);
		intent.putExtra(Utils.ID, store.getId());
		startActivity(intent);
		
	}

}
