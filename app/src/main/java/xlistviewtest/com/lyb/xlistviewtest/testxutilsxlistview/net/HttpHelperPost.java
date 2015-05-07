package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.net;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.NameValuePair;

import java.io.File;
import java.util.List;

import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.R;

public class HttpHelperPost {
	/***
	 * @param context
	 * @param url
	 * @param params
	 * @param callBack
	 * @param file
	 */
	public static void Post(Context context, String url,
			List<NameValuePair> params, RequestCallBack<?> callBack,
			File... file) {
		if (!NetUtils.isNetWorkExist(context)) {
			callBack.onFailure(null,
					context.getString(R.string.activity_title_未连接到互联网));
			return;
		}
		HttpUtils httpUtils = new HttpUtils();
		RequestParams requestParams = new RequestParams();
		requestParams.addQueryStringParameter(params);
		httpUtils.configResponseTextCharset("UTF_8");
		Log.e("lyb","访问url：" + url + params.toString().trim().replace(", ", "&").replace("[", "?").replace("]", ""));
		httpUtils.send(HttpRequest.HttpMethod.POST, url, requestParams, callBack);
	}

}
