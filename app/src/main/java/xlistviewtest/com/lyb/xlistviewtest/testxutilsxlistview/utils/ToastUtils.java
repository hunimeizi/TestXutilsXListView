package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.R;

/**
 * Toast工具类，下一个Toast提示前会取消掉上一个Toast
 * 需要添加layout\common_mytoast_layout.xml
 * @ClassName: ToastUtils  
 * @author Chenyuanming
 * @date 2014年11月7日 上午9:38:49 
 * @version V1.0
 */
@SuppressLint("InflateParams")
public class ToastUtils {

	private static Handler handler = new Handler(Looper.getMainLooper());

	private static Toast toast = null;

	private static Object synObj = new Object();

	public static void showToast(final Context context, final String msg) {
		showToast(context, msg, Toast.LENGTH_SHORT);
	}

	public static void showToast(final Context context, final int msg) {
		showToast(context, msg, Toast.LENGTH_SHORT);
	}

	public static void showToast(final Context context, final String msg, final int len) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (synObj) {
					if (toast != null) {
						// Android4.0
						// Toast显示问题分析http://blog.csdn.net/sylcc_/article/details/7396452
						if (VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
							toast.cancel();
						}
						((TextView) toast.getView().findViewById(R.id.text)).setText(msg);
						toast.setDuration(len);
					} else {
						toast = Toast.makeText(context, msg, len);
						View layout = LayoutInflater.from(context).inflate(R.layout.common_mytoast_layout, null);// 自定义布局
						toast.setView(layout);
						toast.setGravity(Gravity.CENTER, 0, 300);// 屏幕居中
						((TextView) toast.getView().findViewById(R.id.text)).setText(msg);
						toast.setDuration(len);
					}
					toast.show();
				}
			}
		});
	}

	public static void showToast(final Context context, final int msg, final int len) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (synObj) {
					if (toast != null) {
						// Android4.0
						// Toast显示问题分析http://blog.csdn.net/sylcc_/article/details/7396452
						if (VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
							toast.cancel();
						}
						toast.setDuration(len);
						((TextView) toast.getView().findViewById(R.id.text)).setText(context.getString(msg));
					} else {
						toast = Toast.makeText(context, context.getString(msg), len);
						View layout = LayoutInflater.from(context).inflate(R.layout.common_mytoast_layout, null);// 自定义布局
						toast.setView(layout);
						toast.setGravity(Gravity.CENTER, 0, 300);// 屏幕居中
						((TextView) toast.getView().findViewById(R.id.text)).setText(context.getString(msg));
						toast.setDuration(len);
					}
					toast.show();
				}
			}
		});
	}

}