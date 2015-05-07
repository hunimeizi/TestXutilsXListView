package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtils {
	/**
	 *
	 * @param ctx
	 * @return
	 */
	public static boolean isNetWorkExist(Context ctx) {
		ConnectivityManager conManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = conManager.getAllNetworkInfo();
		for (int i = 0; i < netInfo.length; ++i) {
			if (netInfo[i].isConnected()) {
				return true;
			}
		}
		return false;
	}
}
