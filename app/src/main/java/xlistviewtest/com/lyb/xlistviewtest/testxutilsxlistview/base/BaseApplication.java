package xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import xlistviewtest.com.lyb.xlistviewtest.testxutilsxlistview.R;


public class BaseApplication extends Application {

    public static DisplayImageOptions options;		// DisplayImageOptions是用于设置图片显示的类
    public static DisplayImageOptions options_head;		// DisplayImageOptions是用于设置图片显示的类
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.default_image)			// 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.default_image)	// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.default_image)		// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)							// 设置下载的图片是否缓存在SD卡中
                        //.displayer(new RoundedBitmapDisplayer(20))	// 设置成圆角图片
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        options_head = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.default_image)			// 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.default_image)	// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.default_image)		// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)							// 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(10))	// 设置成圆角图片
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(3)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        //.writeDebugLogs() // Remove for release app
                .memoryCache(new WeakMemoryCache())
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
