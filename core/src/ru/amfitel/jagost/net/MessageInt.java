package ru.amfitel.jagost.net;

/**
 * Created by estarcev on 19.12.2016.
 */
public interface MessageInt {

	MessageInt parse(String json);

	void addProperty(String property, String value);

	String getPropAsString(String property);
}
