package com.leovalls.curso_android.fragments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.leovalls.curso_android.R;
import com.leovalls.curso_android.data.PhotosListAdapter;
import com.leovalls.curso_android.model.Photo;
import com.leovalls.curso_android.utils.ParseUtils;
import com.leovalls.curso_android.utils.Utils;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

public class CommunityFragment extends Fragment {

	
	private String photoPath;
	
	List<Photo> photos = new ArrayList<Photo>();
	ListView photosListView;
	ImageButton takePhoto ;
	PhotosListAdapter adapter;
	
//	@Override
//	protected void onCreate(Bundle arg0) {
//		super.onCreate(arg0);
//		
//		setContentView(R.layout.fragment_community);
//		photosListView = (ListView) findViewById(R.id.list) ;
//		takePhoto = (ImageButton) findViewById(R.id.takePhoto);
//		
//	    //photos = ParseUtils.getPhotosFromParse(getActivity());	    
//		photos = ParseUtils.getPhotosFromParse(this);
//	    //ListAdapter adapter = new PhotosListAdapter(getActivity() , photos);
//		adapter = new PhotosListAdapter(this , photos);
//	    photosListView.setAdapter(adapter);
//	    
//	    takePhoto.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
////			    PopupMenu popup = new PopupMenu(getActivity(), takePhoto);  
//				PopupMenu popup = new PopupMenu(CommunityFragment.this, takePhoto);
//	            popup.getMenuInflater().inflate(R.menu.take_photo_menu, popup.getMenu());  
//	           
//	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
//		            public boolean onMenuItemClick(MenuItem item) {  
//			            choosePhotoSource(item.getItemId());  
//			            return true;  
//		            }  
//	            });  
//	            
//	            popup.show();
//				
//			}
//		});
//	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_community, container, false);
		
		photosListView = (ListView) view.findViewById(R.id.list) ;
		takePhoto = (ImageButton) view.findViewById(R.id.takePhoto);
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    photos = ParseUtils.getPhotosFromParse(getActivity());	    
	    ListAdapter adapter = new PhotosListAdapter(getActivity() , photos);
	    photosListView.setAdapter(adapter);
	    
	    takePhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				PopupMenu popup = new PopupMenu(getActivity(), takePhoto);  
	            popup.getMenuInflater().inflate(R.menu.take_photo_menu, popup.getMenu());  
	           
	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
		            public boolean onMenuItemClick(MenuItem item) {  
			            choosePhotoSource(item.getItemId());  
			            return true;  
		            }  
	            });  
	            
	            popup.show();
				
			}
		});
	}
	


	private void choosePhotoSource(int itemId) {
		Intent intent = null;
		int code = 0;
		
		switch (itemId) {
			case R.id.from_camera:
				File photo = Utils.setUpFile(getActivity());
				photoPath = photo.getAbsolutePath();
				
				intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
				intent.putExtra("photoPath", photoPath);
				
				code = Utils.FROM_CAMERA;
				break;
			
			case R.id.from_gallery:
				intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				
				code = Utils.FROM_GALLERY;
				break;
			
			default:
				break;
		}
		
		intent.putExtra("requestCode", code);
		startActivityForResult(intent, code);
		
	}
//	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode) {
		case Utils.FROM_CAMERA:
			if (resultCode == Activity.RESULT_OK){
				//fromCamera();
				saveImage(photoPath);
			}
			break;
		case Utils.FROM_GALLERY:
			if (resultCode == Activity.RESULT_OK){
				fromGallery(data);
			}
			break;
		default:
			break;
		}
		
	}
	
//	private void fromCamera(){
//		Bitmap bitmap = Utils.resizeBitmap(500, 500,photoPath);
//		byte[] byteArray = Utils.bitmapToByteArray(bitmap);
//		
//		saveImage(photoPath);
//	}

	private void fromGallery(Intent data){
		if (data != null){
			Uri selectedImage = data.getData();
			String[] filePathColum = {MediaStore.Images.Media.DATA};
			
			Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColum, null, null, null);
			if (cursor.moveToFirst()){
				int columnIndex = cursor.getColumnIndex(filePathColum[0]);
				String imgPath = cursor.getString(columnIndex);
				cursor.close();
				
				//byte[] byteArray = Utils.bitmapToByteArray(BitmapFactory.decodeFile(imgPath));
				saveImage(imgPath);
			}
		}
	}
	
	
	private void saveImage(String path) {
		Bitmap bitmap = Utils.resizeBitmap(500, 500,path);
		byte[] byteArray = Utils.bitmapToByteArray(bitmap);
		
		ParseFile file = new ParseFile(Utils.getFileName(), byteArray);
		try {
			file.save();
		} catch (ParseException e) {
			Log.getStackTraceString(e);
		}
		ParseObject po = new ParseObject("Photo");
		po.put("image", file);
		
		ParseUtils.insertParseObcjet(po, getActivity());
		
		adapter.notifyDataSetChanged();
	}
	
}
