package srs.Layer.wmts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.WorkerThread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

import srs.Utility.Log;
import srs.Utility.Network;
import srs.Utility.WMTS;

public class ImageDownLoader {

	protected static boolean IsStop = false;

	public static void StartThread(){
		IsStop = false;
	}

	public static void StopThread(){
		IsStop = true;
	}

	public static boolean IsStop(){
		return IsStop;
	}

	/**
	 * 下载Image的线程池
	 */
	private static ExecutorService ImageThreadPool = null;

	public ImageDownLoader(){}

	public static void creatThreadPool(int count){
		synchronized(ExecutorService.class){
			if(ImageThreadPool != null){
				ImageThreadPool.shutdownNow();
				ImageThreadPool = null;
			}
			if(count>0) {
				ImageThreadPool = Executors.newFixedThreadPool(count);
			}
		}
	}

	/**
	 * 获取线程池的方法，因为涉及到并发的问题，我们加上同步锁
	 * @return
	 */
	public static ExecutorService getThreadPool(){
		if(ImageThreadPool == null){
			creatThreadPool(30);
		}
		return ImageThreadPool;
	}

	/**
	 * 添加Bitmap到内存缓存
	 * @param key
	 * @param bitmap
	 */
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null && bitmap != null) {
			ImageUtils.Caches.put(key, bitmap);
		}
	}

	/**
	 * 从内存缓存中获取一个Bitmap
	 * @param key
	 * @return
	 */
	public Bitmap getBitmapFromMemCache(String key) {
		return ImageUtils.Caches.get(key);
	}

	/**多线程下载矢量
	 *
	 */
	public void downloadTiles2SDCRAD(final int IndexOfThread,final List<String> urls,final List<String> keys, final Handler handler){
		getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				if(!IsStop()){
					Bitmap bitmap = null;
					String url = "";
					String key = "";
					int i = IndexOfThread;
					while(i<urls.size()){
						url = urls.get(i);
						key = keys.get(i);
						bitmap = getBitmapFormUrl(url,0);
						if(bitmap!=null){
							//保存在SD卡或者手机目录
							try {
								ImageUtils.SaveBitmap(bitmap,key);
								Log.i("WMTSLOAD", "瓦片SD卡保存成功， 从SD卡获取" + key);
								Message msg = new Message();
								msg.arg1 = ImageUtils.DOWNLOAD_TILE_SUCCESS;
								msg.getData().putString("KEY", key);
								handler.sendMessage(msg);
							} catch (Exception e) {
								Message msg = new Message();
								msg.arg1 = ImageUtils.DOWNLOAD_TILE_FAILED;
								msg.getData().putString("KEY", key);
								handler.sendMessage(msg);
								Log.e("WMTSLOAD", "瓦片获取成功，  机身保存失败" + key);
								e.printStackTrace();
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}else{
							Message msg = new Message();
							msg.arg1 = ImageUtils.DOWNLOAD_TILE_FAILED;
							msg.getData().putString("KEY", key);
							handler.sendMessage(msg);
							Log.i( "WMTSLOAD", "瓦片获取失败！" + key);
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						//准备下一张瓦片的索引
						i+=ImageUtils.DOWNLOAD_THREAD_COUNT;
					}
				}
			}
		});
		return ;
	}


	/**
	 * 根据url从服务器下载多个瓦片
	 * @param urls 瓦片下载地址
	 * @param keys 瓦片保存位置
	 * @param handler
	 * @return
	 */
	public void downloadImages(final Context context, final List<String> urls, final List<String> keys, final Handler handler){

		ImageDownLoader.StartThread();
		if (urls.size() == 0 || keys.size() == 0 || urls.size() != keys.size()) {
			Log.i("LEVEL-ROW-COLUMN", "下载清单为0无需下载");
			return;
		}
		if (!Network.isNetworkAvailable(context)){
			Message msg = new Message();
			msg.arg1 = WMTS.H_DOWNLOAD_NONETWORK;
			handler.sendMessage(msg);
			Log.i("LEVEL-ROW-COLUMN", "无网络连接无法下载！");
			return;
		}
		String url = "";//下载地址
		String key = "";//存储位置
		for (int i = 0; i < urls.size(); i++) {
			if(!ImageDownLoader.IsStop()) {
				url = urls.get(i);
				key = keys.get(i);
				Bitmap bitmap = getBitmapFormUrl(url, 0);
				if (key != null && bitmap != null) {
					//保存在SD卡或者手机目录
					try {
						ImageUtils.SaveBitmap(bitmap, key);
						Log.i("LEVEL-ROW-COLUMN", key + "：瓦片下载成功、保存成功");
					} catch (Exception e) {
						Log.e("LEVEL-ROW-COLUMN", key + "：瓦片获取成功，  机身保存失败");
						e.printStackTrace();
					}
					Message msg = new Message();
					msg.arg1 = WMTS.H_DOWNLOAD_PROCESS;
					msg.getData().putString(WMTS.H_DOWNLOAD_TILE_KEY, key);
					msg.getData().putInt(WMTS.H_DOWNLOAD_PROGRESS, i);
					msg.getData().putInt(WMTS.H_DOWNLOAD_SUM, urls.size());
					handler.sendMessage(msg);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if (key != null && bitmap == null) {
					Message msg = new Message();
					msg.arg1 = WMTS.H_DOWNLOAD_PROCESS;
					msg.getData().putString(WMTS.H_DOWNLOAD_TILE_KEY, key);
					msg.getData().putInt(WMTS.H_DOWNLOAD_PROGRESS, i);
					msg.getData().putInt(WMTS.H_DOWNLOAD_SUM, urls.size());
					handler.sendMessage(msg);
					Log.i("LEVEL-ROW-COLUMN", key + "：瓦片获取失败！");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else{
				Log.i("LEVEL-ROW-COLUMN", "瓦片下载被中途取消！");
				Message msg = new Message();
				msg.arg1 = WMTS.H_DOWNLOAD_CANCEL;
				msg.getData().putInt(WMTS.H_DOWNLOAD_PROGRESS, i-1);
				msg.getData().putInt(WMTS.H_DOWNLOAD_SUM, urls.size());
				handler.sendMessage(msg);
				return;
			}
		}
		Message msg = new Message();
		msg.arg1 = WMTS.H_DOWNLOAD_COMPLETE;
		msg.getData().putInt(WMTS.H_DOWNLOAD_PROGRESS, urls.size());
		msg.getData().putInt(WMTS.H_DOWNLOAD_SUM, urls.size());
		handler.sendMessage(msg);
		Log.i("LEVEL-ROW-COLUMN", "瓦片全部下载完成！");
	}

	/**
	 * 先从内存缓存中获取Bitmap,如果没有就从SD卡或者手机缓存中获取，SD卡或者手机缓存
	 * 没有就去下载
	 * @param url
	 * @param listener
	 * @return
	 */
	public void downloadImage(final String url, final String key, final onImageLoaderListener listener,final Handler handler){

		getThreadPool().execute(new Runnable() {

			@Override
			public void run() {
				if(!IsStop()){
					Bitmap bitmap = getBitmapFormUrl(url,0);
					if(key!=null&&bitmap!=null){
						//保存在SD卡或者手机目录
						try {
							ImageUtils.SaveBitmap(bitmap,key);
							Log.i( "LEVEL-ROW-COLUMN", "瓦片机身获取、保存成功" + key);
						} catch (Exception e) {
							Log.e( "LEVEL-ROW-COLUMN", "瓦片获取成功，  机身保存失败" + key);
							e.printStackTrace();
						}
						//将Bitmap 加入内存缓存
						ImageUtils.Caches.put(key, bitmap);
						Message msg = new Message();
						msg.arg1 = 4;
						msg.getData().putString("KEY", key);
						handler.sendMessage(msg);
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else if(key!=null&&bitmap==null){
						Message msg = new Message();
						msg.arg1 = 4;
						msg.getData().putString("KEY", key);
						handler.sendMessage(msg);
						Log.i( "LEVEL-ROW-COLUMN", "瓦片获取失败！" + key);
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		return ;
	}

	/**
	 * 获取Bitmap, 内存中没有就去手机或者sd卡中获取，这一步在getView中会调用，比较关键的一步
	 * @param url
	 * @return
	 * @throws IOException
	 *//*
	public Bitmap showCacheBitmap(String url){
		if(getBitmapFromMemCache(url) != null){
			return getBitmapFromMemCache(url);
		}else if(fileUtils.isFileExists(url) && fileUtils.getFileSize(url) != 0){
			//从SD卡获取手机里面获取Bitmap
			Bitmap bitmap = fileUtils.getBitmap(url);
			//将Bitmap 加入内存缓存
			addBitmapToMemoryCache(url, bitmap);
			return bitmap;
		}
		return null;
	}*/


	/**解压GZIPInputStream
	 * @param gzipis 数据流
	 * @return
	 * @throws IOException
	 */
	public static byte[] unzip(GZIPInputStream gzipis) throws IOException{
		int len = 1024;
		int slen = 0;
		ByteArrayOutputStream out= new ByteArrayOutputStream(len);
		byte[] buf = new byte[len];
		while((len = gzipis.read(buf))>0){
			out.write(buf, 0, len);
			slen+=len;
		}
		gzipis.close();
		out.close();
		return out.toByteArray();
	}

	/**
	 * 从Url中获取Bitmap
	 * @param url
	 * @return
	 */
	@WorkerThread
	private Bitmap getBitmapFormUrl(String url,int times) {
		Bitmap img = null;
		HttpURLConnection con =null;
		InputStream is = null;
		try {
			//			url = "http://114.242.219.8:81/rest/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=layer_id&STYLE=default&TILEMATRIXSET=matrix_id&TILEMATRIX=6&TILEROW=21&TILECOL=103&FORMAT=image%2Fjpeg";
			URL mImageUrl = new URL(url);
			con = (HttpURLConnection) mImageUrl.openConnection();
			con.setConnectTimeout(2*1000);
			con.setReadTimeout(2*1000);
			is = con.getInputStream();
			if(is instanceof GZIPInputStream){
				GZIPInputStream gzipis = (GZIPInputStream)is;
				byte[] cbt = unzip(gzipis);
				img = BitmapFactory.decodeByteArray(cbt, 0, cbt.length);
				Log.i( "LEVEL-ROW-COLUMN", "瓦片下载成功！");
			}else{
				img = BitmapFactory.decodeStream(is);
				Log.i( "LEVEL-ROW-COLUMN", "瓦片下载成功！");
			}
		} catch (Exception e) {
			Log.e( e.getMessage(), url);
			e.printStackTrace();
			if (con != null) {
				con.disconnect();
			}
			//3次请求后若仍无回应，则放弃
			if(times<3){
				times++;
				img = getBitmapFormUrl(url,times);
			}else if(times==3&&img==null){
				Log.i( "LEVEL-ROW-COLUMN", "瓦片下载失败！");
			}
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return img;
	}

	/**
	 * 取消正在下载的任务
	 */
	public static synchronized void cancelTask() {
		if(ImageThreadPool != null){
			ImageThreadPool.shutdownNow();
			ImageThreadPool = null;
		}
	}

	/**
	 * 异步下载图片的回调接口
	 * @author len
	 *
	 */
	public interface onImageLoaderListener{
		void onImageLoader(Bitmap bitmap, String url);
	}
}

