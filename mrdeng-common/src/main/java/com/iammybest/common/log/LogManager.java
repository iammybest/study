package com.iammybest.common.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 日志管理器。
 *
 * @author Sundl
 * @version 1.0, 09/02/17
 */
public final class LogManager {

    private final static LogManager instance = new LogManager();

    public final static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");

    private ArrayList<LogHandle> handles;
    private byte level;

    private LogManager() {
        this.handles = new ArrayList<LogHandle>();
        this.level = LogLevel.DEBUG;
    }

    public static LogManager getInstance() {
        return instance;
    }

    /**
     * 设置日志等级。
     */
    public void setLevel(byte level) {
        this.level = level;
    }

    /**
     * 返回日志等级。
     */
    public byte getLevel() {
        return this.level;
    }

    /**
     * 记录日志。
     */
    public void log(byte level, String tag, String log) {
        synchronized (this) {
            if (this.handles.isEmpty()) {
                //this.addHandle(createSystemOutHandle());
                this.addHandle(createSpringDefaultHandle());
            }

            if (this.level > level) {
                // 过滤日志等级
                return;
            }

            for (LogHandle handle : this.handles) {
                switch (level) {
                    case LogLevel.DEBUG:
                        handle.logDebug(tag, log);
                        break;
                    case LogLevel.INFO:
                        handle.logInfo(tag, log);
                        break;
                    case LogLevel.WARNING:
                        handle.logWarning(tag, log);
                        break;
                    case LogLevel.ERROR:
                        handle.logError(tag, log);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 返回指定名称的处理器。
     *
     * @param name
     * @return
     */
    public LogHandle getHandle(String name) {
        synchronized (this) {
            for (LogHandle handle : this.handles) {
                if (handle.getName().equals(name)) {
                    return handle;
                }
            }
        }

        return null;
    }

    /**
     * 添加日志内容处理器。
     */
    public void addHandle(LogHandle handle) {
        synchronized (this) {
            if (this.handles.contains(handle)) {
                return;
            }

            for (LogHandle h : this.handles) {
                if (h.getName().equals(handle.getName())) {
                    return;
                }
            }

            this.handles.add(handle);
        }
    }

    /**
     * 移除日志内容处理器。
     */
    public void removeHandle(LogHandle handle) {
        synchronized (this) {
            this.handles.remove(handle);
        }
    }

    /**
     * 移除所有日志内容处理器。
     */
    public void removeAllHandles() {
        synchronized (this) {
            this.handles.clear();
        }
    }

    /** 创建 System.out 日志。
     */
    /** public static LogHandle createSystemOutHandle() {
     return new LogHandle() {

     private String name = "DefaultSystemOutHandle";
     private StringBuilder buf = new StringBuilder();

     @Override public String getName() {
     return this.name;
     }

     @Override public void logDebug(String tag, String log) {
     synchronized (buf) {
     buf.append(TIME_FORMAT.format(new Date()));
     buf.append(" [DEBUG] ");
     buf.append(tag);
     buf.append(" ");
     buf.append(log);

     System.out.println(buf.toString());

     buf.delete(0, buf.length());
     }
     }

     @Override public void logInfo(String tag, String log) {
     synchronized (buf) {
     buf.append(TIME_FORMAT.format(new Date()));
     buf.append(" [INFO]  ");
     buf.append(tag);
     buf.append(" ");
     buf.append(log);

     System.out.println(buf.toString());

     buf.delete(0, buf.length());
     }
     }

     @Override public void logWarning(String tag, String log) {
     synchronized (buf) {
     buf.append(TIME_FORMAT.format(new Date()));
     buf.append(" [WARN]  ");
     buf.append(tag);
     buf.append(" ");
     buf.append(log);

     System.out.println(buf.toString());

     buf.delete(0, buf.length());
     }
     }

     @Override public void logError(String tag, String log) {
     synchronized (buf) {
     buf.append(TIME_FORMAT.format(new Date()));
     buf.append(" [ERROR] ");
     buf.append(tag);
     buf.append(" ");
     buf.append(log);

     System.out.println(buf.toString());

     buf.delete(0, buf.length());
     }
     }
     };
     }*/


    /**
     * 创建 spring boot默认 日志。
     */
    public static LogHandle createSpringDefaultHandle() {
        return new LogHandle() {

            private String name = "SpringBootDefaultHandle";
            private StringBuilder buf = new StringBuilder();

            @Override
            public String getName() {
                return this.name;
            }

            @Override
            public void logDebug(String tag, String log) {
                synchronized (buf) {
                    buf.append(TIME_FORMAT.format(new Date()));
                    buf.append(" [DEBUG] ");
                    buf.append(tag);
                    buf.append(" ");
                    buf.append(log);
                    Log logger = LogFactory.getLog(tag);
                    logger.debug(buf.toString());

                    buf.delete(0, buf.length());
                }
            }

            @Override
            public void logInfo(String tag, String log) {
                synchronized (buf) {
                    buf.append(TIME_FORMAT.format(new Date()));
                    buf.append(" [INFO]  ");
                    buf.append(tag);
                    buf.append(" ");
                    buf.append(log);

                    Log logger = LogFactory.getLog(tag);
                    logger.info(buf.toString());

                    buf.delete(0, buf.length());
                }
            }

            @Override
            public void logWarning(String tag, String log) {
                synchronized (buf) {
                    buf.append(TIME_FORMAT.format(new Date()));
                    buf.append(" [WARN]  ");
                    buf.append(tag);
                    buf.append(" ");
                    buf.append(log);

                    Log logger = LogFactory.getLog(tag);
                    logger.warn(buf.toString());

                    buf.delete(0, buf.length());
                }
            }

            @Override
            public void logError(String tag, String log) {
                synchronized (buf) {
                    buf.append(TIME_FORMAT.format(new Date()));
                    buf.append(" [ERROR] ");
                    buf.append(tag);
                    buf.append(" ");
                    buf.append(log);

                    Log logger = LogFactory.getLog(tag);
                    logger.error(buf.toString());

                    buf.delete(0, buf.length());
                }
            }
        };
    }

}
