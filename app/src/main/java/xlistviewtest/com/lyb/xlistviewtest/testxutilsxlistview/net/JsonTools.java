package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.R;

/**
 * Json解析工具类，单例，通过parseObject转换
 */
public class JsonTools {
	private Context context;
	private static JsonTools instance;

	private JsonTools(Context context) {
		this.context = context;
	}

	public static JsonTools getInstance(Context context) {
		if (instance == null) {
			synchronized (JsonTools.class) {
				if (instance == null) {
					synchronized (JsonTools.class) {
						instance = new JsonTools(context);
					}
				}
			}
		}
		return instance;
	}

	public <T> T parseObject(String json, Class<T> clazz) {
		try {
			Log.e("lyb","json");
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, context.getString(R.string.server_return_json_format_error), Toast.LENGTH_SHORT).show();
			return null;
		}
	}
}
