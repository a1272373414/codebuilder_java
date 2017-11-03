package com.velocity.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

@SuppressWarnings("unused")
public class DateUtils {
	private static final String FORMAT_0 = "yyyy-MM-dd HH:mm:ss";
	private static final String FORMAT_1 = "yyyy-MM-dd";
	private static final String FORMAT_2 = "HH:mm:ss";
	private static final String H_M_S_FORMAT = "HH:mm:ss";
	private static final String Y_M_D_FORMAT = "yyyy-MM-dd";
	private static final String YMDHMS_FORMAT = "yyyyMMddHHmmss";
	private static final String Y_M_D_H_M_S_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String FormatDateDefault() {
		return FormatDatetime("yyyy-MM-dd", new java.util.Date());
	}

	public static final String FormatDatetime(String s, java.util.Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
		return simpledateformat.format(date);
	}

	public static final String FormatDatetime(String s) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
		return simpledateformat.format(new java.util.Date());
	}

	public static final String DatetimetoStr(java.util.Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(date);
	}

	public static final java.util.Date StrtoDate(String s) throws Exception {
		return StrtoDate(s, "-");
	}

	public static final java.util.Date StrtoDate(String s, String s1) throws Exception {
		if ((s == null) || (s.length() == 0)) {
			return new java.util.Date();
		}
		StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
		if (stringtokenizer.countTokens() != 3) {
			throw new Exception("date format error!");
		}
		int i = Integer.parseInt(stringtokenizer.nextToken());
		int j = Integer.parseInt(stringtokenizer.nextToken());
		int k = Integer.parseInt(stringtokenizer.nextToken());
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, i);
		calendar.set(2, j - 1);
		calendar.set(5, k);
		return calendar.getTime();
	}

	public static boolean isDateBefore(String date2) throws ParseException {
		java.util.Date date1 = new java.util.Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		return date1.before(df.parse(date2));
	}

	public static final String DatetoStr(java.util.Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		return simpledateformat.format(date);
	}

	public static final String DatetoStr1(java.util.Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpledateformat.format(date);
	}

	public static final String DateAfterNow(int amount) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());
		calendar.add(5, amount);
		return DatetoStr(calendar.getTime());
	}

	public static final java.util.Date Now() {
		return new java.util.Date();
	}

	public static final String NowStr() {
		return DatetimetoStr(new java.util.Date());
	}

	public static final String DateStr() {
		return DatetoStr(new java.util.Date());
	}

	public static final String getTime() {
		return String.valueOf(new java.util.Date().getTime());
	}

	public static final String getSecond() {
		return getTime().substring(0, 10);
	}

	public static final String getStrCurrYear() {
		return FormatDatetime("yyyy", new java.util.Date());
	}

	public static final String getStrCurrMonth() {
		return FormatDatetime("MM", new java.util.Date());
	}

	public static final String getStrCurrDay() {
		return FormatDatetime("dd", new java.util.Date());
	}

	public static final String getStrCurrHour() {
		return FormatDatetime("HH", new java.util.Date());
	}

	public static final String getStrCurrMinute() {
		return FormatDatetime("mm", new java.util.Date());
	}

	public static final int getIntCurrYear() {
		return Integer.parseInt(getStrCurrYear());
	}

	public static final int getIntCurrMonth() {
		return Integer.parseInt(getStrCurrMonth());
	}

	public static final int getIntCurrDay() {
		return Integer.parseInt(getStrCurrDay());
	}

	public static final int getIntCurrHour() {
		return Integer.parseInt(getStrCurrHour());
	}

	public static final int getIntCurrMinute() {
		return Integer.parseInt(getStrCurrMinute());
	}

	public int getSeason() {
		int i = getIntCurrMonth();
		if ((1 <= i) && (i <= 3))
			return 1;
		if ((4 <= i) && (i <= 6))
			return 2;
		return (7 > i) || (i > 9) ? 4 : 3;
	}

	public static String differDate(String dateStr) throws Exception {
		if ((dateStr == null) || (dateStr.equals(""))) {
			return "";
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = sdf.parse(dateStr);
		c.setTime(date1);
		long day1 = c.getTimeInMillis();
		java.util.Date currentDate = new java.util.Date();
		c.setTime(currentDate);
		long day2 = c.getTimeInMillis();
		Double differDate2 = Double.valueOf((day1 - day2) * 1.0D / 1000.0D / 60.0D / 60.0D / 24.0D);
		if (differDate2.doubleValue() >= 1.0D) {
			return differDate2.longValue() + "天";
		}
		differDate2 = Double.valueOf(differDate2.doubleValue() * 24.0D);
		if (differDate2.doubleValue() >= 1.0D) {
			return differDate2.longValue() + "小时";
		}
		return (int) (differDate2.doubleValue() * 60.0D) + "分钟";
	}

	public static long getdatediff(String dt1, String dt2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = sdf.parse(dt1);
		java.util.Date mydate = sdf.parse(dt2);
		long day = (date.getTime() - mydate.getTime()) / 86400000L;
		return day;
	}

	public static java.util.Date getAfterCountMonth(int monthCount) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(2, monthCount);
		return calendar.getTime();
	}

	public static boolean getDateCompare(String dt1, String dt2, int day) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(df.parse(dt1));
		c2.setTime(df.parse(dt2));
		c1.add(5, day);
		int result = c1.compareTo(c2);
		if (result > 0) {
			return false;
		}
		return true;
	}

	public static java.util.Date getAfterSevenDay(int day) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.add(5, day);
		return calendar.getTime();
	}

	public static String getAfterSevenDay(String str, int day) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(str));
		calendar.add(5, day);
		return df.format(calendar.getTime());
	}

	public static String getAfterSevenMonth(String str, int day) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(str));
		calendar.add(2, day);
		return df.format(calendar.getTime());
	}

	public static String getAfterSeDay(String str, int day) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(str));
		calendar.add(5, day);
		return df.format(calendar.getTime());
	}

	public static int getMonth(java.util.Date start, java.util.Date end) {
		if (start.after(end)) {
			java.util.Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(5, 1);

		int year = endCalendar.get(1) - startCalendar.get(1);
		int month = endCalendar.get(2) - startCalendar.get(2);
		if ((startCalendar.get(5) == 1) && (temp.get(5) == 1))
			return year * 12 + month + 1;
		if ((startCalendar.get(5) != 1) && (temp.get(5) == 1)) {
			return year * 12 + month;
		}
		if (year * 12 + month - 1 < 0) {
			return 0;
		}
		return year * 12 + month;
	}

	public static long differDate1(String dateStr) throws Exception {
		if ((dateStr == null) || (dateStr.equals(""))) {
			return 0L;
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = sdf.parse(dateStr);
		c.setTime(date1);
		long day1 = c.getTimeInMillis();
		java.util.Date currentDate = new java.util.Date();
		c.setTime(currentDate);
		long day2 = c.getTimeInMillis();
		Double differDate2 = Double.valueOf((day1 - day2) * 1.0D / 1000.0D / 60.0D / 60.0D / 24.0D);
		return (long) (differDate2.doubleValue() * 60.0D * 60.0D * 24.0D);
	}

	public static long differDate3(String dateStr) throws Exception {
		if ((dateStr == null) || (dateStr.equals(""))) {
			return 0L;
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = sdf.parse(dateStr);
		c.setTime(date1);
		long day1 = c.getTimeInMillis();
		java.util.Date currentDate = new java.util.Date();
		c.setTime(currentDate);
		long day2 = c.getTimeInMillis();
		Double differDate2 = Double.valueOf((day1 - day2) * 1.0D / 1000.0D / 60.0D / 60.0D / 24.0D);

		return (long) (differDate2.doubleValue() * 24.0D);
	}

	public static long differDatetoMS(java.util.Date date1, java.util.Date date2) throws Exception {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		return time1 - time2;
	}

	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			java.util.Date dt1 = df.parse(DATE1);
			java.util.Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime())
				return 1;
			if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
			return 0;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static int compare_date(String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Calendar calendar = Calendar.getInstance();
			String currentDate = df.format(calendar.getTime());
			java.util.Date dt1 = df.parse(DATE2);
			java.util.Date dt2 = df.parse(currentDate);
			if (dt1.getTime() > dt2.getTime())
				return 1;
			if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
			return 0;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static int getCurrentPeriodNo(String firstOpenTime, long lPeriod) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int currentPeriodNo = 0;
		try {
			java.util.Date dt1 = df.parse(firstOpenTime);
			java.util.Date dt2 = df.parse(NowStr());
			long ldt1 = dt1.getTime();
			long ldt2 = dt2.getTime();
			if (ldt1 <= ldt2) {
				currentPeriodNo = (int) ((ldt2 - ldt1) / (lPeriod * 3600L * 1000L) + 1L);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return currentPeriodNo;
	}

	public static int compareDate(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar calendar = Calendar.getInstance();
			String currentDate = df.format(calendar.getTime());
			java.util.Date dt1 = df.parse(date);
			java.util.Date dt2 = df.parse(currentDate);
			if (dt1.getTime() > dt2.getTime())
				return 1;
			if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
			return 0;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static int compareDate(String DATE1, String DATE2, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			java.util.Date dt1 = df.parse(DATE1);
			java.util.Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime())
				return 1;
			if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
			return 0;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static String getDatetimetoStr(String dateStr) {
		String dateStr1 = "";
		if (StringUtil.hasText(dateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				if (dateStr.indexOf("-") < 0) {
					dateStr1 = DatetimetoStr(sdf.parse(dateStr));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return dateStr1;
	}

	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(5, calendar.getActualMinimum(5));
		return DatetoStr(calendar.getTime());
	}

	public static String getUpMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(2, -1);
		calendar.set(5, calendar.getActualMinimum(5));
		return DatetoStr(calendar.getTime());
	}

	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(5, calendar.getActualMaximum(5));
		return DatetoStr(calendar.getTime());
	}

	public static String getUpMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(2, -1);
		calendar.set(5, calendar.getActualMaximum(5));
		return DatetoStr(calendar.getTime());
	}

	public static String getWeekFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(7, calendar.getActualMinimum(7));
		return DatetoStr(calendar.getTime());
	}

	public static String getWeekLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(7, calendar.getActualMaximum(7));
		return DatetoStr(calendar.getTime());
	}

	public static String dateFormatConvert(String str) {
		String str2 = "";
		if ((str == "") || (str == null)) {
			return str2;
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
			java.util.Date d = f.parse(str);
			SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return f1.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str2;
	}

	public static long differDate2(String dateStr) throws Exception {
		if ((dateStr == null) || (dateStr.equals(""))) {
			return -1L;
		}
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = sdf.parse(dateStr);
		c.setTime(date1);
		long day1 = c.getTimeInMillis();
		java.util.Date currentDate = new java.util.Date();
		c.setTime(currentDate);
		long day2 = c.getTimeInMillis();
		Double differDate2 = Double.valueOf((day2 - day1) * 1.0D / 1000.0D / 60.0D / 60.0D / 24.0D);
		if (differDate2.doubleValue() >= 1.0D) {
			return differDate2.longValue();
		}
		return -1L;
	}

	public static String getDateStr(String date, long s) {
		String time = "";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long timeStart = formatter.parse(date).getTime();
			time = formatter.format(new java.util.Date(timeStart + s));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public static String getAfterHours(String date, int hours) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(df.parse(date));
		calendar.add(10, hours);
		return df.format(calendar.getTime());
	}

	public static final String DatetoStr2(java.util.Date date) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		return simpledateformat.format(date);
	}

	public static String getDateStr(java.util.Date date, String s) {
		SimpleDateFormat simpledateformat;
		return (simpledateformat = new SimpleDateFormat(s)).format(date);
	}

	public static String getDateStr(long l, String s) {
		SimpleDateFormat simpledateformat;
		return (simpledateformat = new SimpleDateFormat(s)).format(new java.util.Date(l));
	}

	public static String getNow(String s) {
		return getDateStr(System.currentTimeMillis(), s);
	}

	public static java.util.Date getDate(String s, String s1, long l) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s1);
		java.util.Date date;
		try {
			date = simpledateformat.parse(s);
		} catch (Exception _ex) {
			date = new java.util.Date(l);
		}
		return date;
	}

	public static java.util.Date getDate(String s, String s1) {
		return getDate(s, s1, 0L);
	}

	public static long getTime(String s, String s1, long l) {
		return getDate(s, s1, l).getTime();
	}

	public static long getTime(String s, String s1) {
		return getTime(s, s1, 0L);
	}

	public static String convert(String s, String s1, String s2) {
		java.util.Date date = getDate(s, s1);
		if (null == date) {
			return "";
		}
		return getDateStr(date, s2);
	}

	public static String convert(String s, String s1, String s2, char c) {
		char[] ac = s.toCharArray();
		for (int i = 0; i < ac.length; i++) {
			if (c == s.charAt(i)) {
				ac[i] = '-';
			}
		}
		java.util.Date date = getDate(new String(ac), s1.replace(c, '-'));
		char[] ac1 = null != date ? getDateStr(date, s2.replace(c, '-')).toCharArray() : new char[0];
		for (int j = 0; j < ac1.length; j++) {
			if (c == s.charAt(j)) {
				ac1[j] = s.charAt(j);
			}
		}
		return new String(ac1);
	}

	public static String getSeason(String s, String s1) {
		String s2 = "";
		java.util.Date date = getDate(s, s1);
		GregorianCalendar gregoriancalendar = new GregorianCalendar();
		String s3 = "yyyy";
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s3);
		s2 = s2 + simpledateformat.format(date);
		gregoriancalendar.setTime(date);
		return s2 += getSeason(gregoriancalendar.get(2));
	}

	private static String getSeason(int i) {
		switch (i) {
		case 0:
			return "01";
		case 1:
			return "01";
		case 2:
			return "01";
		case 3:
			return "02";
		case 4:
			return "02";
		case 5:
			return "02";
		case 6:
			return "03";
		case 7:
			return "03";
		case 8:
			return "03";
		case 9:
			return "04";
		case 10:
			return "04";
		case 11:
			return "04";
		}
		return "";
	}

	public static int getDayInterval(long l, long l1) {
		int i;
		return i = (int) ((l - l1) / 86400000L);
	}

	public static long addDays(long l, int i) {
		return l + i * 86400000L;
	}

	public static long startOfDay(long l) {
		String s;
		return getDate(s = getDateStr(l, "yyyyMMdd"), "yyyyMMdd").getTime();
	}

	public static long endOfDay(long l) {
		return startOfDay(l) + 86400000L - 1L;
	}

	public static String getDayOfWeekStr(long l) {
		GregorianCalendar gregoriancalendar;
		(gregoriancalendar = new GregorianCalendar()).setTimeInMillis(l);
		int i = gregoriancalendar.get(7);
		String s = "";
		switch (i) {
		case 1:
			s = "周日";
			break;
		case 2:
			s = "周一";
			break;
		case 3:
			s = "周二";
			break;
		case 4:
			s = "周三";
			break;
		case 5:
			s = "周四";
			break;
		case 6:
			s = "周五";
			break;
		case 7:
			s = "周六";
		}
		return s;
	}

	@SuppressWarnings("deprecation")
	public static boolean checkCurTimeIsAPeriod(String startTime, String endTime) {
		java.util.Date nowTime = new java.util.Date();
		String time = nowTime.getHours() + ":" + nowTime.getMinutes();
		if ((time.compareTo(startTime) >= 0) && (time.compareTo(endTime) <= 0)) {
			return true;
		}
		return false;
	}

	public static String get14StrCurrentTime() {
		java.util.Date date = new java.util.Date();
		String s = "";
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		s = s + simpledateformat.format(date);
		simpledateformat = new SimpleDateFormat("HHmmss");
		return s += simpledateformat.format(date);
	}

	public static String get14StrTime(Calendar calendar) {
		java.util.Date date = calendar.getTime();
		String s = "";
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		s = s + simpledateformat.format(date);
		simpledateformat = new SimpleDateFormat("HHmmss");
		return s += simpledateformat.format(date);
	}

	public static String getTimeStrBySplitor(String s, String s1) {
		if ((s == null) || (s.length() < 8)) {
			return s;
		}
		if (s.length() < 14) {
			return s.substring(0, 4) + s1 + s.substring(4, 6) + s1 + s.substring(6, 8);
		}
		return s.substring(0, 4) + s1 + s.substring(4, 6) + s1 + s.substring(6, 8) + " " + s.substring(8, 10) + ":" + s.substring(10, 12) + ":" + s.substring(12, 14);
	}

	public static String get14Str(String s) {
		return s = StringUtil.replace(s = StringUtil.replace(s = StringUtil.replace(s, "-", ""), ":", ""), " ", "");
	}

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(cal.getTime());
		return today;
	}

	@SuppressWarnings("deprecation")
	public static String getDateFormString(String s) {
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = dateFm.format(new java.util.Date(s));
		return dateTime;
	}

	public static java.util.Date reverse2Date(String date) {
		SimpleDateFormat simple = null;
		switch (date.trim().length()) {
		case 19:
			simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case 10:
			simple = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 8:
			simple = new SimpleDateFormat("HH:mm:ss");
		}

		try {
			return simple.parse(date.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static java.sql.Date reverse2SqlDate(String date) {
		SimpleDateFormat simple = null;
		switch (date.trim().length()) {
		case 19:
			simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case 10:
			simple = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 8:
			simple = new SimpleDateFormat("HH:mm:ss");
			break;
		}

		try {
			return new java.sql.Date(simple.parse(date.trim()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static java.util.Date stringtoDate(String dateStr, String format) {
		java.util.Date d = null;
		if ((dateStr != null) && (dateStr.trim().length() > 0)) {
			SimpleDateFormat formater = new SimpleDateFormat(format);
			try {
				formater.setLenient(false);
				d = formater.parse(dateStr);
			} catch (Exception e) {
				d = null;
			}
		}
		return d;
	}

	public static java.sql.Date getBeforeAfterDate(String datestr, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date olddate = null;
		try {
			df.setLenient(false);
			olddate = new java.sql.Date(df.parse(datestr).getTime());
		} catch (ParseException e) {
			throw new RuntimeException("日期转换错误");
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(1);
		int Month = cal.get(2);
		int Day = cal.get(5);

		int NewDay = Day + day;

		cal.set(1, Year);
		cal.set(2, Month);
		cal.set(5, NewDay);

		return new java.sql.Date(cal.getTimeInMillis());
	}

	public static int getDaysBetween(String beginDate, String endDate, String dateformat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateformat);
			java.util.Date bDate = format.parse(beginDate);
			java.util.Date eDate = format.parse(endDate);
			Calendar d1 = new GregorianCalendar();
			d1.setTime(bDate);
			Calendar d2 = new GregorianCalendar();
			d2.setTime(eDate);
			int days = d2.get(6) - d1.get(6);
			int y2 = d2.get(1);
			if (d1.get(1) != y2) {
				d1 = (Calendar) d1.clone();
				do {
					days += d1.getActualMaximum(6);
					d1.add(1, 1);
				} while (d1.get(1) != y2);
			}
			return days;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int getDaysBetween(String beginDate, String endDate) {
		return getDaysBetween(beginDate, endDate, "yyyy-MM-dd");
	}
}
