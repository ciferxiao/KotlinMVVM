package com.ggh.baselibrary.utils;

import android.util.Log;


/**
 * 作者：Create by (mcl)
 * 时间：2020/5/11
 * 文件名：LogUtil.java
 * 描述：  日志工具类
 * 		使打印日志变得简单
 * 		自动识别调用日志函数的类名 方法名 与位置
 */

public class LogUtil {
	private static String TAG = "LogUtil-->";

	private static final String TOP_BORDER     = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";
	private static final String LEFT_BORDER    = "║ ";
	private static final String BOTTOM_BORDER  = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";
	private static LogUtil log;
	public static boolean isDebug = true;
	private LogUtil() {}

	/**
	 * 获取调用log方法的名字
	 * @return Name
	 */
	private String getFunctionName() {

		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		if (sts == null) {
			return null;
		}
		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}
			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}
			if (st.getClassName().equals(this.getClass().getName())) {
				continue;
			}

			return "("+ st.getFileName() + ":" + st.getLineNumber() + ")  "
					+ st.getMethodName() + " -->";
		}
		return null;
	}

	public static void i(Object str) {
		printLog(Log.INFO, str);
	}
	public static void i(String tag,Object str) {
		printLog(Log.INFO, tag,str);
	}

	public static void d(Object str) {
		printLog(Log.DEBUG, str);
	}
	public static void d(String tag,Object str) {
		printLog(Log.DEBUG,tag,str);
	}

	public static void v(Object str) {
		printLog(Log.VERBOSE, str);
	}
	public static void v(String tag,Object str) {
		printLog(Log.VERBOSE,tag, str);
	}

	public static void w(Object str) {
		printLog(Log.WARN, str);
	}
	public static void w(String tag,Object str) {
		printLog(Log.WARN, tag,str);
	}

	public static void e(Object str) {
		printLog(Log.ERROR, str);
	}
	public static void e(String tag,Object str) {
		printLog(Log.ERROR,tag, str);
	}

	/**
	 * 调用系统的打印
	 * @param index
	 * @param str
	 */
	private static void printLog(int index, String tag ,Object str) {
		if (!isDebug){
			return;
		}

		if (log == null) {
			log = new LogUtil();
		}
		String name = log.getFunctionName();
		if (name != null) {
			str = name + str;
		}
		switch (index) {
			case Log.VERBOSE:
				Log.v(tag,TOP_BORDER);
				Log.v(tag,LEFT_BORDER+ str.toString());
				Log.v(tag,BOTTOM_BORDER);
				break;
			case Log.DEBUG:
				Log.d(tag,TOP_BORDER);
				Log.d(tag,LEFT_BORDER+ str.toString());
				Log.d(tag,BOTTOM_BORDER);
				break;
			case Log.INFO:
				Log.i(tag,TOP_BORDER);
				Log.i(tag,LEFT_BORDER+ str.toString());
				Log.i(tag,BOTTOM_BORDER);
				break;
			case Log.WARN:
				Log.w(tag,TOP_BORDER);
				Log.w(tag,LEFT_BORDER+ str.toString());
				Log.w(tag,BOTTOM_BORDER);
				break;
			case Log.ERROR:
				Log.e(tag,TOP_BORDER);
				Log.e(tag,LEFT_BORDER+ str.toString());
				Log.e(tag,BOTTOM_BORDER);
				break;
			default:
				break;
		}
	}

	private static void printLog(int index,Object str) {
		if (!isDebug){
			return;
		}

		if (log == null) {
			log = new LogUtil();
		}
		String name = log.getFunctionName();
		if (name != null) {
			str = name + str;
		}
		switch (index) {
			case Log.VERBOSE:
				Log.v(TAG,TOP_BORDER);
				Log.v(TAG,LEFT_BORDER+ str.toString());
				Log.v(TAG,BOTTOM_BORDER);
				break;
			case Log.DEBUG:
				Log.d(TAG,TOP_BORDER);
				Log.d(TAG,LEFT_BORDER+ str.toString());
				Log.d(TAG,BOTTOM_BORDER);
				break;
			case Log.INFO:
				Log.i(TAG,TOP_BORDER);
				Log.i(TAG,LEFT_BORDER+ str.toString());
				Log.i(TAG,BOTTOM_BORDER);
				break;
			case Log.WARN:
				Log.w(TAG,TOP_BORDER);
				Log.w(TAG,LEFT_BORDER+ str.toString());
				Log.w(TAG,BOTTOM_BORDER);
				break;
			case Log.ERROR:
				Log.e(TAG,TOP_BORDER);
				Log.e(TAG,LEFT_BORDER+ str.toString());
				Log.e(TAG,BOTTOM_BORDER);
				break;
			default:
				break;
		}
	}
}
