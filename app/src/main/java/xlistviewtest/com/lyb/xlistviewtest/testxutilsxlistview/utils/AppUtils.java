package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import java.util.List;

/**
 * 
 * @ClassName: AppUtils
 * @Description: 判断程序是否在前台运行
 * @author shadow
 * @date 2014年6月16日 下午2:19:39
 * @version V1.0
 */
public class AppUtils {
	/**
	 * 检查后台的server是否运行，通过输入server name
	 *
	 * @param context
	 * @param className
	 * @return boolean
	 */
	public static boolean isServiceRunning(Context context, String className) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) ||serviceList.get(i).service.getClass().getSimpleName().equals(className) ) {
				return  true;
			}
		}
		return false;
	}

	public static boolean isActivityForeground(Context context, String className) {
		String packageName = getPackageName(context);
		String topActivityClassName = getTopActivityName(context);
		System.out.println("packageName=" + packageName + ",topActivityClassName=" + topActivityClassName);
		if (topActivityClassName.equals(className)) {
            LogUtil.lyb("niha");
			return true;
		} else {
			if (packageName != null && topActivityClassName != null && topActivityClassName.endsWith(className)) {
                LogUtil.lyb("nihaoaaaaa");
				System.out.println("---> isRunningForeGround");
				return true;
			} else {
                LogUtil.lyb("n");
				System.out.println("---> isRunningBackGround");
				return false;
			}
		}
	}

	public static String getTopActivityName(Context context) {
		String topActivityClassName = "";
        try {
            ActivityManager activityManager = (ActivityManager) (context.getSystemService(Context.ACTIVITY_SERVICE));
            List<RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);
            if (runningTaskInfos != null) {
                ComponentName f = runningTaskInfos.get(0).topActivity;
                topActivityClassName = f.getClassName();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return topActivityClassName;
	}
	/***
	 * 获取版本名称
	 *
	 * @param c
	 * @return
	 */
	public static String getVerName(Context c) {
		try {
			PackageInfo packageInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "未知版本";
	}

	/**
	 * 获取应用程序包名
	 *
	 * @param context
	 * @return String
	 */
	public static String getPackageName(Context context) {
		String packageName = context.getPackageName();
		return packageName;
	}



    /**
     * 判断一个程序是否显示在前端,根据测试此方法执行效率在11毫秒,无需担心此方法的执行效率
     *
     * @param packageName 程序包名
     * @param context 上下文环境
     * @return true---&gt;在前端,false---&gt;不在前端
     */
    public static boolean isApplicationShowing(String packageName, Context context) {
        boolean result = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
        if (appProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : appProcesses) {
                if (runningAppProcessInfo.processName.equals(packageName)) {
                    int status = runningAppProcessInfo.importance;
                    if (status == RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 程序是否在前台运行
     * @return
     */
    public static boolean isAppOnForeground(Context context, String packageName) {
        // Returns a list of application processes that are running on the
        // device
        ActivityManager activityManager = (ActivityManager) (context.getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    /**
     * 程序是否在运行
     *
     * @return
     */
    public static boolean isAppRunning(Context context, String packageName) {
        // Returns a list of application processes that are running on the
        // device
        ActivityManager activityManager = (ActivityManager) (context
                .getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    || appProcess.importance == RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                return true;
            }
        }

        return false;
    }

    /**
     * @MethodName: isAppInstalled 判断某个包名的应用是否已经安装了
     * @param c
     * @param packagename
     *            OtherPackageName
     * @return
     * @author 馋猫
     * @date 2014年7月16日 下午6:04:37
     */
    public static boolean isAppInstalled(Context c, String packagename) {
        PackageManager pm = c.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

}
