package com.leovalls.curso_android.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

public class Utils {
	public static final String ID = "id";
	public static final String STORE = "store";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "descripcion";
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String HORARY = "horary";
	public static final String WEB = "web";
	public static final String MAIL = "mail";
	

	public static final int FROM_CAMERA = 1;
	public static final int FROM_GALLERY = 2;
	

	public static List<HashMap<String, String>> getStores() {
		List<HashMap<String, String>> stores = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(ID, "1");
		map.put(STORE, "La Librería");
		map.put(DESCRIPTION, "Librería por departamentos");
		map.put(ADDRESS, "Gran Via Les Corts Catalanes, 599, Barcelona");
		map.put(HORARY, " Lun-Vier: 09:00 - 20:00");
		map.put(PHONE, "+34999999999");
		map.put(WEB, "http://www.lalibreria.com");
		map.put(MAIL, "info@lalibreria.com");
		stores .add(map);
		
		map = new HashMap<String, String>();
		map.put(ID, "2");
		map.put(STORE, "PreNatal");
		map.put(DESCRIPTION, "Todo para tu bebé");
		map.put(ADDRESS, "Carrer Carles III, 51, Barcelona");
		map.put(HORARY, "Lun-Sbdo: 10:00 - 22:00");
		map.put(PHONE, "+34888888888");
		map.put(WEB, "http://www.prenatal.com");
		map.put(MAIL, "info@prenatal.com");
		
		stores.add(map);
		map = new HashMap<String, String>();
		map.put(ID, "3");
		map.put(STORE, "Stradivarius");
		map.put(DESCRIPTION, "Moda femenina");
		map.put(ADDRESS, "Carrer de Llobregat, 98, L'Hospitalet");
		map.put(HORARY, "Lun-Sbdo: 09:00 - 20:00");
		map.put(PHONE, "+34777777777");
		map.put(WEB, "http://www.stradivarius.com");
		map.put(MAIL, "info@stradivarius.com");
		
		stores.add(map);
		map = new HashMap<String, String>();
		map.put(ID, "4");
		map.put(STORE, "Springfield");
		map.put(DESCRIPTION, "Moda para todos");
		map.put(ADDRESS, "Carrer de Progress, 105, L'Hospitalet");
		map.put(HORARY, "Lun-Sbdo: 09:00 - 20:00");
		map.put(PHONE, "+34666666666");
		map.put(WEB, "http://www.springfield.com");
		map.put(MAIL, "info@springfield.com");
		
		stores.add(map);
		
		return stores;
	}
	

	public static HashMap<String, String> getStoreById(String id){
		HashMap<String, String> result = null;
		List<HashMap<String, String>> storesList = getStores();
		for(HashMap<String, String> store: storesList){
			if(id.equals(store.get(ID))){
				result = store;
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public static Drawable bytesToDrawable(byte[] bytes){
		return new BitmapDrawable(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
	}
	
	
	@SuppressWarnings("deprecation")
	public static Drawable drawableFromUrl(String url) {
	    Bitmap x = null;

	    HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
		    connection.connect();
		    InputStream input = connection.getInputStream();
		    x = BitmapFactory.decodeStream(input);
		} catch (MalformedURLException e) {
			Log.getStackTraceString(e);
		} catch (IOException e) {
			Log.getStackTraceString(e);
		}

	    return new BitmapDrawable(x);
	}


	@SuppressWarnings("deprecation")
	public static Drawable resizeDrawable(Drawable image) {
		if ((image == null) || !(image instanceof BitmapDrawable)) {
	        return image;
	    }

		Bitmap b = ((BitmapDrawable)image).getBitmap();
	    Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 1000, 500, false);

	    return new BitmapDrawable(bitmapResized);
	}
	
	
	public static File setUpFile(Context context) {
		File albumDir;
		
		String albumName = getApplicationName(context);
		//getExternalStoragePublicDirectory(DIRECTORY_PICTURES);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO){
			albumDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
		} else {
			albumDir = new File(Environment.getExternalStorageDirectory()+"/dcim/" +  albumName);
		}
		albumDir.mkdirs();
		
		String imgFileName = getFileName();
		File image = new File(albumDir + "/" + imgFileName);
		return image;
	}


	public static String getFileName() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
									.format(Calendar.getInstance().getTime());
		
		String imgFileName = "IMG_" + timeStamp + ".jpg";
		return imgFileName;
	}
	
	public static String getApplicationName(Context context) {
	    int stringId = context.getApplicationInfo().labelRes;
	    return context.getString(stringId);
	}
	
	public static Bitmap resizeBitmap(int targetW, int targetH, String photoPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        
		BitmapFactory.decodeFile(photoPath, options);
        
		int height = options.outHeight;
		int width = options.outWidth;
		int scaleFactor = 1;
		
		if (targetW > 0 || targetH > 0) {
		    scaleFactor = Math.min(width/targetW, height/targetH);
		}
		options.inJustDecodeBounds = false;
		options.inSampleSize = scaleFactor;
		options.inPurgeable = true;
		
		return BitmapFactory.decodeFile(photoPath, options);
	}   
	
	public static byte[] bitmapToByteArray(Bitmap bmp){
		if (bmp == null){
			return null;
		}
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		return stream.toByteArray();
	}
}
