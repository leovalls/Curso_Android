package com.leovalls.curso_android.data;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.model.Store;

public class StoresListAdapter extends BaseAdapter {
    
    
	private List<Store> data;
	private LayoutInflater inflater = null;
    
	public StoresListAdapter(Context c, List<Store> d) {
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
            convertView = inflater.inflate(R.layout.store_list_item, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.storeName);
            holder.description = (TextView) convertView.findViewById(R.id.storeDescription);
            holder.webSite = (TextView) convertView.findViewById(R.id.storeWeb);
            
            convertView.setTag(holder);                
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
    
    	holder.name.setText(data.get(position).getName());
    	holder.description.setText(data.get(position).getDescription());
    	holder.webSite.setText(data.get(position).getWebsite());
    
        return convertView;
    }
    
    
    static class ViewHolder {
            public TextView name;
            public TextView description;
            public TextView webSite;
    }

}