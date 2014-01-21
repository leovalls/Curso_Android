package com.leovalls.curso_android.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.data.PhotosListAdapter;
import com.leovalls.curso_android.model.Photo;
import com.leovalls.curso_android.utils.ParseUtils;

public class CommunityFragment extends Fragment {
	
	List<Photo> photos = new ArrayList<Photo>();
	ListView photosListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_community, container, false);
		
		photosListView = (ListView) view.findViewById(R.id.list) ;
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    photos = ParseUtils.getPhotosFromParse(getActivity());	    
	    ListAdapter adapter = new PhotosListAdapter(getActivity() , photos);
	    
	    photosListView.setAdapter(adapter);
	   //photosListView.setOnItemClickListener(this);
	}
}
