package br.com.mr.pathfinder.client.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JsonUtil {
	public static String asString(JSONValue value) {
		if (value != null) {
			return value.isString().stringValue();
		}
		return "";
	}

	public static Boolean asBoolean(JSONValue value) {
		if (value != null) {
			return value.isBoolean().booleanValue();
		}
		return Boolean.FALSE;
	}

	public static JSONArray noSafeEval(String json) {
		return new JSONArray(evalToJavaScriptObject(json));
	}

	public static native JavaScriptObject evalToJavaScriptObject(String json) /*-{
		return eval('(' + json + ')');
	}-*/;

	public static Long asLong(JSONValue jsonValue) {
		JSONNumber number = jsonValue.isNumber();
		Long ret = null;
		if (number != null) {
			ret = new Double(number.doubleValue()).longValue();
		}
		return ret;
	}

	public static Integer asInteger(JSONValue jsonValue) {
		JSONNumber number = jsonValue.isNumber();
		Integer ret = null;
		if (number != null) {
			ret = new Double(number.doubleValue()).intValue();
		}
		return ret;
	}

	@SuppressWarnings("deprecation")
	public static Date asDate(JSONValue jsonValue) {
		JSONNumber date = jsonValue.isNumber();
		Date ret = null;
		try {
			if (date != null) {
				Long time = new Double(date.doubleValue()).longValue();
				ret = new Date(time);
			}
			JSONString dateString = jsonValue.isString();
			if (dateString != null) {
				ret = new Date(dateString.stringValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static JSONValue asJsonNumber(Number number) {
		JSONNumber jsonNumber = null;
		try {
			jsonNumber = new JSONNumber(number.doubleValue());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonNumber;
	}

	public static JSONValue asJsonNumber(Date timestamp) {
		JSONNumber jsonNumber = null;
		try {
			jsonNumber = new JSONNumber(timestamp.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonNumber;
	}

	public static Double asDouble(JSONValue jsonValue) {
		JSONNumber number = jsonValue.isNumber();
		Double ret = null;
		if (number != null) {
			ret = new Double(number.doubleValue());
		}
		return ret;
	}

	public static <T extends JSONValue> JSONValue asJSONValue(List<T> list) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			array.set(i, list.get(i));
		}

		return array;
	}

	public static JSONValue asJsonString(String code) {
		JSONString jsonString = null;
		try {
			jsonString = new JSONString(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	public static <T extends JSONValue> List<T> asList(JSONValue jsonValue) {
		long ini = System.currentTimeMillis();
		List<T> ret = new ArrayList<T>();
		if (jsonValue != null) {
			JSONArray array = jsonValue.isArray();
			if (array != null) {
				for (int i = 0; i < array.size(); i++) {
					ret.add((T) array.get(i));
				}
			}
//			System.out.println("JsonUtil.asList(): " + array.size() + " registros em " + (System.currentTimeMillis() - ini) + " ms");
		}
		return ret;
	}

	public static JSONObject getAsObject(JSONValue jsonValue) {

		return jsonValue.isObject();
	}
}
