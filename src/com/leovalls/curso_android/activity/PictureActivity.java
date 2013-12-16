package com.leovalls.curso_android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.fragments.CommentsFragment;

public class PictureActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_picture);
	    
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction()
				.add(R.id.fragmentComments, new CommentsFragment())
				.commit();
		
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
                	Uri uri = Uri.parse("android.resource:///com.leovalls.curso_android//drawable//" + R.drawable.images);
                	
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);                                        
                    intent.putExtra(Intent.EXTRA_TEXT, getText(R.string.msg_img_share));
                    intent.putExtra(Intent.EXTRA_STREAM,uri);
                    intent.setType("image/*");
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); 
                    
                    startActivity(Intent.createChooser(intent, getResources().getText(R.string.action_share)));
                    return true;
                default :
                    return super.onOptionsItemSelected(item);
        }                
    }
}


