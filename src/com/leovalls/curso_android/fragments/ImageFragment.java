package com.leovalls.curso_android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leovalls.curso_android.R;

public class ImageFragment extends Fragment{
	public static final String RESOURCE = "drawable";
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_image, container, false);
	    Bundle args = getArguments();
	    
	    ImageView img = ((ImageView) view.findViewById(R.id.imageView));        
	    img.setImageResource(args.getInt(RESOURCE));        
        return view;
    }

}
