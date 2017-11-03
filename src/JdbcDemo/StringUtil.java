//package JdbcDemo;
//
//public class StringUtil {
//	public static String replace(String strSource, String strFrom, String strTo) {
//		if (strFrom == null || strFrom.equals(""))
//			return strSource;
//		String strDest = "";
//		int intFromLen = strFrom.length();
//		int intPos;
//		while ((intPos = strSource.indexOf(strFrom)) != -1) {
//			strDest = strDest + strSource.substring(0, intPos);
//			strDest = strDest + strTo;
//			strSource = strSource.substring(intPos + intFromLen);
//		}
//		strDest = strDest + strSource;
//		return strDest;
//	}
//
//	/**
//	 * Fck控件 输出中文转码
//	 */
//	public static String covertUnicode(String unicode) {
//		char[] unicodes = unicode.toCharArray();
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < unicodes.length; i++) {
//			if (unicodes[i] == '&' && unicodes.length > i + 7) {
//				if (unicodes[i] == '&' && unicodes[i + 1] == '#' && unicodes[i + 2] == 'x' && unicodes[i + 7] == ';') {
//					String word = "" + unicodes[i + 3] + unicodes[i + 4] + unicodes[i + 5] + unicodes[i + 6];
//					int a = Integer.parseInt(word, 16);
//					sb.append((char) a);
//					i += 7;
//				} else {
//					sb.append(unicodes[i]);
//				}
//			} else {
//				sb.append(unicodes[i]);
//			}
//		}
//		return sb.toString();
//	}
//
//	/**
//	 * HTML实体编码转成普通的编码
//	 */
//	public static String htmlEntityToString(final String dataStr) {
//		if (dataStr != null) {
//			int start = 0;
//			int end = 0;
//			StringBuffer buffer = new StringBuffer();
//			while (start > -1) {
//				int system = 10;// 进制
//				if (start == 0) {
//					int t = dataStr.indexOf("&#");
//					if (start != t)
//						start = t;
//				}
//				end = dataStr.indexOf(";", start + 2);
//				String charStr = "";
//				if (end != -1) {
//					charStr = dataStr.substring(start + 2, end);
//					// 判断进制
//					char s = charStr.charAt(0);
//					if (s == 'x' || s == 'X') {
//						system = 16;
//						charStr = charStr.substring(1);
//					}
//				}
//				// 转换
//				try {
//					char letter = (char) Integer.parseInt(charStr, system);
//					buffer.append(new Character(letter).toString());
//				} catch (NumberFormatException e) {
//					// e.printStackTrace();
//				}
//
//				// 处理当前unicode字符到下一个unicode字符之间的非unicode字符
//				start = dataStr.indexOf("&#", end);
//				if (start - end > 1) {
//					buffer.append(dataStr.substring(end + 1, start));
//				}
//
//				// 处理最后面的非unicode字符
//				if (start == -1) {
//					int length = dataStr.length();
//					if (end + 1 != length) {
//						buffer.append(dataStr.substring(end + 1, length));
//					}
//				}
//			}
//			return buffer.toString();
//		} else {
//			return "";
//		}
//	}
//}
