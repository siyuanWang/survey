package com.wsy.webseed.util;


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
			result.put("result", resultMsg);
		} catch (Exception e) {
		}
		return result.toString();
	}
}
