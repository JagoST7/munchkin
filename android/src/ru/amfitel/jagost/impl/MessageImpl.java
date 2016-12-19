package ru.amfitel.jagost.impl;

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

}
