package com.leovalls.curso_android.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.fragments.CommunityFragment;
import com.leovalls.curso_android.fragments.StoreImagesFragment;
import com.leovalls.curso_android.fragments.StoresContentFragment;

public class MainActivity extends ActionBarActivity {
	
	private DrawerLayout drawerLayout ;
	private ListView drawerList;
	private String[] drawerOptions;
	private ActionBarDrawerToggle drawerToggle;
	private Fragment[] fragments = new Fragment[] {new StoreImagesFragment(),
													new StoresContentFragment(),
													new CommunityFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);	

		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		
        drawerList = (ListView)findViewById(R.id.left_drawer);
		drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		drawerOptions = getResources().getStringArray(R.array.drawer_options);
		
		drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,drawerOptions));
		drawerList.setItemChecked(0, true);
		drawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		drawerToggle = new ActionBarDrawerToggle(this, 
											drawerLayout, 
											R.drawable.ic_drawer, 
											R.string.drawer_open, 
											R.string.drawer_close){
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				ActivityCompat.invalidateOptionsMenu(MainActivity.this);
			}
			
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				ActivityCompat.invalidateOptionsMenu(MainActivity.this);
			}
		};
		
		drawerLayout.setDrawerListener(drawerToggle);
        
		FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
		tr.add(R.id.contentFrame, fragments[0]);
		tr.add(R.id.contentFrame, fragments[1]);
		tr.add(R.id.contentFrame, fragments[2]);
		tr.hide(fragments[1]);
		tr.hide(fragments[2]);
		tr.commit();
    }
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
        
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setContent(position);
        }
    }     
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == android.R.id.home){
			if(drawerLayout.isDrawerOpen(drawerList)){
				drawerLayout.closeDrawer(drawerList);
			}else {
				drawerLayout.openDrawer(drawerList);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void setContent(Integer index) {
		Log.i("DRAWER", index.toString());
		Fragment toHide1 = null;
		Fragment toHide2 = null;
		Fragment toShow = null;
		ActionBar actionBar = getSupportActionBar();
		switch (index) {
			case 0:
				toShow  = fragments[0];
				toHide1 = fragments[1];
				toHide2 = fragments[2];
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
				break;
			case 1:
				toHide1 = fragments[0];
				toShow  = fragments[1];
				toHide2 = fragments[2];
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				break;
			case 2:
				toHide1 = fragments[0];
				toHide2 = fragments[1];
				toShow  = fragments[2];
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
				break;
		}
		
		FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
		tr.hide(toHide1);
		tr.hide(toHide2);
//		tr.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
		tr.show(toShow);
		tr.commit();
		
		drawerList.setItemChecked(index, true);
		drawerLayout.closeDrawer(drawerList);
	}
    
}
