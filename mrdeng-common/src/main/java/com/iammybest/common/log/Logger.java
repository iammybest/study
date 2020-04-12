package com.iammybest.common.log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 系统通用日志接口。
 *
 *
 * @version        1.0, 09/02/17
 * @author         Sundl
 */
public final class Logger {

	/** 打印 DEBUG 级别日志。
	 */
	public static void d(Class<?> clazz, String log) {
		LogManager.getInstance().log(LogLevel.DEBUG, clazz.getName(), log);
	}

	/** 打印 INFO 级别日志。
	 */
	public static void i(Class<?> clazz, String log) {
		LogManager.getInstance().log(LogLevel.INFO, clazz.getName(), log);
	}

	/** 打印 WARNING 级别日志。
	 */
	public static void w(Class<?> clazz, String log) {
		LogManager.getInstance().log(LogLevel.WARNING, clazz.getName(), log);
	}

	/** 打印 ERROR 级别日志。
	 */
	public static void e(Class<?> clazz, String log) {
		LogManager.getInstance().log(LogLevel.ERROR, clazz.getName(), log);
	}

	/** 日志管理器是否设置为 DEBUG 等级。
	 */
	public static boolean isDebugLevel() {
		return (LogManager.getInstance().getLevel() == LogLevel.DEBUG);
	}

	/** 记录异常。
	 */
	public static void log(Class<?> clazz, Exception exception, byte level) {
		if (LogManager.getInstance().getLevel() > level) {
			return;
		}

		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			LogManager.getInstance().log(level, clazz.getName() + " Catched exception: ", sw.toString());
		} catch (Exception ie) {
			// Nothing
		} finally {
			try {
				pw.close();
				sw.close();
			} catch (Exception oe) {
				// Nothing
			}
		}
	}
}
