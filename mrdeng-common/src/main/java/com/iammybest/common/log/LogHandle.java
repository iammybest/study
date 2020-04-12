package com.iammybest.common.log;

/**
 * 日志操作器。
 *
 *
 * @version        1.0, 09/02/17
 * @author         Sundl
 */
public interface LogHandle {

	/** 返回日志句柄名称。
	 */
	public String getName();

	/** 记录 DEBUG 记录。
	 */
	public void logDebug(String tag, String log);

	/** 记录 INFO 记录。
	 */
	public void logInfo(String tag, String log);

	/** 记录 WARNING 记录。
	 */
	public void logWarning(String tag, String log);

	/** 记录 ERROR 记录。
	 */
	public void logError(String tag, String log);
}
