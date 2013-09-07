package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil {

	public static final NumberFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat(
			"#.0#################");
	public static final String doubleFormat2Digits = "#0.00";
	public static final String doubleFormat2DigitsWithComma = "#,##0.00";

	/*
	 * public static String format(long value,String pattern){ NumberFormat
	 * formatter = new DecimalFormat(pattern); return formatter.format(value); }
	 */

	public static String format(double value, String pattern) {
		NumberFormat formatter = new DecimalFormat(pattern);
		return formatter.format(value);
	}

	public static double add(double double1, double double2) {
		String s = DEFAULT_DECIMAL_FORMAT.format(double1);
		BigDecimal bd = new BigDecimal(s);
		return add(bd, double2);
	}

	public static double add(BigDecimal bigDecimal1, double double1) {
		String s = DEFAULT_DECIMAL_FORMAT.format(double1);
		BigDecimal bd = new BigDecimal(s);
		return add(bigDecimal1, bd);
	}

	private static double add(BigDecimal a, BigDecimal b) {
		if (a == null)
			return (b == null) ? 0.0 : b.doubleValue();
		return a.add(b).doubleValue();
	}

	public static double subtract(double double1, double double2) {
		String s = DEFAULT_DECIMAL_FORMAT.format(double1);
		BigDecimal bd = new BigDecimal(s);
		return subtract(bd, double2);
	}

	public static double subtract(BigDecimal bigDecimal1, double double1) {
		String s = DEFAULT_DECIMAL_FORMAT.format(double1);
		BigDecimal bd = new BigDecimal(s);
		return subtract(bigDecimal1, bd);
	}

	private static double subtract(BigDecimal a, BigDecimal b) {
		if (a == null)
			return (b == null) ? 0.0 : b.doubleValue();
		return a.subtract(b).doubleValue();
	}

	public static double multiply(double double1, double double2) {
		String s = DEFAULT_DECIMAL_FORMAT.format(double1);
		BigDecimal bd = new BigDecimal(s);
		return multiply(bd, double2);
	}

	public static double multiply(BigDecimal bigDecimal1, double double1) {
		String s = DEFAULT_DECIMAL_FORMAT.format(double1);
		BigDecimal bd = new BigDecimal(s);
		return multiply(bigDecimal1, bd);
	}

	private static double multiply(BigDecimal a, BigDecimal b) {
		if (a == null)
			return (b == null) ? 0.0 : b.doubleValue();
		return a.multiply(b).doubleValue();
	}

	public static double divide(double double1, double double2) {
		if (double1 == 0) {
			return 0;
		}
		String s = DEFAULT_DECIMAL_FORMAT.format(double1);
		BigDecimal bd = new BigDecimal(s);
		return divide(bd, double2);
	}

	public static double divide(BigDecimal bigDecimal1, double double1) {
		String s = DEFAULT_DECIMAL_FORMAT.format(double1);
		BigDecimal bd = new BigDecimal(s);
		return divide(bigDecimal1, bd);
	}

	private static double divide(BigDecimal a, BigDecimal b) {
		if (a == null)
			return (b == null) ? 0.0 : b.doubleValue();
		return a.divide(b).doubleValue();
	}

	public static BigDecimal str2BigDecimal(String number) {
		number = StringUtil.getValidString(number);
		if (StringUtil.isEmpty(number)) {
			number = "0";
		}
		return new BigDecimal(number);
	}

	public static BigDecimal object2BigDecimal(Object numberObj) {
		String number = StringUtil.getValidString(numberObj);
		if (StringUtil.isEmpty(number)) {
			number = "0";
		}
		return new BigDecimal(number);
	}
}
