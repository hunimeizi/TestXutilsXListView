package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.R;
import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.base.BaseApplication;

public class XListViewAdapter extends BaseAdapter {

	private Context context;
	private String[] list;
	private LayoutInflater inflater;
	public XListViewAdapter(Context context, String[] list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return list.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.shot_list_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.shot_item_image);
			holder.head = (ImageView) convertView.findViewById(R.id.shot_item_head);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(list[position], holder.head, BaseApplication.options_head);
		ImageLoader.getInstance().displayImage(list[position], holder.image, BaseApplication.options_head);
		return convertView;
	}
	
	class ViewHolder{
		ImageView head;
		ImageView image;
	}

}
