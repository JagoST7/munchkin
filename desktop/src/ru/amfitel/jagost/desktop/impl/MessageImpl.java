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

	private MessageImpl(){}

	@Override
	public MessageInt parse(String json) {
		return parseStat(json);
	}

	public static MessageInt parseStat(String json) {
		MessageImpl impl = new MessageImpl();
		if (json != null && !json.isEmpty() && !json.equals("{}")) {
			try {
				JsonElement el = new JsonParser().parse(json);
				impl.jsonObject = el.getAsJsonObject();
				return impl;
			} catch (Exception e) {
				//^:(
			}
		}
		impl.jsonObject = new JsonObject();
		return impl;
	}

	@Override
	public void addProperty(String property, String value) {
		jsonObject.addProperty(property, value);
	}

	@Override
	public String getPropAsString(String property) {
		JsonElement el =jsonObject.get(property);
		if(el != null) {
			return el.getAsString();
		}
		return null;
	}

	@Override
	public String toString() {
		return jsonObject.toString();
	}

	//compile "com.google.code.gson:gson:2.2.4"

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
