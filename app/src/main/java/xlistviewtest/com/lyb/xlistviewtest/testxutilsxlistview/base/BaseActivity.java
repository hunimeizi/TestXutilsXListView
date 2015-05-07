package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.R;
import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.net.JsonTools;
import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.net.NetUtils;
import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.utils.AppUtils;

public abstract class BaseActivity extends Activity implements
        OnClickListener {
    protected Context context;
    // protected BaseApplication app;
    // 布局转换器
    protected LayoutInflater inflater;
    // protected UMengUtils uMengUtils;
    protected JsonTools jsonTools;
    protected BaseApplication app;

    public static Map<String, String> foregroundActivityMap = new HashMap<String, String>();
    private Button btn_title_netinfo;
    protected View ll_title_netinfo;
    // 中间容器
    protected View rl_main_frame;
    protected LinearLayout frame_body_content;
    // 标题全局控件
    protected RelativeLayout title_layout;
    // 页面标题
    private TextView title;
    // 标题左边的按钮
    protected Button btn_left;
    // 标题右边的按钮
    protected Button btn_right;
    protected Intent intent;
    protected View view;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private static final int DEFAULT_TEXT_SIZE = 15;

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        initBase();
    }

    /**
     * 初始化资源layout，初始化布局加载器，初始化imgLoader，Activity入栈，查找控件，设置监听
     *
     * @MethodName: initBase
     * @Description:
     * @author lyb
     * @date 2015年3月16日 下午4:21:11
     */
    private void initBase() {
        context = this;
        inflater = LayoutInflater.from(this);
        // 启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        jsonTools = JsonTools.getInstance(context);
        app = (BaseApplication) getApplication();
        initTitleView();
    }

    private void initTitleView() {
        View frameView = inflater.inflate(R.layout.demo_main_frame, null);
        // 标题导航的控件
        title_layout = (RelativeLayout) frameView
                .findViewById(R.id.title_layoutmain);
        btn_left = (Button) frameView.findViewById(R.id.btn_left);
        btn_right = (Button) frameView.findViewById(R.id.btn_right);
        title = (TextView) frameView.findViewById(R.id.title_text);
        btn_title_netinfo = (Button) frameView
                .findViewById(R.id.btn_title_netinfo);
        ll_title_netinfo = frameView.findViewById(R.id.ll_title_netinfo);
        // 中间容器
        rl_main_frame = frameView.findViewById(R.id.rl_main_frame);
        frame_body_content = (LinearLayout) frameView
                .findViewById(R.id.frame_body_content);
        frame_body_content.removeAllViews();
        view = setBodyView();
        if (view != null) {
            frame_body_content.addView(view);
            View rootView = frame_body_content.getChildAt(0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    frame_body_content.getLayoutParams().height);
            rootView.setLayoutParams(params);
        }
        setContentView(frameView);
        findViewById();
        processLogic();
        setListener();
        setBaseListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        foregroundActivityMap.put(this.getClass().getName(), "");
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangeReceiver, mFilter);
    }
    private BroadcastReceiver netWorkChangeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (foregroundActivityMap.size() > 0) {

                // 应用不在后台
                String action = intent.getAction();
                if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                    checkNetInfo(context);
                }
            }
        }
    };
    private void checkNetInfo(Context context) {
        if (ll_title_netinfo != null) {
            if (!NetUtils.isNetWorkExist(context)) {
                if (!AppUtils.isActivityForeground(context, NonetWorkActivity.class.getName())) {
                    // 不是NonetWorkActivity的时候才显示
                    ll_title_netinfo.setVisibility(View.VISIBLE);
                }
            } else {
                ll_title_netinfo.setVisibility(View.GONE);
                getDataAgain();
            }
            RefreshHomeView();
        }
    }
    private void setBaseListener() {
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_title_netinfo.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        foregroundActivityMap.remove(this.getClass().getName());
        super.onStop();
    }

    /**
     * 启动新的Activity并且关闭当前页面
     *
     * @param clazz
     * @MethodName: startActivityAndFinishThis
     * @author lyb
     * @date 2015年3月16日 下午4:21:32
     */
    @SuppressWarnings("rawtypes")
    public void startActivityAndFinishThis(Class clazz) {
        Intent intent = new Intent(getApplicationContext(), clazz);
        startActivity(intent);
        finish();
    }

    /**
     * 启动Activity并且不关闭当前页面
     *
     * @param clazz
     * @MethodName: startActivityAndDonotFinishThis
     * @author lyb
     * @date 2015年3月16日 下午4:21:41
     */
    @SuppressWarnings("rawtypes")
    public void startActivityAndDonotFinishThis(Class clazz) {
        Intent intent = new Intent(getApplicationContext(), clazz);
        startActivity(intent);
    }

    /**
     * 启动新的Activity并且关闭当前页面
     *
     * @param intent
     * @MethodName: startActivityAndFinishThis
     * @Description:
     * @author lyb
     * @date 2015年3月16日 下午4:21:48
     */
    public void startActivityAndFinishThis(Intent intent) {
        startActivity(intent);
        finish();
    }

    protected void setframe_body_contentGrative(int gravity) {
        frame_body_content.setGravity(gravity);

    }

    protected void setMainBackground(int resid) {
        try {
            rl_main_frame.setBackgroundResource(resid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(netWorkChangeReceiver);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Receiver not registered")) {
                // Ignore this exception. This is exactly what is desired
            } else {
                // unexpected, re-throw
                throw e;
            }
        }
    }

    protected void setframe_body_contentLayoutparams(LinearLayout.LayoutParams layoutParams) {
        this.frame_body_content.setLayoutParams(layoutParams);
    }

    /**
     * 通过泛型查找控件
     *
     * @param viewID
     * @param <T>
     * @return
     */
    public <T> T $(int viewID) {
        return (T) findViewById(viewID);
    }

    /**
     * 通过泛型查找控件
     *
     * @param viewID
     * @param <T>
     * @return
     */
    public <T> T $(View view, int viewID) {
        return (T) view.findViewById(viewID);
    }

    /**
     * find控件
     */
    protected abstract void findViewById();

    /**
     * 设置中间容器View
     *
     * @return
     */
    public abstract View setBodyView();

    /**
     * 数据处理
     */
    protected abstract void processLogic();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 重新获取数据
     */
    protected abstract void getDataAgain();

    protected void RefreshHomeView() {

    }

    ;

    /**
     * 设置标题
     */
    @Override
    public void setTitle(CharSequence title) {
        this.title.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        this.title.setText(titleId);
    }

    /**
     * 左边按钮返回功能
     *
     * @param v
     * @MethodName: onHeadLeftButton
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:36:32
     */
    protected void onHeadLeftButton(View v) {
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    protected abstract void onHeadRightButton(View v);

    /**
     * 设置标题导航左边文本内容, <br>
     * 调用本方法之前会 <br>
     * 1.自动让按钮可见； <br>
     * 2.自动增加边框； <br>
     * 3.设置按钮默认字体大小
     *
     * @param text
     * @MethodName: setHeadLeftText
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:36:38
     */
    protected void setHeadLeftText(CharSequence text) {
        setHeadLeftVisibility(View.VISIBLE);
        setHeadLeftBackgroundResource(R.drawable.selector_title_back_button);
        setHeadLeftTextSize(DEFAULT_TEXT_SIZE);
        btn_left.setText(text);
    }

    /**
     * 设置标题导航左边文本内容 <br>
     * 调用本方法之前会 <br>
     * 1.自动让按钮可见； <br>
     * 2.自动增加边框； <br>
     * 3.设置按钮默认字体大小
     *
     * @param resid
     * @MethodName: setHeadLeftText
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:36:38
     */
    protected void setHeadLeftText(int resid) {
        setHeadLeftVisibility(View.VISIBLE);
        setHeadLeftBackgroundResource(R.drawable.selector_title_back_button);
        setHeadLeftTextSize(DEFAULT_TEXT_SIZE);
        btn_left.setText(resid);
    }

    /**
     * 设置标题导航左边文本内容 <br>
     * 调用本方法之前会 <br>
     * 1.自动让按钮可见； <br>
     * 2.自动增加边框；
     *
     * @param textSize
     * @MethodName: setHeadLeftText
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:36:38
     */
    protected void setHeadLeftTextSize(float textSize) {
        setHeadLeftVisibility(View.VISIBLE);
        setHeadLeftBackgroundResource(R.drawable.selector_title_back_button);
        btn_left.setTextSize(textSize);
    }

    /**
     * 设置标题导航右边文本内容 <br>
     * 调用本方法之前会 <br>
     * 1.自动让按钮可见； <br>
     * 2.自动增加边框； <br>
     * 3.设置按钮默认字体大小
     *
     * @param text
     * @MethodName: setHeadRightText
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:37:02
     */
    protected void setHeadRightText(CharSequence text) {
        setHeadRightVisibility(View.VISIBLE);
        setHeadRightBackgroundResource(R.drawable.selector_border_title_button);
        setHeadRightTextSize(DEFAULT_TEXT_SIZE);
        btn_right.setText(text);
    }

    /**
     * 设置标题导航右边文本内容 <br>
     * 调用本方法之前会 <br>
     * 1.自动让按钮可见； <br>
     * 2.自动增加边框； <br>
     * 3.设置按钮默认字体大小
     *
     * @param resid
     * @MethodName: setHeadRightText
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:37:34
     */
    protected void setHeadRightText(int resid) {
        setHeadRightVisibility(View.VISIBLE);
        setHeadRightBackgroundResource(R.drawable.selector_border_title_button);
        setHeadRightTextSize(DEFAULT_TEXT_SIZE);
        btn_right.setText(resid);
    }

    /**
     * 设置右边按钮上的字体 <br>
     * 调用本方法之前会 <br>
     * 1.自动让按钮可见； <br>
     * 2.自动增加边框；
     *
     * @param size
     * @MethodName: setHeadRightTextSize
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:37:50
     */
    protected void setHeadRightTextSize(float size) {
        setHeadRightVisibility(View.VISIBLE);
        setHeadRightBackgroundResource(R.drawable.selector_border_title_button);
        btn_right.setTextSize(size);
    }

    protected void setHeadRightCenter_Vertical() {
        btn_right.setGravity(Gravity.CENTER_VERTICAL);
    }

    /**
     * 设置左边按钮背景
     *
     * @param resid
     * @MethodName: setHeadLeftBackgroundResource
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:38:03
     */
    protected void setHeadLeftBackgroundResource(int resid) {
        try {
            setHeadLeftVisibility(View.VISIBLE);
            btn_left.setBackgroundResource(resid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得右边按钮
     *
     * @return
     * @MethodName: getBtn_right
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:38:14
     */
    public Button getBtn_right() {
        return btn_right;
    }

    /**
     * 设置右边按钮
     *
     * @param btn_right
     * @MethodName: setBtn_right
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:38:36
     */
    public void setBtn_right(Button btn_right) {
        this.btn_right = btn_right;
    }

    /**
     * 设置左边按钮可见性
     *
     * @param visibility
     * @MethodName: setHeadLeftVisibility
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:38:45
     */
    protected void setHeadLeftVisibility(int visibility) {
        btn_left.setVisibility(visibility);
    }

    protected void setHeadRightBackgroundResource(int resid) {
        try {
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setBackgroundResource(resid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    protected void setHeadRightBackDrawable(Drawable drawable) {
        try {
            btn_right.setBackgroundDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setHeadRightVisibility(int visibility) {
        btn_right.setVisibility(visibility);
    }

    protected void setHeadRightenble(boolean flage) {
        btn_right.setEnabled(flage);
    }

    /**
     * 设置标题全局控件是否可见
     *
     * @param visibility
     * @MethodName: setTitleLayoutVisibility
     * @author Chenyuanming
     * @date 2014年9月22日 下午1:39:14
     */
    protected void setTitleLayoutVisibility(int visibility) {
        title_layout.setVisibility(visibility);
    }

    public boolean checkApp(String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            if (info != null) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    public int getScreenHeight() {
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int getScreenWidth() {
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取EditText中的内容
     *
     * @param et
     * @return
     * @MethodName: getEditTextContent
     * @author Chenyuanming
     * @date 2014年10月9日 下午3:39:05
     */
    public String getEditTextContent(EditText et) {
        return et.getText().toString().trim();
    }
    /**
     * 填充布局
     *
     * @param resid
     * @MethodName: inflate
     * @author Chenyuanming
     * @date 2014年9月30日 下午3:21:59
     */
    public View inflate(int resid) {
        return inflater.inflate(resid, null);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                onHeadLeftButton(v);
                break;
            case R.id.btn_right:
                onHeadRightButton(v);
                break;
            case R.id.btn_title_netinfo:
                startActivityAndDonotFinishThis(NonetWorkActivity.class);
                break;
        }
    }
}
