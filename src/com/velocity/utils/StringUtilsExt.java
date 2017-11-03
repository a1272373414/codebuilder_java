package com.velocity.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StringUtilsExt {

	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");
	private static int RESERVESNUM;
	private static char reserve[];
	private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();
	private static final char AMP_ENCODE[] = "&amp;".toCharArray();
	private static final char LT_ENCODE[] = "&lt;".toCharArray();
	private static final char GT_ENCODE[] = "&gt;".toCharArray();
	public static final long SECOND = 1000L;
	public static final long MINUTE = 60000L;
	public static final long HOUR = 0x36ee80L;
	public static final long DAY = 0x5265c00L;
	public static final long WEEK = 0x240c8400L;
	public static final long KBYTES = 1024L;
	private static SimpleDateFormat DATE_TEXT_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static MessageDigest digest = null;

	public StringUtilsExt() {
	}

	/**
	 * 把布尔型的字符串转换成整数,true->1,false->0,不区分大小写 不是布尔型的字符串全部转成0
	 * 
	 * @param booleanStr
	 * @return int
	 */
	public static int booleanStr2int(String booleanStr) {
		if (new Boolean(booleanStr).booleanValue()) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 去除字符串头尾的空白,如果是null,则返回空白
	 * 
	 * @param str
	 * @return String
	 */
	public static String trim(String str) {
		if (str == null) {
			return "";
		} else {
			return str.trim();
		}
	}

	/**
	 * 只适用网页上进行字符串转换成空白 返回&nbsp;
	 * 
	 * @param str
	 * @return str
	 */
	public static String webTrim(String str) {
		str = trim(str);
		return str.equals("") ? "&nbsp;" : str;
	}

	/**
	 * 只适用网页上进行对象转换成空白 返回&nbsp;
	 * 
	 * @param obj
	 * @return string
	 */
	public static String webTrim(Object obj) {
		String str = trim(obj);
		return str.equals("") ? "&nbsp;" : str;
	}

	public static String trim(String str, String defult) {
		if (str == null) {
			return trim(defult);
		} else {
			return str.trim();
		}
	}

	public static String trim(Object str) {
		if (str == null)
			return "";
		else
			return str.toString().trim();
	}

	public static String trim_web(String str) {
		String tmp = trim(str);
		if (tmp.equals(""))
			return "&nbsp;";
		else
			return tmp;
	}

	public static String trim(Object str, String defult) {
		if (str == null)
			return trim(defult);
		else
			return str.toString().trim();
	}

	public static String nonNull(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	public static StringBuffer getStringBuffer(InputStream in) throws IOException {
		if (in == null)
			return new StringBuffer("");
		StringBuffer result = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		char buf[] = new char[1024];
		for (boolean quit = false; !quit;) {
			int len = reader.read(buf);
			if (len < 0)
				quit = true;
			else
				result.append(buf, 0, len);
		}

		reader.close();
		return result;
	}

	public static String getText(InputStream in) throws IOException {
		return getStringBuffer(in).toString();
	}

	public static String getText(Reader reader) throws IOException {
		if (reader == null)
			return "";
		StringBuffer dat = new StringBuffer();
		char buf[] = new char[1024];
		do {
			int l = reader.read(buf);
			if (l >= 0)
				dat.append(buf, 0, l);
			else
				return dat.toString();
		} while (true);
	}

	public static int getAsInt(String str) {
		return getAsInt(str, -1);
	}

	public static int getAsInt(String str, int defaultv) {
		if (str == null || "".equals(str)) {
			return defaultv;
		}
		try {
			return Integer.parseInt(str, 10);
		} catch (Exception e) {
			return defaultv;
		}

	}

	public static long getAsLong(String str) {
		return getAsLong(str, -1L);
	}

	public static long getAsLong(String str, long defaultv) {
		if (str == null || "".equals(str)) {
			return defaultv;
		}
		try {
			return Long.parseLong(str, 10);
		} catch (Exception e) {
			return defaultv;
		}

	}

	public static String[] split(String str, String seperator) {
		return split(str, seperator, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] split(String str, String seperator, boolean tail) {
		if (str == null || str.equals("") || trim(seperator).equals(""))
			if (tail)
				return (new String[] { "" });
			else
				return new String[0];
		if (!tail && str.endsWith(seperator))
			str = str.substring(0, str.length() - seperator.length());
		ArrayList temp = new ArrayList();
		int oldPos = 0;
		int newPos = str.indexOf(seperator);
		int parentLength = str.length();
		int subStrLength = seperator.length();
		if (newPos != -1)
			newPos += subStrLength;
		while (newPos <= parentLength && newPos != -1) {
			temp.add(str.substring(oldPos, newPos - subStrLength));
			oldPos = newPos;
			newPos = str.indexOf(seperator, oldPos);
			if (newPos != -1)
				newPos += seperator.length();
		}
		if (oldPos <= parentLength)
			temp.add(str.substring(oldPos));
		return (String[]) temp.toArray(new String[temp.size()]);
	}

	public static int count(String src, String key) {
		int count = 0;
		for (String temp = src; temp.indexOf(key) >= 0; temp = temp.substring(temp.indexOf(key) + key.length()))
			count++;

		return count;
	}

	public static String replace(String src, String oldStr, String newStr) {
		StringBuffer result = new StringBuffer();
		boolean found = false;
		if (src == null || oldStr == null || newStr == null)
			throw new NullPointerException("parameter is null");
		if (oldStr.length() == 0)
			throw new IllegalArgumentException("illegal parameter");
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
		if (url == null)
			return "";
		byte dat[];
		if (encode != null)
			try {
				dat = url.getBytes(encode);
			} catch (UnsupportedEncodingException ex) {
				return "";
			}
		else
			dat = url.getBytes();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < dat.length; i++) {
			int ch = dat[i] & 0xff;
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
		if (ch > 126 || ch < 32)
			return true;
		for (int i = 0; i < RESERVESNUM; i++)
			if (ch == reserve[i])
				return true;

		return false;
	}

	public static final String escapeHTMLTags(String in) {
		if (in == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '>') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(GT_ENCODE);
				}
		}

		if (last == 0)
			return in;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	public static final String escapeForXML(String in) {
		if (in == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '&') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(AMP_ENCODE);
				} else if (ch == '"') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(QUOTE_ENCODE);
				}
		}

		if (last == 0)
			return in;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	public static Calendar toTime(String str) {
		if (str == null)
			return null;
		if (str.length() != 14)
			return null;
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
		if (date == null)
			return "";
		else
			return DATE_FORMATTER.format(date);
	}

	public static Date TimeStrToDate(String str) {
		if (str == null)
			return null;
		str = str.trim();
		if (str.length() != 14 && str.length() != 8)
			return null;
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
		long num = size / 1024L + (long) (size % 1024L <= 0L ? 0 : 1);
		return num + "KB";
	}

	public static String getDateText(Date date) {
		if (date == null)
			return "";
		else
			return DATE_TEXT_FORMATTER.format(date);
	}

	public static final synchronized String hash(String data) {
		if (digest == null)
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. System will be unable to function normally.");
				nsae.printStackTrace();
			}
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	public static final String encodeHex(byte bytes[]) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 16)
				buf.append("0");
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}

		return buf.toString();
	}

	public static final byte[] decodeHex(String hex) {
		char chars[] = hex.toCharArray();
		byte bytes[] = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}

		return bytes;
	}

	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case 48: // '0'
			return 0;

		case 49: // '1'
			return 1;

		case 50: // '2'
			return 2;

		case 51: // '3'
			return 3;

		case 52: // '4'
			return 4;

		case 53: // '5'
			return 5;

		case 54: // '6'
			return 6;

		case 55: // '7'
			return 7;

		case 56: // '8'
			return 8;

		case 57: // '9'
			return 9;

		case 97: // 'a'
			return 10;

		case 98: // 'b'
			return 11;

		case 99: // 'c'
			return 12;

		case 100: // 'd'
			return 13;

		case 101: // 'e'
			return 14;

		case 102: // 'f'
			return 15;

		case 65: // 'A'
			return 10;

		case 66: // 'B'
			return 11;

		case 67: // 'C'
			return 12;

		case 68: // 'D'
			return 13;

		case 69: // 'E'
			return 14;

		case 70: // 'F'
			return 15;

		case 58: // ':'
		case 59: // ';'
		case 60: // '<'
		case 61: // '='
		case 62: // '>'
		case 63: // '?'
		case 64: // '@'
		case 71: // 'G'
		case 72: // 'H'
		case 73: // 'I'
		case 74: // 'J'
		case 75: // 'K'
		case 76: // 'L'
		case 77: // 'M'
		case 78: // 'N'
		case 79: // 'O'
		case 80: // 'P'
		case 81: // 'Q'
		case 82: // 'R'
		case 83: // 'S'
		case 84: // 'T'
		case 85: // 'U'
		case 86: // 'V'
		case 87: // 'W'
		case 88: // 'X'
		case 89: // 'Y'
		case 90: // 'Z'
		case 91: // '['
		case 92: // '\\'
		case 93: // ']'
		case 94: // '^'
		case 95: // '_'
		case 96: // '`'
		default:
			return 0;
		}
	}

	public static String encodeBase64(String data) {
		return encodeBase64(trim(data).getBytes());
	}

	public static String encodeBase64(byte data[]) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
		for (int i = 0; i < len; i++) {
			int c = data[i] >> 2 & 0x3f;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			c = data[i] << 4 & 0x3f;
			if (++i < len)
				c |= data[i] >> 4 & 0xf;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			if (i < len) {
				c = data[i] << 2 & 0x3f;
				if (++i < len)
					c |= data[i] >> 6 & 0x3;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				i++;
				ret.append('=');
			}
			if (i < len) {
				c = data[i] & 0x3f;
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

	public static String decodeBase64(byte data[]) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; i++) {
			int c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
			i++;
			int c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
			c = c << 2 | c1 >> 4 & 0x3;
			ret.append((char) c);
			if (++i < len) {
				c = data[i];
				if (61 == c)
					break;
				c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c);
				c1 = c1 << 4 & 0xf0 | c >> 2 & 0xf;
				ret.append((char) c1);
			}
			if (++i >= len)
				continue;
			c1 = data[i];
			if (61 == c1)
				break;
			c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c1);
			c = c << 6 & 0xc0 | c1;
			ret.append((char) c);
		}

		return ret.toString();
	}

	public static String encodeQuote(String data) {
		return data;
	}

	public static String decodeQuote(String data) {
		StringBuffer ret = new StringBuffer(data.length() / 3);
		char chars[] = data.toCharArray();
		for (int i = 0; i < chars.length; i++)
			if (chars[i] == '=') {
				i++;
				StringBuffer hex = new StringBuffer();
				for (; i < chars.length; i++) {
					hex.append(chars[i]);
					i++;
					hex.append(chars[i]);
					if (++i < chars.length && chars[i] != '=')
						break;
				}

				byte bytes[] = decodeHex(hex.toString());
				ret.append(new String(bytes));
			} else {
				ret.append(chars[i]);
			}

		return ret.toString();
	}

	public static final String chopAtWord(String string, int length) {
		if (string == null)
			return string;
		char charArray[] = string.toCharArray();
		int sLength = string.length();
		if (length < sLength)
			sLength = length;
		for (int i = 0; i < sLength - 1; i++) {
			if (charArray[i] == '\r' && charArray[i + 1] == '\n')
				return string.substring(0, i + 1);
			if (charArray[i] == '\n')
				return string.substring(0, i);
		}

		if (charArray[sLength - 1] == '\n')
			return string.substring(0, sLength - 1);
		if (string.length() < length)
			return string;
		for (int i = length - 1; i > 0; i--)
			if (charArray[i] == ' ')
				return string.substring(0, i).trim();

		return string.substring(0, length);
	}

	public static String join(String list[], String seperator) {
		StringBuffer result = new StringBuffer();
		if (list == null || list.length <= 0)
			return "";
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null)
				result.append(list[i].toString());
			if (i != list.length - 1)
				result.append(seperator);
		}

		return result.toString();
	}

	public static String join(long[] values, String seperator) {
		StringBuffer result = new StringBuffer();
		if (values == null || values.length <= 0)
			return "";
		for (int i = 0; i < values.length; i++) {
			result.append(trim(values[i]));
			if (i != values.length - 1) {
				result.append(seperator);
			}
		}

		return result.toString();
	}

	public static String gbEncoding(String gbString) {
		char utfBytes[] = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2)
				hexB = "00" + hexB;
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}

		return unicodeBytes;
	}

	public static boolean isASCIIString(String str) {
		if (str == null || str.length() == 0)
			return true;
		byte bytes[] = str.getBytes();
		for (int i = 0; i < bytes.length; i++)
			if (bytes[i] < 0)
				return false;

		return true;
	}

	public static String duplicate(String source, int count) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < count; i++)
			sb.append(source);

		return sb.toString();
	}

	public static String fillCharAtHeadForNumber(int source, String count, char fillchr) {
		int i = getAsInt(count);
		String str = (new StringBuffer(String.valueOf(source))).toString();
		if (str.length() < i) {
			for (int j = str.length(); j < i; j++)
				str = fillchr + str;

		}
		return str;
	}

	public static String fillCharAtHead(String source, String count, char fillchr) {
		int i = getAsInt(count);
		if (source.length() < i) {
			for (int j = source.length(); j < i; j++)
				source = fillchr + source;

		}
		return source;
	}

	public static String fillCharAtTail(String source, String count, char fillchr) {
		int i = getAsInt(count);
		if (source.length() < i) {
			for (int j = source.length(); j < i; j++)
				source = source + fillchr;

		}
		return source;
	}

	public static String pad(String words, String filling, int length) {
		for (int i = words.length(); i < length; i++)
			words = filling + words;

		return words;
	}

	public static String rpad(String words, String filling, int length) {
		for (int i = words.length(); i < length; i++)
			words = words + filling;

		return words;
	}

	public static boolean in(String str, String array[]) {
		if (array != null && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				if (str == null && array[i] == null)
					return true;
				if (str != null && str.equals(array[i]))
					return true;
			}
		} else {
			return str == null;
		}

		return false;
	}

	public static int indexof(String strs[], String str) {
		if (str == null || strs == null)
			return -1;
		for (int i = 0; i < strs.length; i++)
			if (str.equals(strs[i]))
				return i;

		return -1;
	}

	public static boolean checkIntercross(String strs1[], String strs2[]) {
		if (strs1 == null || strs2 == null)
			return false;
		for (int i = 0; i < strs1.length; i++) {
			for (int k = 0; k < strs2.length; k++)
				if (strs1[i].equals(strs2[k]))
					return true;

		}

		return false;
	}

	public static String acfSubstring(String s, int j) throws IOException {
		if (s == null)
			return "";
		int k = 0;
		int l = 0;
		for (int i1 = 0; i1 < s.length() && j > l * 2 + k; i1++)
			if (s.charAt(i1) > '\200')
				l++;
			else
				k++;

		if (l + k >= s.length())
			return s;
		if (j >= l * 2 + k && l + k > 0)
			return s.substring(0, l + k) + "...";
		if (l + k > 0)
			return s.substring(0, (l + k) - 1) + "...";
		else
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

	/**
	 * 过滤数组中重复的数值只保留其中的一个
	 * 
	 * @param arrays
	 * @return String[]
	 */
	public static String[] filterDuplicate(String[] arrays) {
		if (arrays == null || arrays.length == 0) {
			return null;
		}
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < arrays.length; i++) {
			if (!list.contains(arrays[i])) {
				list.add(arrays[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * 填充空白的str为newstr
	 * 
	 * @param str
	 * @param newstr
	 * @return
	 */
	public static String fillblankstr(String str, String newstr) {
		return StringUtilsExt.hasText(str) ? str : newstr;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean isEmptyArray(String[] arr) {
		if (arr == null || arr.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取两个数组之间的交集
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] getArraysIntersection(String[] arr1, String[] arr2) {
		if (isEmptyArray(arr1)) {
			if (isEmptyArray(arr2)) {
				return null;
			} else {
				return arr2;
			}
		} else {
			if (isEmptyArray(arr2)) {
				return arr1;
			} else {
				List vallist = new ArrayList();
				for (int i = 0; i < arr1.length; i++) {
					String val = arr1[i];
					if (in(val, arr2) && !vallist.contains(val)) {
						vallist.add(val);
					}
				}
				return (String[]) vallist.toArray(new String[0]);
			}
		}
	}

	public static String getGenerateKey(int index) {
		StringBuffer sb = new StringBuffer();
		String[] arr = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		for (int i = 0; i < index; i++) {
			sb.append(String.valueOf(arr[Math.abs(new Random().nextInt() % 9)]));
		}
		return sb.toString();
	}

}
