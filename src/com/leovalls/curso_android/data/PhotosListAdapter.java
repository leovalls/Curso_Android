package com.leovalls.curso_android.data;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.model.Photo;
import com.leovalls.curso_android.utils.Utils;

public class PhotosListAdapter extends BaseAdapter {
    
    
	private List<Photo> data;
	private LayoutInflater inflater = null;
    
	public PhotosListAdapter(Context c, List<Photo> d) {
		this.data = d;
		inflater = LayoutInflater.from(c);
	}    
	
    @Override
    public int getCount() {
            return data.size();
    }

    @Override
    public Object getItem(int position) {
            return data.get(position);
    }

    @Override
    public long getItemId(int position) {
            return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.image_list_item, null);

            holder = new ViewHolder();
            holder.description = (TextView) convertView.findViewById(R.id.imageDescription);
            
            convertView.setTag(holder);                
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
    
    	holder.description.setText(data.get(position).getDescription());
    	
    	Drawable drawable = null;
    	//String url = data.get(position).getUrl();
    	byte[] image = data.get(position).getImage();
    	
//    	if (url != null && !url.equals("")){
//    		drawable = Utils.drawableFromUrl(url);
//    	}else if (image != null) {
		if (image != null) {
    		drawable = Utils.bytesToDrawable(image);
    	}
    
    	if (drawable != null){
    		drawable = Utils.resizeDrawable(drawable);
    		holder.description.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
    	}
    	
        return convertView;
    }
    
    
    static class ViewHolder {
            public TextView description;
    }

}