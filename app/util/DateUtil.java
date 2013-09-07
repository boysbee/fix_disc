package util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.log4j.Logger;

public class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);

	public static final String dateFormatStrShow = "yyyy/MM/dd HH:mm:ss";
	public static final String dateFormatStr_yyyyMMddHHmmsss = "dd/MM/yyyy HH:mm:sss";
	public static final String dateFormatCriteriaStr = "yyyy-MM-dd";
	public static final String dateFormatStrDB = "yyyy-MM-dd HH:mm:ss";
	public static final String dateFormatShortStr = "dd/MM/yyyy";
	public static final String dateFormatShortStr2 = "dd/MM/yy";
	public static final String dateFormatJS = "MM/dd/yyyy";
	public static final String dateTimeFormatStr = "dd/MM/yyyy HH:mm:ss";
	public static final String dateTimeStampFormatStr = "yyyy/MM/dd HH:mm:ss";
	public static final String dateTimeFormatMillisec = "dd/MM/yyyy HH:mm:S";
	public static final String dateTimeNoSecFormatStr = "dd/MM/yyyy HH:mm";
	public static final String dateTimeNoSecFormatStr2 = "yyyyMMddHHmm";
	public static final String timeFormatStr = "mm:ss:SSS";
	public static final String dateFormat_ddMMyyyy = "ddMMyyyy";
	public static final String dateFormat_yyyyMMdd = "yyyyMMdd";
	public static final String dateFormat_yyyyMMddHH = "yyyyMMddHH";
	public static final String dateFormat_MMddyyyy_HH24MISS = "MM/dd/yyyy HH:mm:ss";
	public static final String dateFormat_MMddyyyy = dateFormatJS;
	public static final String dateFormatFullStr = "dd MMMM yyyy";
	public static final String shortTimeFormat = "HH.mm";
	public static final String shortTimeFormatHHMM = "HH:mm";
	public static final String shortTimeFormatHHmmss = "HHmmss";
	public static final String dateTimeFormat_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String dateTimeFormat_yyyyMMddHHmmsss = "yyyyMMddHHmmsss";
	public static final String dateTimeFormat_yyyyMMddHHmmSSS = "yyyyMMddHHmmSSS";
	public static final String dateTimeFormat_yyyyMMddHH = "yyyyMMddHH";
	public static final String dateTimeFormat_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String dateTimeFormat_yyyyMMddHHmmS = "yyyyMMddHHmmS";
	public static final String dateTimeFormat_yyyyMMddHHmm = "yyyyMMddHHmm";
	public static final String dateTimeZoneFormat_yyyyMMddHHmmssZ = "yyyy-MM-dd'T'HH:mm:ssZ";
	
	public static final String dateFormatJQuerDatePicker = "dd-MM-yyyy";
	public static final String dateFormatSQL = "MM/dd/yyyy HH:mm:ss";
	public static final String dateTimeZoneFormat_yyyyMMddHHmmssSSSZ ="yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	public static int dayOfWeek() {
		return new GregorianCalendar(Locale.US).get(Calendar.DAY_OF_WEEK);
	}

	public static int dayOfWeek(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.DAY_OF_WEEK);
	}

	public static int weekOfYear(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.WEEK_OF_YEAR);
	}

	public static String weekOfYearStr(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int wkY = cl.get(Calendar.WEEK_OF_YEAR);
		String wkYear = (wkY < 10) ? StringUtil.lpadZero(wkY, 2) : String
				.valueOf(wkY);
		return wkYear;
	}

	public static int weekOfMonth(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.WEEK_OF_MONTH);
	}

	public static String weekOfMonthStr(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int wkM = cl.get(Calendar.WEEK_OF_MONTH);
		String wkMonth = (wkM < 10) ? StringUtil.lpadZero(wkM, 2) : String
				.valueOf(wkM);
		return wkMonth;
	}

	public static int getHour(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.HOUR_OF_DAY);
	}

	public static String getHourStr(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int hh = cl.get(Calendar.HOUR_OF_DAY);
		String hour = (hh < 10) ? StringUtil.lpadZero(hh, 2) : String
				.valueOf(hh);
		return hour;
	}

	public static int getMinutes(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.MINUTE);
	}

	public static String getMinutesStr(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int min = cl.get(Calendar.MINUTE);
		String minute = (min < 10) ? StringUtil.lpadZero(min, 2) : String
				.valueOf(min);
		return minute;
	}

	public static String getSecondStr(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int sec = cl.get(Calendar.SECOND);
		String second = (sec < 10) ? StringUtil.lpadZero(sec, 2) : String
				.valueOf(sec);
		return second;
	}

	public static int getSecond(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int sec = cl.get(Calendar.SECOND);
		return sec;
	}

	public static Date addHourAndRound(Date date, int inc) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.HOUR, inc);
		cl.set(Calendar.SECOND, 0);
		cl.set(Calendar.MINUTE, 0);
		return cl.getTime();

	}

	public static String getYearStr(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return String.valueOf(cl.get(cl.YEAR));

	}

	public static int getYear(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(cl.YEAR);

	}

	public static String getMonthStr(Date date) {
		String month = "";
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int mnth = cl.get(cl.MONTH) + 1;
		month = (mnth < 10) ? StringUtil.lpadZero(mnth, 2) : String
				.valueOf(mnth);
		return month;

	}

	public static int getMonth(Date date) {
		String month = "";
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int mnth = cl.get(cl.MONTH) + 1;
		return mnth;

	}

	public static String getDateStr(Date date) {
		String day = "";
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int d = cl.get(cl.DATE);
		day = (d) < 10 ? StringUtil.lpadZero(d, 2) : String.valueOf(d);
		return day;

	}

	public static int getDate(Date date) {
		String day = "";
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		int d = cl.get(cl.DATE);
		return d;
	}

	public static String getYearStr(String date) {
		Date dt = str2Date(date, dateFormat_yyyyMMdd);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		return String.valueOf(cl.get(cl.YEAR));

	}

	public static int getYear(String date) {
		Date dt = str2Date(date, dateFormat_yyyyMMdd);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		return cl.get(cl.YEAR);

	}

	public static String getMonthStr(String date) {
		String month = "";
		Date dt = str2Date(date, dateFormat_yyyyMMdd);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		int mnth = cl.get(cl.MONTH) + 1;
		month = (mnth < 10) ? StringUtil.lpadZero(mnth, 2) : String
				.valueOf(mnth);
		return month;

	}

	public static int getMonth(String date) {
		String month = "";
		Date dt = str2Date(date, dateFormat_yyyyMMdd);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		int mnth = cl.get(cl.MONTH) + 1;
		return mnth;

	}

	public static String getDateStr(String date) {
		String day = "";
		Date dt = str2Date(date, dateFormat_yyyyMMdd);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		int d = cl.get(cl.DATE);
		day = (d) < 10 ? StringUtil.lpadZero(d, 2) : String.valueOf(d);
		return day;

	}

	public static int getDate(String date) {
		String day = "";
		Date dt = str2Date(date, dateFormat_yyyyMMdd);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		int d = cl.get(cl.DATE);
		return d;

	}

	public static String getYear(String date, String pattern) {
		Date dt = str2Date(date, pattern);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);

		return String.valueOf(cl.get(cl.YEAR));

	}

	public static String getMonth(String date, String pattern) {
		String month = "";
		Date dt = str2Date(date, pattern);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		int mnth = cl.get(cl.MONTH) + 1;
		month = (mnth < 10) ? StringUtil.lpadZero(mnth, 2) : String
				.valueOf(mnth);
		return month;

	}

	public static String getDate(String date, String pattern) {
		String day = "";
		Date dt = str2Date(date, pattern);
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		int d = cl.get(cl.DATE);
		day = (d) < 10 ? StringUtil.lpadZero(d, 2) : String.valueOf(d);
		return day;

	}

	public static Date addSecond(Date date, int inc) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.SECOND, inc);
		return cl.getTime();
	}

	public static Date addMinute(Date date, int inc) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MINUTE, inc);
		return cl.getTime();
	}

	public static Date addHour(Date date, int inc) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.HOUR, inc);
		return cl.getTime();
	}

	public static Date addDate(Date dt, int inc) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		cl.add(Calendar.DATE, inc);
		return cl.getTime();
	}

	public static Date addMonth(Date dt, int inc) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		cl.add(Calendar.MONTH, inc);
		return cl.getTime();
	}

	public static Date addYear(Date dt, int inc) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		cl.add(Calendar.YEAR, inc);
		return cl.getTime();
	}

	public static boolean checkDateAfter(Date dt1, Date dt2) {
		Calendar cl1 = Calendar.getInstance();
		cl1.setTime(dt1);
		Calendar cl2 = Calendar.getInstance();
		cl2.setTime(dt2);

		return cl1.after(cl2);

	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(new GregorianCalendar(Locale.US).getTimeInMillis());
	}

	public static Date timestampToDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public static Timestamp dateToTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static String format(Date dt, String pattern) {
		String strDate = "";
		try {
			if (dt != null) {
				Format formatter = new SimpleDateFormat(pattern, Locale.US);
				strDate = formatter.format(dt);
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return strDate.toUpperCase();
	}

	public static String format(Date dt, String pattern, Locale lc) {
		String strDate = "";
		try {
			if (dt != null) {
				Format formatter = new SimpleDateFormat(pattern, lc);
				strDate = formatter.format(dt);
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return strDate;
	}

	public static java.util.Date getCurrentDateTime() {
		java.util.Date date = null;
		try {
			Date currentdate = Calendar.getInstance(Locale.US).getTime();

			DateFormat df = new SimpleDateFormat(dateTimeFormatStr, Locale.US);
			date = df.parse(df.format(currentdate));
		}
		catch (ParseException e) {
			// log.debug(".getCurrentDateTime" + "\n----- ParseException :" +
			// e.getMessage());
		}
		return date;

	}

	public static java.util.Date getCurrentDate() {
		java.util.Date date = null;
		try {
			Date currentdate = Calendar.getInstance(Locale.US).getTime();
			DateFormat dateFormat = new SimpleDateFormat(dateFormatShortStr,
					Locale.US);
			date = dateFormat.parse(dateFormat.format(currentdate));
		}
		catch (ParseException e) {
			// log.debug(".getCurrentDate" + "\n----- ParseException :" +
			// e.getMessage());
		}
		return date;

	}

	public static String getCurrentDateStr() {
		java.util.Date date = null;
		String str = "";
		try {
			Date currentdate = Calendar.getInstance(Locale.US).getTime();
			DateFormat dateFormat = new SimpleDateFormat(dateFormatShortStr,
					Locale.US);
			date = dateFormat.parse(dateFormat.format(currentdate));
			str = date2Str(date, dateFormatShortStr);
		}
		catch (ParseException e) {
			// log.debug(".getCurrentDate" + "\n----- ParseException :" +
			// e.getMessage());
		}
		return str;

	}

	public static String getCurrentDateStr(String pattern) {
		java.util.Date date = null;
		String str = "";
		try {
			Date currentdate = Calendar.getInstance(Locale.US).getTime();
			DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
			date = dateFormat.parse(dateFormat.format(currentdate));
			str = date2Str(date, pattern);
		}
		catch (ParseException e) {
			// log.debug(".getCurrentDate" + "\n----- ParseException :" +
			// e.getMessage());
		}
		return str;

	}

	public static java.util.Date getYesterday() {
		return addDate(getCurrentDate(), -1);

	}

	public static Date string2Date(String dateStr, String formatDateStr)
			throws ParseException {
		if ("".equals(StringUtil.getValidString(dateStr))) return null;

		java.util.Date date = null;
		java.util.Calendar currentTime = Calendar.getInstance(Locale.US);

		try {
			DateFormat fdate = new SimpleDateFormat(formatDateStr);
			fdate.setCalendar(currentTime);
			date = fdate.parse(dateStr);
		}
		catch (ParseException e) {
			throw e;
		}
		return date;
	}

	public static Date str2Date(String dateStr, String formatDateStr) {
		if ("".equals(StringUtil.getValidString(dateStr))) return null;

		java.util.Date date = null;
		java.util.Calendar currentTime = Calendar.getInstance(Locale.US);

		try {
			DateFormat fdate = new SimpleDateFormat(formatDateStr);
			fdate.setCalendar(currentTime);
			date = fdate.parse(dateStr);
		}
		catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return date;
	}
	public static Date convertDate(String dateStr, String sourceFormat,String expectedFormat) {
		if ("".equals(StringUtil.getValidString(dateStr))) return null;
		
		Date conv = str2Date(dateStr, sourceFormat);
		
		String conv2 = date2Str(conv, expectedFormat);
		
		return str2Date(conv2, expectedFormat);
		
	}
	public static String date2Str(java.util.Date date, String formatDateStr) {
		if ("".equals(StringUtil.getValidString(formatDateStr)) || date == null)
			return "";

		String result = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(formatDateStr,
					Locale.US);
			result = dateFormat.format(date);
		}
		catch (Exception e) {
			// log.debug(".date2Str" + "\n----- Exception :" + e.getMessage());
		}
		return result;

	}

	public static Date getEndDateOfMonth() {
		java.util.Date endDateOfMonth = null;
		Calendar calendar = Calendar.getInstance();
		try {
			// int year = calendar.YEAR;
			// int month = calendar.MONTH;
			int lastDate = calendar.getActualMaximum(Calendar.DATE);
			calendar.set(Calendar.DATE, lastDate);
			endDateOfMonth = calendar.getTime();
		}
		catch (Exception e) {
			// log.debug(".getEndDateOfMonth" + "\n----- ParseException :" +
			// e.getMessage());
		}
		return endDateOfMonth;
	}

	public static Date getEndDateOfYear() {
		Calendar calendar = Calendar.getInstance();
		String currentDate = DateUtil.format(calendar.getTime(),
				DateUtil.dateFormatShortStr);
		String[] ddMMyyyy = currentDate.split("/");
		calendar.set(Calendar.YEAR, Integer.parseInt(ddMMyyyy[2]));
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DATE, 31);

		return calendar.getTime();
	}

	@SuppressWarnings("deprecation")
	public static Date calcStartAndEndDatePeriod(
			Date startDate,
				Character validityUnit,
				int validity) {
		// Date startDate = DateUtil.getCurrentDateTime();
		Date stopDate = null;

		// Character validityUnit = dbfPricePlan.getValidityUnit();
		if (validityUnit == 'H') {
			stopDate = DateUtil.addSecond(
					DateUtil.addHour(startDate, validity), -1);
		}
		else if (validityUnit == 'D') {
			Date cloneDate = (Date) ObjectUtils.deepCopy(startDate);

			cloneDate.setHours(0);
			cloneDate.setMinutes(0);
			cloneDate.setSeconds(0);

			// Oui Add code for Clear Millisec because error when compare date
			long longTime = cloneDate.getTime();
			longTime = (longTime / 1000);
			longTime = Long.parseLong(longTime + "000");
			cloneDate.setTime(longTime);
			// ( End ) Oui Add code for Clear Millisec because error when

			stopDate = DateUtil.addSecond(
					DateUtil.addDate(cloneDate, validity), -1);
		}
		else if (validityUnit == 'M') {
			Date cloneDate = (Date) ObjectUtils.deepCopy(startDate);

			cloneDate.setHours(0);
			cloneDate.setMinutes(0);
			cloneDate.setSeconds(0);

			stopDate = DateUtil.addSecond(DateUtil
					.addMonth(cloneDate, validity), -1);
			stopDate = DateUtil.getEndDateOfMonth();
		}
		else if (validityUnit == 'Y') {
			Date cloneDate = (Date) ObjectUtils.deepCopy(startDate);

			cloneDate.setHours(0);
			cloneDate.setMinutes(0);
			cloneDate.setSeconds(0);

			stopDate = DateUtil.addSecond(
					DateUtil.addYear(cloneDate, validity), -1);
			stopDate = DateUtil.getEndDateOfYear();
		}
		else {
			stopDate = startDate;
		}

		return stopDate;
	}

	public static String getUsedTime(long theStartTime, long theEndTime) {
		SimpleDateFormat TIME_FORMAT_FOR_SHOW = new SimpleDateFormat(
				timeFormatStr, Locale.ENGLISH);
		long usedTime = theEndTime - theStartTime;
		Date dateUsedTime = new Date(usedTime);
		String output = TIME_FORMAT_FOR_SHOW.format(dateUsedTime);
		// log.debug("--->Time Used = [" + output + "]");
		return output;
	}

	public static String dateDiff(java.util.Date date1, java.util.Date date2) {
		String result = "";
		try {
			Calendar calendarDate = Calendar.getInstance(Locale.US);
			calendarDate.setTime(date2);

			int date2Year = calendarDate.get(Calendar.YEAR);
			int date2Month = calendarDate.get(Calendar.MONTH);
			int date2Day = calendarDate.get(Calendar.DATE);

			Calendar calendarBirthDate = Calendar.getInstance(Locale.US);
			calendarBirthDate.setTime(date1);

			int date1Year = calendarBirthDate.get(Calendar.YEAR);
			int date1Month = calendarBirthDate.get(Calendar.MONTH);
			int date1Day = calendarBirthDate.get(Calendar.DATE);

			int resultYears = date2Year - date1Year;
			int resultMonths = 0;
			int resultDays = 0;

			if (date2Month >= date1Month)
				resultMonths = date2Month - date1Month;
			else {
				resultYears--;
				resultMonths = date2Month + 12 - date1Month;
			}

			if (date2Day >= date1Day)
				resultDays = date2Day - date1Day;
			else {
				if (resultMonths > 0)
					resultMonths--;
				else {
					resultYears--;
					resultMonths += 11;
				}
				resultDays = date2Day + 31 - date1Day;
			}

			if (resultYears < 0) return "0/0/0";

			if ((resultYears == 0) && (resultMonths == 0) && (resultDays == 0))
				return "0/0/0";

			result = resultYears + "/" + resultMonths + "/" + resultDays;
		}
		catch (Exception e) {
			return "0/0/0";
		}

		return result;
	}

	public static Date calStartBillDate(String day) {
		try {
			Calendar cl = Calendar.getInstance();
			cl.setTime(new Date());
			int dateDay = cl.get(Calendar.DATE);
			int inputDay = Integer.parseInt(day);
			// log.debug("date : "+dateDay);
			cl.set(Calendar.DATE, inputDay);
			cl.set(Calendar.HOUR, 0);
			cl.set(Calendar.MINUTE, 0);
			cl.set(Calendar.SECOND, 0);
			// log.debug("cl : "+cl.getTime());
			Date date = null;
			if (inputDay <= dateDay) {
				date = cl.getTime();
				return cl.getTime();
			}
			else {
				date = DateUtil.addMonth(cl.getTime(), -1);
				return DateUtil.addMonth(cl.getTime(), -1);
			}
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	public static long diffDay(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(date1);
		c2.setTime(date2);

		long milliseconds1 = c1.getTimeInMillis();
		long milliseconds2 = c2.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		// if diff long value > -86400000 return -1
		if ((diff < 0) && (diff > -86400000)) {
			return -1;
		}
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

	/**
	 * Get the hours difference
	 */
	public static double getDifferenceDate(
			Date earlierDate,
				Date laterDate,
				String mode) {
		if (earlierDate == null || laterDate == null) return 0;

		long milliseconds1 = earlierDate.getTime();
		long milliseconds2 = laterDate.getTime();

		long diff = milliseconds2 - milliseconds1;

		if ("S".equals(mode)) {
			return diff / 1000;
		}
		else if ("M".equals(mode)) {
			return diff / (60 * 1000);
		}
		else if ("H".equals(mode)) {
			long minite = diff / (60 * 1000);
			long hour = (minite / 60);
			long strPrecision = (minite % 60);
			double diffHour = Double.parseDouble(hour + "." + strPrecision);

			return diffHour;
		}
		else if ("D".equals(mode)) {
			long minite = diff / (60 * 1000);
			long hour = (minite / 60);
			long scrapsMinite = (minite % 60);
			long day = (hour / 24);
			long scrapsHour = (hour % 24);

			String strHour = "";
			if (scrapsHour < 10) {
				strHour = "0" + scrapsHour;
			}
			else {
				strHour = String.valueOf(scrapsHour);
			}

			String strMinite = "";
			if (scrapsMinite < 10) {
				strMinite = "0" + scrapsMinite;
			}
			else {
				strMinite = String.valueOf(scrapsMinite);
			}

			double diffDay = Double.parseDouble(new StringBuilder("").append(
					day).append(".").append(strHour).append(strMinite)
					.toString());

			// return diff / (24 * 60 * 60 * 1000);
			return diffDay;
		}
		else {
			return 0;
		}
	}

	public static int calDiffCurrentDate(String dateStr) {

		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					dateFormat_ddMMyyyy, Locale.US);
			long diffDay = diffDay(formatter
					.parse(formatter.format(new Date())), formatter
					.parse(dateStr));
			return BigDecimal.valueOf(diffDay).abs().intValue() + 1;
		}
		catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return 0;
	}

	public static String smartParseDate(String date, String pattern) {
		String dateStr = null;
		Date d = null;
		try {
			d = DateUtil.string2Date(date, DateUtil.dateFormatCriteriaStr);
		}
		catch (Exception e) {
			try {
				d = DateUtil.string2Date(date, DateUtil.dateFormatShortStr);
			}
			catch (Exception e1) {
				try {
					d = DateUtil
							.string2Date(date, DateUtil.dateFormatShortStr2);
				}
				catch (Exception e2) {
					try {
						d = DateUtil.string2Date(date, DateUtil.dateFormatJS);
					}
					catch (Exception e3) {
						try {
							d = DateUtil.string2Date(date,
									DateUtil.dateTimeFormatStr);
						}
						catch (Exception e4) {
							try {
								d = DateUtil.string2Date(date,
										DateUtil.dateTimeFormat_yyyyMMddHHmmss);
							}
							catch (Exception e5) {
								try {
									d = DateUtil.string2Date(date,
											DateUtil.dateFormat_yyyyMMdd);
								}
								catch (Exception e6) {
									try {
										d = DateUtil.string2Date(date,
												DateUtil.dateFormat_ddMMyyyy);
									}
									catch (Exception e7) {
										log.error("Error : " + e.getMessage(),
												e);
									}
								}
							}
						}
					}
				}
			}
		}
		dateStr = DateUtil.date2Str(d, pattern);
		return dateStr;
	}

	public static String formatIntoHHMMSS(int secsIn) {

		int hours = secsIn / 3600;
		int remainder = secsIn % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60;

		return ((hours < 10 ? "0" : "") + hours + ":"
				+ (minutes < 10 ? "0" : "") + minutes + ":"
				+ (seconds < 10 ? "0" : "") + seconds);

	}

	public static int[] calHourBetween2Value(int startHour, int endHour) {
		int[] i = null;

		try {
			if (startHour == endHour) {
				i = null;
			}
			else if (startHour < endHour) {
				int diff = endHour - startHour;

				i = new int[diff];
				for (int x = 0; x < diff; x++) {
					i[x] = (startHour + x);
				}
			}
			else if (startHour > endHour) {
				int addEndHour = endHour + 24;

				int diff = addEndHour - startHour;
				i = new int[diff];
				for (int x = 0; x < diff; x++) {
					if ((startHour + x) >= 24) {
						i[x] = ((startHour + x) - 24);
					}
					else {
						i[x] = (startHour + x);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	public static void main(String args[]) throws ParseException {
		String d = DateUtil.date2Str(new Date(),
				DateUtil.dateTimeFormat_yyyyMMddHHmm);
		System.out.println(d);

		String hour = Integer.toString(DateUtil.getHour(new Date()));
		System.out.println(hour);
		String minutes = Integer.toString(DateUtil.getMinutes(new Date()));
		System.out.println(minutes);

		int weekOfYear = DateUtil.weekOfYear(DateUtil.str2Date("1/1/2011",
				"dd/MM/yyyy"));
		System.out.println(weekOfYear);
	}

	public static long dateToLong(Date d) {
		Calendar c = Calendar.getInstance(Locale.US);
		c.setTime(d);
		return c.getTimeInMillis();
	}

	public static String date2yyyyMMWYWMddHHmmss(Date d1) {
		String yyyyMMWYWMddHHmmss = "";
		String year = getYearStr(d1);
		String month = getMonthStr(d1);
		String wy = weekOfYearStr(d1);
		String wm = weekOfMonthStr(d1);
		String d = getDateStr(d1);
		String hh = getHourStr(d1);
		String mm = getMinutesStr(d1);
		String sec = getSecondStr(d1);
		yyyyMMWYWMddHHmmss = year + month + wy + wm + d + hh + mm + sec;
		return yyyyMMWYWMddHHmmss;
	}
	public static String convertPattern(String source,String sourcePattern, String destPattern) {
		Date dSource = str2Date(source, sourcePattern);
		String date2String = date2Str(dSource, destPattern);
		return date2String;
	}
}
