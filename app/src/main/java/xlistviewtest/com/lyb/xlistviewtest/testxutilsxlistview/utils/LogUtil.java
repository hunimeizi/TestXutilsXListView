package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.utils;

/**
 * Log打印工具类
 * @ClassName: LogCat  
 * @author Chenyuanming
 * @date 2014年11月7日 上午10:01:42 
 * @version V1.0
 */
public class LogUtil {
    public static final boolean LOG = true;
    static final String CHANMAO = "馋猫";
    static final String CHAN = "fef";
    static final String TEST = "test";
    static final String lyb = "lyb";
    static final String Shadow = "Shadow";
    static final String Kyla = "Kyla";
    static final String Hehe = "尼玛";
    static final String Cao_Xiaolong = "Cao_Xiaolong";

    public static void i(String tag, String string) {
	if (LOG)
	    android.util.Log.i(tag, string);
    }

    public static void e(String tag, String string) {
	if (LOG)
	    android.util.Log.e(tag, string);
    }

    public static void d(String tag, String string) {
	if (LOG)
	    android.util.Log.d(tag, string);
    }

    public static void v(String tag, String string) {
	if (LOG)
	    android.util.Log.v(tag, string);
    }

    public static void w(String tag, String string) {
	if (LOG)
	    android.util.Log.w(tag, string);
    }

    public static void EChan(String string) {
	if (LOG)
	    android.util.Log.e(CHANMAO, string);
    }
    public static void hehe(String string) {
    	if (LOG)
    		android.util.Log.e(Hehe, string);
    }
    
    public static void hehe(String string,Exception e) {
    	if (LOG)
    		android.util.Log.e(Hehe, string+":"+e.getMessage());
    }


    public static void Kyla(String string) {
	if (LOG)
	    android.util.Log.e(Kyla, string);
    }

    public static void Shadow(String string) {
	if (LOG)
	    android.util.Log.e(Shadow, string);
    }

    public static void Cao_Xiaolong(String string) {
	if (LOG)
	    android.util.Log.e(Cao_Xiaolong, string);
    }

    public static void lyb(String string) {
	if (LOG)
	    android.util.Log.e(lyb, string);
    }

    public static void tag(String string) {
	if (LOG)
	    android.util.Log.e(CHAN, string);
    }

    public static void test(String string) {
	if (LOG)
	    android.util.Log.e(TEST, string);
    }
}
