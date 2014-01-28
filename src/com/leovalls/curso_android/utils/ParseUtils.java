package com.leovalls.curso_android.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.leovalls.curso_android.model.Comment;
import com.leovalls.curso_android.model.Photo;
import com.leovalls.curso_android.model.Store;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ParseUtils {

	private static final String COMMENT_TYPE_STORE = "store";
	private static final String COMMENT_TYPE_PHOTO = "photo";

	public static final String APPLICATION_ID = "LDzxUNt6EOYbPNLgfIsQlSe9lsSFMV2V4EBaQaKy";
	public static final String CLIENT_KEY = "Wj844jtbrtDDa9sibcfqDxdl9EXkTMwEpDH11ql3";
	
	public static void parseInitialize(Context context){
		Parse.initialize(context, APPLICATION_ID, CLIENT_KEY);
	}
	
	public static List<Store>  getStoresFromParse(Context context){
		parseInitialize(context);
		List<Store> stores = new ArrayList<Store>();
		List<ParseObject> storesList = null;
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Store");
		try {
			storesList = query.find();
			for(ParseObject po : storesList){
				Store store = ParseObjectToStore(po);
				store.setComments(getComments(context,po.getObjectId(),COMMENT_TYPE_STORE));
				stores.add(store);
			}
		} catch (ParseException e) {
			Log.getStackTraceString(e);
		}
		
		
		return stores;
	}
	
	public static List<Photo>  getPhotosFromParse(Context context){
		parseInitialize(context);
		List<Photo> photos = new ArrayList<Photo>();
		List<ParseObject> storesList = null;
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Photo");
		try {
			storesList = query.find();
			for(ParseObject po : storesList){
				Photo photo = ParseObjectToPhoto(po);
				photo.setComments(getComments(context,po.getObjectId(),COMMENT_TYPE_PHOTO));
				photos.add(photo);
			}
		} catch (ParseException e) {
			Log.getStackTraceString(e);
		}
		
		
		return photos;
	}

	public static List<Comment> getComments(Context context, String idObject, String commentType) {
		parseInitialize(context);
		List<Comment> comments = new ArrayList<Comment>();
		List<ParseObject> commentsList = null;
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Comment");
		query.whereEqualTo("idObject", idObject);
		query.whereEqualTo("type", commentType);
		try {
			commentsList = query.find();
			for(ParseObject po: commentsList){
				Comment comment = ParseObjectToComment(po);
				comments.add(comment);
			}
		} catch (ParseException e) {
			Log.getStackTraceString(e);
		}
		
		return comments;
	}


	public static Store getStoreById(String id, Context context){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Store");
		Store result = null;
		try {
			ParseObject po = query.get(id);
			result = ParseObjectToStore(po);
			result.setComments(getComments(context,po.getObjectId(),COMMENT_TYPE_STORE));
		} catch (ParseException e) {
			Log.getStackTraceString(e);
		}
		
		return result;
	}

	public static void insertParseObcjet(ParseObject po, Context context){
		parseInitialize(context);
		po.saveInBackground();
	} 
	
	private static Store ParseObjectToStore(ParseObject po) {
		Store store = new Store();
		store.setId(po.getObjectId());
		store.setName(po.getString("name"));
		store.setDescription(po.getString("description"));
		store.setAddress(po.getString("address"));
		store.setEmail(po.getString("email"));
		store.setHorary(po.getString("horary"));
		store.setLatitude(po.getString("latitude"));
		store.setLongitude(po.getString("longitude"));
		store.setNumFavorites(po.getInt("numFavorites"));
		store.setPhone(po.getString("phone"));
		store.setWebsite(po.getString("website"));
		
		return store;
	}

	private static Comment ParseObjectToComment(ParseObject po) {
		Comment comment = new Comment();
		comment.setId(po.getObjectId());
		comment.setComment(po.getString("comment"));
		comment.setIdObject(po.getString("idObject"));
		comment.setType(po.getString("type"));
		return comment;
	}
	
	private static Photo ParseObjectToPhoto(ParseObject po) {
		Photo photo = new Photo();
		photo.setId(po.getObjectId());
		photo.setDescription(po.getString("desctription"));
		photo.setUrl(po.getString("url"));
		photo.setNumFavorites(po.getInt("numFavorites"));
		
		ParseFile image = po.getParseFile("image");
		try {
			photo.setImage(image.getData());
		} catch (ParseException e) {
			Log.getStackTraceString(e);
		}

		return photo;
	}
	
	
}
