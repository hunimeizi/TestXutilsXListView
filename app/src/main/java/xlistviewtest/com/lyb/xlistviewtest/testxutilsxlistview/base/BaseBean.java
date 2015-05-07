package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.base;

import java.io.Serializable;

public class BaseBean implements Serializable {

	/**
	 * @Fields serialVersionUID
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 11L;
	private int code;
	private String hint;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

}
