package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.main;

import android.view.View;

import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.R;
import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.base.BaseActivity;
import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.base.BaseBean;
import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.net.HttpHelperPost;
import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.view.XListView;

/**
 * 所有新写的类继承BaseActivity 实现其方法
 *
 * @author lyb
 */
public class MainTestActivity extends BaseActivity implements XListView.IXListViewListener {

    private int index = 1;
    private XListView xlistView;
    private String[] imagePath = new String[]{
            "http://g.hiphotos.baidu.com/album/w%3D2048/sign=10e27d76adaf2eddd4f14ee9b92800e9/bd315c6034a85edfeb5afd5348540923dc5475ef.jpg",
            "http://d.hiphotos.baidu.com/album/w%3D2048/sign=e5974229adaf2eddd4f14ee9b92800e9/bd315c6034a85edf1e2fc20c48540923dd547579.jpg",
            "http://a.hiphotos.baidu.com/album/w%3D2048/sign=87622c1934fae6cd0cb4ac613b8b0e24/728da9773912b31ba01800078718367adab4e1ac.jpg",
            "http://c.hiphotos.baidu.com/album/w%3D2048/sign=bb64b3117aec54e741ec1d1e8d009a50/574e9258d109b3de06d9da47cdbf6c81800a4cbe.jpg",
            "http://a.hiphotos.baidu.com/album/w%3D2048/sign=abdce0dbb64543a9f51bfdcc2a2f8b82/0b7b02087bf40ad17a1cf9cc562c11dfa9ecce20.jpg",
            "http://b.hiphotos.baidu.com/album/w%3D2048/sign=ecb30bc8902397ddd6799f046dbab3b7/9c16fdfaaf51f3de3d048b4b95eef01f3a29796e.jpg",
            "http://d.hiphotos.baidu.com/album/w%3D2048/sign=0176fc51738b4710ce2ffaccf7f6c2fd/c995d143ad4bd113c827b3425bafa40f4afb05d3.jpg",
            "http://c.hiphotos.baidu.com/album/w%3D2048/sign=1a929103ac4bd11304cdb0326e97a40f/2f738bd4b31c8701738cc796267f9e2f0708ff8c.jpg",
            "http://g.hiphotos.baidu.com/album/w%3D2048/sign=c933feb571cf3bc7e800caece538bba1/e7cd7b899e510fb3a11e752fd833c895d1430c06.jpg",
            "http://a.hiphotos.baidu.com/album/w%3D2048/sign=67b261168882b9013dadc43347b5a977/f3d3572c11dfa9ec225d1c2660d0f703918fc194.jpg"};
    private XListViewAdapter adapter;

    /**
     * findViewById
     */
    @Override
    protected void findViewById() {
        xlistView = (XListView) findViewById(R.id.shot_listview);
    }

    /**
     * 中间容器填充
     *
     * @return
     */
    @Override
    public View setBodyView() {
        return inflate(R.layout.activity_xlist_view_test);
    }

    /**
     * 可设置背景 以及一些操作
     */
    @Override
    protected void processLogic() {
        setMainBackground(R.drawable.zuixinjiaoyi_text_f);
        setHeadLeftBackgroundResource(R.drawable.title_back);
        setHeadRightBackgroundResource(R.drawable.title_more);

        xlistView.setXListViewListener(this, 0);
        xlistView.setPullLoadEnable(false);//不可加载更多
//        xlistView.setPullRefreshEnable(false);//该方法不可下拉刷新
        xlistView.setRefreshTime();
        adapter = new XListViewAdapter(this, imagePath);
        xlistView.setAdapter(adapter);
        rebindPhoneNum();
    }

    /**
     * 控件的监听事件
     */
    @Override
    protected void setListener() {

    }

    /**
     * 网络发生变化时会调用该方法
     */
    @Override
    protected void getDataAgain() {

    }

    /**
     * title 右边按钮的点击事件
     *
     * @param v
     */
    @Override
    protected void onHeadRightButton(View v) {

    }

    private void rebindPhoneNum() {
        String url = "http://api.thefuture.city/Labor/SignIn";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("userid", 1 + ""));
        params.add(new BasicNameValuePair("source", "701718813905"));
        params.add(new BasicNameValuePair("time", "1426585993"));
        params.add(new BasicNameValuePair("language", "zh"));
        params.add(new BasicNameValuePair("token", "867253010821145"));
        params.add(new BasicNameValuePair("ip", "219.142.213.138"));
        params.add(new BasicNameValuePair("device", "HTT MQ9"));
        params.add(new BasicNameValuePair("sysversion", "4.2.2"));
        params.add(new BasicNameValuePair("imei", "867253010821145"));
        params.add(new BasicNameValuePair("softversion", "V1.1.1-dev"));
        params.add(new BasicNameValuePair("sign", "5JL18BA9LM0E7387F02A"));
        HttpHelperPost.Post(context, url, params,
                new RequestCallBack<String>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        // 可添加请稍后的对话框 显示
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        // 请稍后的对话框 取消

                        // basebean 只是个例子 只要返回的是个json字符串 建立好json字符串里的数据javabean
                        // 自己的javabean的名字更换basebean 可直接拿到里面的数据
                        // 这样 fastjson就已经帮你解析好了 不必要 手动解析 这是fastjson的最大好处
                        // 唯一一点 javabean 一定要符合 json字符串返回的数据类型 什么数组型什么单纯的数据 等等
                        BaseBean bean = jsonTools.parseObject(
                                responseInfo.result, BaseBean.class);
                        if (bean != null) {// 该方法对其做异常处理 防止程序崩溃

                        }
//                        xlistView.stopRefresh();
//                        xlistView.stopLoadMore();
//                        if(shotModel.dataList.size() >= size) {
//                            xlistView.setPullLoadEnable(false);
//                        } else {
//                            xlistView.setPullLoadEnable(true);
//                        }
//                        if(shopAdapter == null) {
//                            shopAdapter = new ShotAdapter(this, shotModel.dataList);
//                            xlistView.setAdapter(shopAdapter);
//                        } else {
//                            shopAdapter.notifyDataSetChanged();
//                        }
                    }

                    @Override
                    public void onFailure(
                            com.lidroid.xutils.exception.HttpException error,
                            String msg) {
                        // 请稍后的对话框 取消
                        // 接口报错 不通等 都走这个方法
                    }
                });
    }

    @Override
    public void onRefresh(int id) {
        index = 1;
        rebindPhoneNum();
        xlistView.setRefreshTime();
    }

    @Override
    public void onLoadMore(int id) {
        index++;
        rebindPhoneNum();
    }
}
