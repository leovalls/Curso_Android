package com.leovalls.curso_android.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.leovalls.curso_android.data.Comment;
import com.leovalls.curso_android.data.Store;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Utils {
	public static final String ID = "id";
	public static final String STORE = "store";
	public static final String DESCRIPTION = "descripcion";
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";
	public static final String HORARY = "horary";
	public static final String WEB = "web";
	public static final String MAIL = "mail";

	private static final String COMMENT_TYPE_STORE = "store";
	private static final String COMMENT_TYPE_PHOTO = "photo";
	

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
	

	public static List<Store>  getStoresFromParse(Context context){
		parseInitialize(context);
		List<Store> stores = new ArrayList<Store>();
		List<ParseObject> storesList = null;
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Store");
		try {
			storesList = query.find();
			for(ParseObject po : storesList){
				Store store = new Store();
				store.setName(po.getString("name"));
				store.setAddress(po.getString("address"));
				store.setEmail(po.getString("email"));
				store.setHorary(po.getString("horary"));
				store.setLatitude(po.getString("latitude"));
				store.setLongitude(po.getString("longitude"));
				store.setNumFavorites(po.getInt("numFavorites"));
				store.setPhone(po.getString("phone"));
				store.setWebsite(po.getString("website"));
				store.setComments(getComments(context,po.getObjectId(),COMMENT_TYPE_STORE));
				stores.add(store);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return stores;
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
				Comment comment = new Comment();
				comment.setComment(po.getString("comment"));
				comment.setIdObject(idObject);
				comment.setType(commentType);
				
				comments.add(comment);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comments;
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
	
	public static void parseInitialize(Context context){
		Parse.initialize(context, "LDzxUNt6EOYbPNLgfIsQlSe9lsSFMV2V4EBaQaKy", "Wj844jtbrtDDa9sibcfqDxdl9EXkTMwEpDH11ql3");
	}
}
