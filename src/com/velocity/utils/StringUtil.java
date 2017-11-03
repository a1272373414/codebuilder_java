package com.velocity.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.ArrayUtils;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class StringUtil {
	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat DATE_TEXT_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static int RESERVESNUM;
	private static char[] reserve;
	private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
	private static final char[] AMP_ENCODE = "&amp;".toCharArray();
	private static final char[] LT_ENCODE = "&lt;".toCharArray();
	private static final char[] GT_ENCODE = "&gt;".toCharArray();
	public static final long SECOND = 1000L;
	public static final long MINUTE = 60000L;
	public static final long HOUR = 3600000L;
	public static final long DAY = 86400000L;
	public static final long WEEK = 604800000L;
	public static final long KBYTES = 1024L;
	private static MessageDigest digest = null;

	public static int booleanStr2int(String booleanStr) {
		return new Boolean(booleanStr).booleanValue() ? 1 : 0;
	}

	public static boolean booleanStr2boolen(String booleanStr) {
		return hasText(booleanStr) ? new Boolean(booleanStr).booleanValue() : false;
	}

	public static String trim(String str) {
		return str == null ? "" : str.trim();
	}

	public static String webTrim(String str) {
		str = trim(str);
		return str.equals("") ? "&nbsp;" : str;
	}

	public static String webTrim(Object obj) {
		String str = trim(obj);
		return str.equals("") ? "&nbsp;" : str;
	}

	public static String trim(String str, String defult) {
		return str == null ? trim(defult) : str.trim();
	}

	public static List<Long> removeDuplicate(List<Long> list) {
		HashSet<Long> set = new HashSet(list);
		list.clear();
		list.addAll(set);
		return list;
	}

	public static double getDouble(String str) throws Exception, NumberFormatException {
		return getDouble(str, 0.0D);
	}

	public static double getDouble(String str, double defaultValue) throws Exception, NumberFormatException {
		if (!hasText(str)) {
			return defaultValue;
		}
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException numberformatexception) {
		}
		return defaultValue;
	}

	public static String trim(Object obj, String defult) {
		return obj == null ? trim(defult) : obj.toString().trim();
	}

	public static String trim(Object str) {
		return str == null ? "" : str.toString().trim();
	}

	/**
	* @deprecated
	*/
	public static String trim_web(String str) {
		String tmp = trim(str);
		return tmp.equals("") ? "&nbsp;" : tmp;
	}

	public static String nonNull(String str) {
		return str == null ? "" : str;
	}

	public static StringBuffer getStringBuffer(InputStream in) throws IOException {
		if (in == null) {
			return new StringBuffer("");
		}
		StringBuffer result = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		char[] buf = new char['?'];
		for (boolean quit = false; !quit;) {
			int len = reader.read(buf);
			if (len < 0) {
				quit = true;
			} else {
				result.append(buf, 0, len);
			}
		}
		reader.close();
		return result;
	}

	public static String getText(InputStream in) throws IOException {
		return getStringBuffer(in).toString();
	}

	public static String getText(Reader reader) throws IOException {
		if (reader == null) {
			return "";
		}
		StringBuffer dat = new StringBuffer();
		char[] buf = new char['?'];
		for (;;) {
			int l = reader.read(buf);
			if (l >= 0) {
				dat.append(buf, 0, l);
			} else {
				return dat.toString();
			}
		}
	}

	public static int getAsInt(String str) {
		return getAsInt(str, -1);
	}

	public static int getAsInt(String str, int defaultv) {
		if ((str == null) || ("".equals(str))) {
			return defaultv;
		}
		try {
			return Integer.parseInt(str, 10);
		} catch (Exception e) {
		}
		return defaultv;
	}

	public static long getAsLong(String str) {
		return getAsLong(str, -1L);
	}

	public static long getAsLong(String str, long defaultv) {
		if ((str == null) || ("".equals(str))) {
			return defaultv;
		}
		try {
			return Long.parseLong(str, 10);
		} catch (Exception e) {
		}
		return defaultv;
	}

	public static String[] split(String str, String seperator) {
		return split(str, seperator, false);
	}

	public static String[] split(String str, String seperator, boolean tail) {
		if ((str == null) || (str.equals("")) || (trim(seperator).equals(""))) {
			return tail ? new String[] { "" } : new String[0];
		}
		if ((!tail) && (str.endsWith(seperator))) {
			str = str.substring(0, str.length() - seperator.length());
		}

		ArrayList temp = new ArrayList();
		int oldPos = 0;
		int newPos = str.indexOf(seperator);
		int parentLength = str.length();
		int subStrLength = seperator.length();
		if (newPos != -1) {
			newPos += subStrLength;
		}
		while ((newPos <= parentLength) && (newPos != -1)) {
			temp.add(str.substring(oldPos, newPos - subStrLength));
			oldPos = newPos;
			newPos = str.indexOf(seperator, oldPos);
			if (newPos != -1) {
				newPos += seperator.length();
			}
		}
		if (oldPos <= parentLength) {
			temp.add(str.substring(oldPos));
		}
		return (String[]) temp.toArray(new String[temp.size()]);
	}

	public static int count(String src, String key) {
		int count = 0;
		for (String temp = src; temp.indexOf(key) >= 0; temp = temp.substring(temp.indexOf(key) + key.length())) {
			count++;
		}
		return count;
	}

	public static String replace(String src, String oldStr, String newStr) {
		StringBuffer result = new StringBuffer();
		boolean found = false;
		if ((src == null) || (oldStr == null) || (newStr == null)) {
			throw new NullPointerException("parameter is null");
		}
		if (oldStr.length() == 0) {
			throw new IllegalArgumentException("illegal parameter");
		}
		int pos = 0;
		int pos1 = 0;
		while (pos < src.length()) {
			pos1 = src.indexOf(oldStr, pos);
			if (pos1 < 0) {
				pos1 = src.length();
				found = false;
			} else {
				found = true;
			}
			result.append(src.substring(pos, pos1));
			if (found) {
				result.append(newStr);
				pos = pos1 + oldStr.length();
			} else {
				pos = pos1;
			}
		}
		return result.toString();
	}

	public static String urlEncode(String url, String encode) {
		if (url == null) {
			return "";
		}
		byte[] dat;
		if (encode != null) {
			try {
				dat = url.getBytes(encode);
			} catch (UnsupportedEncodingException ex) {
				return "";
			}
		} else {
			dat = url.getBytes();
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < dat.length; i++) {
			int ch = dat[i] & 0xFF;
			if (isReserve(ch)) {
				result.append("%");
				result.append(Integer.toHexString(ch).toUpperCase());
			} else {
				result.append((char) ch);
			}
		}
		return result.toString();
	}

	private static boolean isReserve(int ch) {
		if ((ch > 126) || (ch < 32)) {
			return true;
		}
		for (int i = 0; i < RESERVESNUM; i++) {
			if (ch == reserve[i]) {
				return true;
			}
		}
		return false;
	}

	public static final String escapeHTMLTags(String in) {
		if (in == null) {
			return null;
		}
		int i = 0;
		int last = 0;
		char[] input = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>') {
				if (ch == '<') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '>') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(GT_ENCODE);
				}
			}
		}
		if (last == 0) {
			return in;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	public static final String escapeForXML(String in) {
		if (in == null) {
			return null;
		}
		int i = 0;
		int last = 0;
		char[] input = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>') {
				if (ch == '<') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '&') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(AMP_ENCODE);
				} else if (ch == '"') {
					if (i > last) {
						out.append(input, last, i - last);
					}
					last = i + 1;
					out.append(QUOTE_ENCODE);
				}
			}
		}
		if (last == 0) {
			return in;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	public static Calendar toTime(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() != 14) {
			return null;
		}
		Calendar time = Calendar.getInstance();
		time.clear();
		try {
			String tmp = str.substring(0, 4);
			int y = Integer.parseInt(tmp);
			tmp = str.substring(4, 6);
			int m = Integer.parseInt(tmp) - 1;
			tmp = str.substring(6, 8);
			int d = Integer.parseInt(tmp);
			tmp = str.substring(8, 10);
			int h = Integer.parseInt(tmp);
			tmp = str.substring(10, 12);
			int min = Integer.parseInt(tmp);
			tmp = str.substring(12, 14);
			int s = Integer.parseInt(tmp);
			time.set(y, m, d, h, min, s);
		} catch (NumberFormatException ex) {
			return null;
		}
		return time;
	}

	public static String DateToTimeStr(Date date) {
		return date == null ? "" : DATE_FORMATTER.format(date);
	}

	public static String getDateText(Date date) {
		return date == null ? "" : DATE_TEXT_FORMATTER.format(date);
	}

	public static Date TimeStrToDate(String str) {
		if (str == null) {
			return null;
		}
		str = str.trim();
		if ((str.length() != 14) && (str.length() != 8)) {
			return null;
		}
		Calendar time = Calendar.getInstance();
		time.clear();
		try {
			String tmp = str.substring(0, 4);
			int y = Integer.parseInt(tmp);
			tmp = str.substring(4, 6);
			int m = Integer.parseInt(tmp) - 1;
			tmp = str.substring(6, 8);
			int d = Integer.parseInt(tmp);
			if (str.length() > 8) {
				tmp = str.substring(8, 10);
				int h = Integer.parseInt(tmp);
				tmp = str.substring(10, 12);
				int min = Integer.parseInt(tmp);
				tmp = str.substring(12, 14);
				int s = Integer.parseInt(tmp);
				time.set(y, m, d, h, min, s);
			} else {
				time.set(y, m, d);
			}
		} catch (NumberFormatException ex) {
			return null;
		}
		return time.getTime();
	}

	public static String replaceToken(String s) {
		int startToken = s.indexOf("${");
		int endToken = s.indexOf("}", startToken);
		String token = s.substring(startToken + 2, endToken);
		StringBuffer value = new StringBuffer();
		value.append(s.substring(0, startToken));
		value.append(System.getProperty(token));
		value.append(s.substring(endToken + 1));
		return value.toString();
	}

	public static String getFileSizeText(long size) {
		long num = size / 1024L + (size % 1024L <= 0L ? 0 : 1);
		return num + "KB";
	}

	public static final synchronized String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. System will be unable to function normally.");
				nsae.printStackTrace();
			}
		}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xFF) < 16) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xFF, 16));
		}
		return buf.toString();
	}

	public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0;
			newByte = (byte) (newByte | hexCharToByte(chars[i]));
			newByte = (byte) (newByte << 4);
			newByte = (byte) (newByte | hexCharToByte(chars[(i + 1)]));
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case 'a':
			return 10;
		case 'b':
			return 11;
		case 'c':
			return 12;
		case 'd':
			return 13;
		case 'e':
			return 14;
		case 'f':
			return 15;
		case 'A':
			return 10;
		case 'B':
			return 11;
		case 'C':
			return 12;
		case 'D':
			return 13;
		case 'E':
			return 14;
		case 'F':
			return 15;
		}
		return 0;
	}

	public static String encodeBase64(String data) {
		return encodeBase64(trim(data).getBytes());
	}

	public static String encodeBase64(byte[] data) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
		for (int i = 0; i < len; i++) {
			int c = data[i] >> 2 & 0x3F;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			c = data[i] << 4 & 0x3F;
			i++;
			if (i < len) {
				c |= data[i] >> 4 & 0xF;
			}
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			if (i < len) {
				c = data[i] << 2 & 0x3F;
				i++;
				if (i < len) {
					c |= data[i] >> 6 & 0x3;
				}
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				i++;
				ret.append('=');
			}
			if (i < len) {
				c = data[i] & 0x3F;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				ret.append('=');
			}
		}
		return ret.toString();
	}

	public static String decodeBase64(String data) {
		return decodeBase64(data.getBytes());
	}

	public static String decodeBase64(byte[] data) {
		int len = data.length;
		StringBuffer ret = new StringBuffer(len * 3 / 4);
		for (int i = 0; i < len; i++) {
			int c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
			i++;
			int c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
			c = c << 2 | c1 >> 4 & 0x3;
			ret.append((char) c);
			i++;
			if (i < len) {
				c = data[i];
				if (61 == c) {
					break;
				}
				c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c);
				c1 = c1 << 4 & 0xF0 | c >> 2 & 0xF;
				ret.append((char) c1);
			}
			i++;
			if (i < len) {
				c1 = data[i];
				if (61 == c1) {
					break;
				}
				c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c1);
				c = c << 6 & 0xC0 | c1;
				ret.append((char) c);
			}
		}
		return ret.toString();
	}

	public static String decodeString(String str, String format) throws Exception {
		if ((hasText(str)) && (hasText(format))) {
			return URLDecoder.decode(str, format);
		}
		return str;
	}

	public static String join(String[] list, String seperator) {
		StringBuffer result = new StringBuffer();
		if ((list == null) || (list.length <= 0)) {
			return "";
		}
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null) {
				result.append(list[i].toString());
			}
			if (i != list.length - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	public static String join(ArrayList list, String seperator) {
		StringBuffer result = new StringBuffer();
		if ((list == null) || (list.size() <= 0)) {
			return "";
		}
		for (int i = 0; i < list.size(); i++) {
			if (hasText(trim(list.get(i)))) {
				result.append(trim(list.get(i)));
			}
			if (i != list.size() - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	public static String join(long[] values, String seperator) {
		StringBuffer result = new StringBuffer();
		if ((values == null) || (values.length <= 0)) {
			return "";
		}
		for (int i = 0; i < values.length; i++) {
			result.append(trim(Long.valueOf(values[i])));
			if (i != values.length - 1) {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	public static String gbEncoding(String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		return unicodeBytes;
	}

	public static boolean isASCIIString(String str) {
		if ((str == null) || (str.length() == 0)) {
			return true;
		}
		byte[] bytes = str.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] < 0) {
				return false;
			}
		}
		return true;
	}

	public static String duplicate(String source, int count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++) {
			sb.append(source);
		}
		return sb.toString();
	}

	public static String fillCharAtHeadForNumber(int source, String count, char fillchr) {
		int i = getAsInt(count);
		String str = trim(Integer.valueOf(source));
		if (str.length() < i) {
			for (int j = str.length(); j < i; j++) {
				str = fillchr + str;
			}
		}
		return str;
	}

	public static String fillCharAtHead(String source, String count, char fillchr) {
		int i = getAsInt(count);
		if (source.length() < i) {
			for (int j = source.length(); j < i; j++) {
				source = fillchr + source;
			}
		}
		return source;
	}

	// public static String getRequestIP(HttpServletRequest request) {
	// String ip = request.getHeader("x-forwarded-for");
	// if ((ip == null) || (ip.length() == 0) ||
	// ("unknown".equalsIgnoreCase(ip))) {
	// ip = request.getHeader("Proxy-Client-IP");
	// }
	// if ((ip == null) || (ip.length() == 0) ||
	// ("unknown".equalsIgnoreCase(ip))) {
	// ip = request.getHeader("WL-Proxy-Client-IP");
	// }
	// if ((ip == null) || (ip.length() == 0) ||
	// ("unknown".equalsIgnoreCase(ip))) {
	// ip = request.getRemoteAddr();
	// }
	// return ip;
	// }

	public static String fillCharAtTail(String source, String count, char fillchr) {
		int i = getAsInt(count);
		if (source.length() < i) {
			for (int j = source.length(); j < i; j++) {
				source = source + fillchr;
			}
		}
		return source;
	}

	public static String pad(String words, String filling, int length) {
		for (int i = words.length(); i < length; i++) {
			words = filling + words;
		}
		return words;
	}

	public static String rpad(String words, String filling, int length) {
		for (int i = words.length(); i < length; i++) {
			words = words + filling;
		}
		return words;
	}

	public static boolean in(String str, String[] array) {
		if ((array != null) && (array.length > 0)) {
			for (int i = 0; i < array.length; i++) {
				if ((str == null) && (array[i] == null)) {
					return true;
				}
				if ((str != null) && (str.equals(array[i]))) {
					return true;
				}
			}
		} else {
			return str == null;
		}
		return false;
	}

	public static int indexof(String[] strs, String str) {
		if ((str == null) || (strs == null)) {
			return -1;
		}
		for (int i = 0; i < strs.length; i++) {
			if (str.equals(strs[i])) {
				return i;
			}
		}
		return -1;
	}

	public static boolean checkIntercross(String[] strs1, String[] strs2) {
		if ((strs1 == null) || (strs2 == null)) {
			return false;
		}
		for (int i = 0; i < strs1.length; i++) {
			for (int k = 0; k < strs2.length; k++) {
				if (strs1[i].equals(strs2[k])) {
					return true;
				}
			}
		}
		return false;
	}

	public static String acfSubstring(String s, int j) throws IOException {
		if (s == null) {
			return "";
		}
		int k = 0;
		int l = 0;
		for (int i1 = 0; (i1 < s.length()) && (j > l * 2 + k); i1++) {
			if (s.charAt(i1) > '?') {
				l++;
			} else {
				k++;
			}
		}
		if (l + k >= s.length()) {
			return s;
		}
		if ((j >= l * 2 + k) && (l + k > 0)) {
			return s.substring(0, l + k) + "...";
		}
		if (l + k > 0) {
			return s.substring(0, l + k - 1) + "...";
		}
		return s;
	}

	static {
		RESERVESNUM = 12;
		reserve = new char[RESERVESNUM];
		reserve[0] = '!';
		reserve[1] = '#';
		reserve[2] = '%';
		reserve[3] = '*';
		reserve[4] = '/';
		reserve[5] = ':';
		reserve[6] = '?';
		reserve[7] = '&';
		reserve[8] = ';';
		reserve[9] = '=';
		reserve[10] = '@';
		reserve[11] = ' ';
	}

	public static boolean hasText(String str) {
		return !"".equals(nonNull(str));
	}

	public static String[] filterDuplicate(String[] arrays) {
		if ((arrays == null) || (arrays.length == 0)) {
			return null;
		}
		List<String> list = new LinkedList();
		for (int i = 0; i < arrays.length; i++) {
			if (!list.contains(arrays[i])) {
				list.add(arrays[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String fillblankstr(String str, String newstr) {
		return hasText(str) ? str : newstr;
	}

	public static boolean isEmptyArray(String[] arr) {
		if ((arr == null) || (arr.length == 0)) {
			return true;
		}
		return false;
	}

	public static String[] getArraysIntersection(String[] arr1, String[] arr2) {
		if (isEmptyArray(arr1)) {
			if (isEmptyArray(arr2)) {
				return null;
			}
			return arr2;
		}
		if (isEmptyArray(arr2)) {
			return arr1;
		}
		List vallist = new ArrayList();
		for (int i = 0; i < arr1.length; i++) {
			String val = arr1[i];
			if ((in(val, arr2)) && (!vallist.contains(val))) {
				vallist.add(val);
			}
		}
		return (String[]) vallist.toArray(new String[0]);
	}

	public static HashMap convertMap(String[][] arrays) {
		return (HashMap) ArrayUtils.toMap(arrays);
	}

	public static String firstCharToLowCase(String str) {
		String result = "";
		if ((str != null) && (str.length() > 0)) {
			String firstChar = str.substring(0, 1);
			firstChar = firstChar.toLowerCase();
			result = firstChar + str.substring(1, str.length());
		}
		return result;
	}

	public static String firstCharToUpperCase(String str) {
		String result = "";
		if ((str != null) && (str.length() > 0)) {
			String firstChar = str.substring(0, 1);
			firstChar = firstChar.toUpperCase();
			result = firstChar + str.substring(1, str.length());
		}
		return result;
	}

	public static String null2String(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public static String trimLeadingWhitespace(String str) {
		if (str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while ((buf.length() > 0) && (Character.isWhitespace(buf.charAt(0)))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (arr == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if ((!ignoreEmptyTokens) || (token.length() > 0)) {
				tokens.add(token);
			}
		}
		return (String[]) tokens.toArray(new String[tokens.size()]);
	}

	public static String join(List as, String s) {
		StringBuffer stringbuffer = new StringBuffer();
		if (null == as) {
			return "";
		}
		int j = 0;
		for (int len = as.size(); j < len; j++) {
			if (null != as.get(j)) {
				stringbuffer.append(as.get(j).toString());
			}
			if (j != len - 1) {
				stringbuffer.append(s);
			}
		}
		return stringbuffer.toString();
	}
}
