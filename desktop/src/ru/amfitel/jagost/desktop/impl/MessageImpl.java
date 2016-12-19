package ru.amfitel.jagost.desktop.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.amfitel.jagost.net.MessageInt;

/**
 * Created by estarcev on 19.12.2016.
 */
public class MessageImpl implements MessageInt {

	private JsonObject jsonObject;

	@Override
	public MessageInt parse(String json) {
		JsonElement el = new JsonParser().parse(json);
		if(el != null) {
			MessageImpl impl = new MessageImpl();
			impl.jsonObject = el.getAsJsonObject();
			return impl;
		}
		return null;
	}

	//compile "com.google.code.gson:gson:2.2.4"
	public static JsonObject stringToJson(String inStr) {
		JsonElement el = new JsonParser().parse(inStr);
		if(el != null) {
			return el.getAsJsonObject();
		}
		return null;
	}

	public static String paramAsString(JsonObject json, String name) {
		JsonElement el = json.get(name);
		if(el != null) {
			return el.getAsString();
		}
		return null;
	}

	public static Integer paramAsInteger(JsonObject json, String name) {
		JsonElement el = json.get(name);
		if(el != null) {
			return el.getAsInt();
		}
		return null;
	}

	public static Boolean paramAsBoolean(JsonObject json, String name) {
		JsonElement el = json.get(name);
		return el != null && el.getAsBoolean();
	}


}
