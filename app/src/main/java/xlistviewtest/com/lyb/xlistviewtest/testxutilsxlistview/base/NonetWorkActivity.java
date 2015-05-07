package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.base;

import android.view.View;

import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.R;


public class NonetWorkActivity extends BaseActivity {

	@Override
	protected void findViewById() {

	}

	@Override
	public View setBodyView() {
		return inflate(R.layout.activity_nonetwork);
	}

	@Override
	protected void processLogic() {
		setTitle(getString(R.string.activity_chat_bukeyong));
		setMainBackground(R.drawable.bg_commerce);
		setHeadLeftBackgroundResource(R.drawable.selector_title_back_button);
		//隐藏没网络的提示条
		hideNoNetTitleBar();
	}
	/**
	 * 隐藏没网络的提示条
	 * @MethodName: hideNoNetTitleBar
	 * @author Chenyuanming
	 * @date 2014年10月16日 下午6:13:03
	 */
	private void hideNoNetTitleBar() {
		if (ll_title_netinfo!=null) {
			ll_title_netinfo.setVisibility(View.GONE);
		}
	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void getDataAgain() {

	}

	@Override
	protected void onHeadRightButton(View v) {

	}

}
