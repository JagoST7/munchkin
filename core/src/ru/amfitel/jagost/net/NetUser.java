package ru.amfitel.jagost.net;

/**
 * Created by estarcev on 23.12.2016.
 */
public class NetUser {

	public enum Status {CONNECTED, DISCONNECTED}

	private final String stUID;
	private int hash = -1;
	private Status status = Status.DISCONNECTED;
	private MessageInt userInfo;

	public NetUser(String uid) {
		this.stUID = uid;
	}

	public String getUID() {
		return stUID;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}

	public int getHash(){
		return hash;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setInfo(MessageInt info) {
		userInfo = info;
		userInfo.addProperty(CommunicationInt.PR_USER_UID, stUID);
		userInfo.addProperty(CommunicationInt.PR_USER_NAME, "UsEr NaMe todo"); //// FIXME: 23.12.2016 
	}

	public MessageInt getInfo() {
		return userInfo;
	}
}
