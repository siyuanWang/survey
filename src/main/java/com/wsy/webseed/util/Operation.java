package com.wsy.webseed.util;


import org.json.JSONArray;
import org.json.JSONObject;

public class Operation {

	private Operation() {
		super();
	}

	public static final int successCode = 1;
	public static final int failCode = 0;
	/**
	 * 
	 * @param resultCode 0 代表错误 1正确 
	 * @param resultMsg 如果0，则返回错误信息
	 * @return
	 */
	public static String result(int resultCode, String resultMsg) {
		JSONObject result = new JSONObject();
		try {
			result.put("resultCode", resultCode);
			result.put("resultMsg", resultMsg);
			result.put("resultList", new JSONArray());
		} catch (Exception e) {
		}
		return result.toString();
	}
	/**
	 * 
	 * @param resultCode 0 代表错误 1正确 
	 * @param resultMsg 如果0，则返回错误信息
	 * @param results 返回对象
	 * @return
	 */
	public static String result(int resultCode, String resultMsg, String results) {
		JSONObject result = new JSONObject();
		try {
			result.put("resultCode", resultCode);
			result.put("resultMsg", resultMsg);
			result.put("resultList", results);
		} catch (Exception e) {
		}
		return result.toString();
	}
}
