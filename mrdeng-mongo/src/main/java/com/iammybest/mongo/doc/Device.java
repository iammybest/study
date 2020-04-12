package com.iammybest.mongo.doc;

import net.cellcloud.common.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用于描述用户登录的终端类型。
 */
public final class Device {

	public static enum Type {
		/**
		 * 浏览器。
		 */
		Browser(100),

		/**
		 * Windows 桌面。
		 */
		Windows(201),

		/**
		 * MacOS 桌面。
		 */
		MacOS(205),

		/**
		 * Android 设备。
		 */
		Android(501),

		/**
		 * iOS 设备。
		 */
		iOS(502),

		/**
		 * 服务器。
		 */
		Server(601),

		/**
		 * 未知设备。
		 */
		Unknown(900);

		private int code = -1;

		Type(int code) {
			this.code = code;
		}

		public int getCode() {
			return this.code;
		}

		/**
		 * 按照名称解析设备类型。
		 * @param name
		 * @return
		 */
		public static Type parse(String name) {
			String lcname = name.toLowerCase();
			if (lcname.equals("android")) {
				return Type.Android;
			}
			else if (lcname.equals("ios")) {
				return Type.iOS;
			}
			else if (lcname.equals("windows")) {
				return Type.Windows;
			}
			else if (lcname.equals("macos")) {
				return Type.MacOS;
			}
			else if (lcname.equals("netscape") || lcname.indexOf("microsoft") >= 0) {
				return Type.Browser;
			}
			else if (lcname.equals("server")) {
				return Type.Server;
			}
			else {
				return Type.Unknown;
			}
		}
	}

	private JSONObject data;
	private String name;
	private String version;
	private String platform;
	private String userAgent;
	private String deviceToken;

	private Type type;

	private int hashCode = 0;

	public Device() {
		this.name = "Server";
		this.version = "1.0";
		this.platform = System.getProperty("os.name");
	}

	public Device(JSONObject data) {
		this.data = data;
		try {
			this.name = this.data.getString("name");
			this.platform = this.data.getString("platform");

			if (!this.data.has("version")) {
				Logger.w(this.getClass(), "Device '" + this.name + "' [" + this.platform + "] no 'version'");
				this.version = "unknown";
			}
			else {
				this.version = this.data.getString("version");
			}

			if (this.data.has("userAgent")) {
				this.userAgent = this.data.getString("userAgent");
			}

			if (this.data.has("deviceToken")) {
				this.deviceToken = this.data.getString("deviceToken");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		this.type = Type.parse(this.name);
	}

	public Type getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public String getVersion() {
		return this.version;
	}

	public String getPlatform() {
		return this.platform;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public String getToken() {
		return this.deviceToken;
	}

	public void updateToken(String token) {
		this.deviceToken = token;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("name", this.name);
			json.put("version", this.version);
			json.put("platform", this.platform);
			if (null != this.userAgent) {
				json.put("userAgent", this.userAgent);
			}
			if (null != this.deviceToken) {
				json.put("deviceToken", this.deviceToken);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public JSONObject toCompactJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("name", this.name);
			json.put("version", this.version);
			json.put("platform", this.platform);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String toString() {
		return this.toJSON().toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Device) {
			Device d = (Device) other;
			if (d.name.equals(this.name)
				&& d.platform.equals(this.platform)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int hashCode() {
		if (0 == this.hashCode) {
			this.hashCode = (this.name + this.platform).hashCode();
		}

		return this.hashCode;
	}

}
