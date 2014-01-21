package com.leovalls.curso_android.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.fragments.CommentsFragment;
import com.leovalls.curso_android.model.Store;
import com.leovalls.curso_android.utils.ParseUtils;
import com.leovalls.curso_android.utils.Utils;

public class StoreActivity extends FragmentActivity{
	
	Store store = new Store ();
	String storeText = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_store);
	    
	    getStoreInfo();

		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction()
				.add(R.id.fragmentComments, new CommentsFragment())
				.commit();
	    
	    Button btnCall = (Button) findViewById(R.id.btnCall); 
	    btnCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:" +store.getPhone()));
					startActivity(intent);
				} catch (ActivityNotFoundException activityException) {
					Toast.makeText(getApplicationContext(), "Error al realizar la llamada", Toast.LENGTH_SHORT).show();
			        Log.e("dialing-example", "Call failed", activityException);
			    }
			}
		});
	    
	}


	public void getStoreInfo() {
		Intent intent = getIntent();
		String id = intent.getStringExtra(Utils.ID);
	    store = ParseUtils.getStoreById(id, this);
	    
	    TextView storeName = (TextView) findViewById(R.id.txtStoreName);
	    storeName.setText(store.getName());
	    storeText += store.getName() + ". ";
	    
	    TextView storeDescription = (TextView) findViewById(R.id.txtStoreDescription);
	    storeDescription.setText(store.getDescription());
	    
	    TextView storeAddress = (TextView) findViewById(R.id.txtStoreAddress);
	    storeAddress.setText(store.getAddress());
	    Linkify.addLinks(storeAddress, Linkify.ALL);
	    storeText += store.getAddress() + ". ";
	    
	    TextView storePhone = (TextView) findViewById(R.id.txtStorePhone);
	    storePhone.setText(store.getPhone());
	    Linkify.addLinks(storePhone, Linkify.ALL);
	    storeText += store.getPhone() + ". ";
	    
	    TextView storeHorary = (TextView) findViewById(R.id.txtStoreHorary);
	    storeHorary.setText(store.getHorary());
	    
	    TextView storeWeb = (TextView) findViewById(R.id.txtStoreWeb);
	    storeWeb.setText(store.getWebsite());
	    Linkify.addLinks(storeWeb, Linkify.ALL);
	    
	    TextView storeMail = (TextView) findViewById(R.id.txtStoreMail);
	    storeMail.setText(store.getEmail());
	    Linkify.addLinks(storeMail, Linkify.ALL);
	}
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);                
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.action_favorite:                                
                    return true;
                case R.id.action_share:
                	
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);   
                    intent.putExtra(Intent.EXTRA_TEXT,String.format(getString(R.string.msg_store_share),storeText));
                    intent.setType("text/plain");
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); 
                    
                    startActivity(Intent.createChooser(intent, getResources().getText(R.string.action_share)));
                    return true;
                default :
                    return super.onOptionsItemSelected(item);
        }                
    }
}
