package com.neil.module_lib.util.util;

import android.util.Log;

import com.neil.module_lib.util.config.LibConfig;

/**
 *	日志类
 */
public class LogUtil{
	
	private final static boolean isDebug = LibConfig.Config.isDegug;
	private final static String TAG = "LogUtil";

	/**
	 * 默认tag打印
	 */
	public static void v(String msg){
		v(TAG, msg);
	}
	public static void d(String msg){
		d(TAG, msg);
	}
	public static void i(String msg){
		i(TAG, msg);
	}
	public static void w(String msg){
		w(TAG, msg);
	}
	public static void e(String msg){
		e(TAG, msg);
	}

	/**
	 * 自定义tag打印
	 */
	public static void v(Object tag,String msg){
		log(Log.VERBOSE,getTagName(tag),msg);
	}
	public static void d(Object tag,String msg){
		log(Log.DEBUG,getTagName(tag),msg);
	}
	public static void i(Object tag,String msg){
		log(Log.INFO,getTagName(tag),msg);
	}
	public static void w(Object tag,String msg){
		log(Log.WARN,getTagName(tag),msg);
	}
	public static void e(Object tag,String msg){
		log(Log.ERROR,getTagName(tag),msg);
	}

	//--------------------------------打印日志
	/**
	 * API：	打印控制
	 */
	private static void log(int level ,String tag,String msg){
		if(isDebug){
			Log.println(level,tag,msg);
		}
	}
	private static String getTagName(Object object) {
		if(object instanceof String)
			return (String) object;
		return object.getClass().getSimpleName();
	}
}
