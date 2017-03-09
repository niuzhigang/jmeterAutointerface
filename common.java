package org.tn.qa.automation.keyword;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class common {
	public static String UrlEncode(String result) throws UnsupportedEncodingException {
		StringBuffer urle = new StringBuffer();
		String[] results = result.split("&");
		int i = 0;
		for (String tempStr : results) {
			int start = tempStr.indexOf("{");
			int end = tempStr.lastIndexOf("}") + 1;
			// 要urlencode的字符串组合
			String str = tempStr.substring(start, end);
			String key = tempStr.replace(str, "");
			// urlencode的消息体
			String data = URLEncoder.encode(str, "UTF-8");
			if (i > 0) {
				urle.append("&");
			}
			urle.append(key);
			urle.append(data);
			i++;
		}
		return urle.toString();
	}
	
	private static boolean isConnection(String url) {
		int counts = 0;
		if (null == url || url.length() <= 0) {
			return false;
		}
		while (counts < 10) {
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				int state = connection.getResponseCode();
				if (state == 200) {
					return true;
				}
				break;
			} catch (Exception e) {
				counts++;
				continue;
			}
		}
		return false;
	}

	public static boolean compareDate(String curtTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date curtTime1 = sdf.parse(curtTime);
		Date endTime1 = sdf.parse(endTime);
		if (!curtTime1.after(endTime1)) {
			return true;
		}
		return false;
	}

	public static boolean scompareDate(String curtTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
		Date curtTime1 = sdf.parse(curtTime);
		Date endTime1 = sdf.parse(endTime);
		if (!curtTime1.after(endTime1)) {
			return true;
		}
		return false;
	}

	public static boolean checkStr(String complete, String regex) {
		if (complete.matches(regex) == true) {
			return true;
		}

		return false;

	}

	public static boolean compareData(int a, int b) {
		if (a >= b) {
			return true;
		}
		return false;
	}

	public static boolean equalStr(String str1, String str2) {
		if (str1.equals(str2) == true) {
			return true;
		}
		return false;

	}

	public static String querySysTime() {
		int y, m1, d1, h, mi, s;
		Calendar cal = Calendar.getInstance();
		y = cal.get(Calendar.YEAR);
		m1 = cal.get(Calendar.MONTH);
		d1 = cal.get(Calendar.DATE);
		int m, d;
		m = m1 + 1;
		d = d1;
		h = cal.get(Calendar.HOUR_OF_DAY);
		mi = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);
		String timeStr = y + "-" + m + "-" + d;
		return timeStr;
	}

	public static String squerySysTime() {
		int y, m1, d1, h, mi, s;
		Calendar cal = Calendar.getInstance();
		y = cal.get(Calendar.YEAR);
		m1 = cal.get(Calendar.MONTH);
		d1 = cal.get(Calendar.DATE);
		int m, d;
		m = m1 + 1;
		d = d1;
		h = cal.get(Calendar.HOUR_OF_DAY);
		mi = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);
		String timeStr = y + "年" + m + "月" + d + "日";
		return timeStr;
	}
	public static String Base64Encode(String str) { 
		if (str == null){
			return null; 
		}else
			return (new sun.misc.BASE64Encoder()).encode(str.getBytes()); 			
	} 

	public static String Base64EDecode(String str) { 
		if (str == null)
			return null; 
		else{
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			try { 
				byte[] bt = decoder.decodeBuffer(str); 
					return new String(bt); 
			}catch (Exception e){ 
					return null; 
			} 
		}
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(common.squerySysTime());
	}
}

