package com.apptodia.cityguide.parser;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.apptodia.cityguide.extra.PrintLog;
import com.apptodia.cityguide.extra.PskHttpRequest;
import com.apptodia.cityguide.holder.AllCityMenu;
import com.apptodia.cityguide.model.CityMenuList;

import android.content.Context;

public class CityMenuParser {
	public static boolean connect(Context con, String url)
			throws JSONException, IOException {

		// String result = GetText(con.getResources().openRawResource(
		// R.raw.get_participants));

		String result = "";
		try {
			result = PskHttpRequest.getText(PskHttpRequest
					.getInputStreamForGetRequest(url));
		} catch (final URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (result.length() < 1) {
			return false;
			// Log.e("result is ", "parse " + result);
		}

		// Log.w("log result is menu sectionparsing", result);

		// Log.e(" result is ", "parse " + result);

		AllCityMenu.removeAll();

		final JSONObject catObject = new JSONObject(result);

		CityMenuList menuData;

		JSONArray resultArray = catObject.getJSONArray("results");

		for (int i = 0; i < resultArray.length(); i++) {
			final JSONObject resultObject = resultArray.getJSONObject(i);
			menuData = new CityMenuList();

			try{
			JSONArray photoArray = resultObject.getJSONArray("photos");
			
			
			for (int j = 0; j < photoArray.length(); j++) {
			
				final JSONObject photoObject = photoArray.getJSONObject(j);
				
				try{
				menuData.setPhotoReference(photoObject
						.getString("photo_reference"));
				PrintLog.myLog("photo_reference : ", menuData
						.getPhotoReference()
						+ "");
				

			} catch (Exception e) {
				// TODO: handle exception
			}
			
			}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				menuData.setIcon(resultObject.getString("icon"));
				PrintLog.myLog("icon : ", menuData.getIcon() + "");
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				menuData.setReference(resultObject.getString("reference"));
				PrintLog.myLog("reference : ", menuData.getReference() + "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				menuData.setName(resultObject.getString("name"));
				PrintLog.myLog("Name of token : ", menuData.getName() + "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				menuData.setRating(resultObject.getString("rating"));
				PrintLog.myLog("Rating: ", menuData.getRating() + "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				menuData.setVicinity(resultObject
						.getString("vicinity"));
				PrintLog.myLog("Name of formatted_address : ", menuData
						.getVicinity()
						+ "");
			} catch (Exception e) {
				// TODO: handle exception
			}

			AllCityMenu.setCityMenuList(menuData);
			menuData = null;
		}
		return true;
	}

}
