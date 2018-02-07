package com.dawn.httplib.response;

/**
 * Created by wind on 16/8/31 13:07
 */
public class BaseResult<T> {
	public static String OKCODE="0000";
	public int tag;
	public T data;

	public String code;

	public String msg;

	public String action;


	@Override
	public String toString() {
		return "HttpResult{" +
				" data=" + data.getClass() +
				", code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", action='" + action + '\'' +
				'}';
	}
}
