package util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class StringUtil {
	private static Logger log = Logger.getLogger(StringUtil.class);

	public static String ASCII2Unicode(String ascii) {

		StringBuffer unicode = new StringBuffer(ascii);

		int code;

		for (int i = 0; i < ascii.length(); i++) {

			code = (int) ascii.charAt(i);

			if ((0xA1 <= code) && (code <= 0xFB))

			unicode.setCharAt(i, (char) (code + 0xD60));

		}

		return unicode.toString();

	}

	public static boolean checkMatchData(String input, String conditionReg) {
		boolean isSuccess = false;
		// data = "a123";
		// conditionReg = "a.*|b.*";
		String data = (input == null ? "" : input.trim());

		if ("*".equals(conditionReg)) return true;

		try {
			if (data.matches(conditionReg)) {
				isSuccess = true;
			}

			// log.debug("NCBUtil.checkMatchData isSuccess::" + isSuccess);
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return isSuccess;
	}

	public static boolean contains(String source, char search) {
		if (isEmpty(source)) {
			return false;
		}
		return source.indexOf(search) >= 0;
	}

	public static boolean contains(String source, String search) {
		if (source == null || search == null) {
			return false;
		}
		return source.indexOf(search) >= 0;
	}

	public static String convert08xto66x(String input) {
		String result = "";
		input = getValidString(input);
		if (!"".equals(input)) {
			if (input.startsWith("08"))
				result = "66" + input.substring(1);
			else
				result = input;
		}
		return result;
	}

	public static String convert08xToMsgFormat(String mobileNo) {
		if (mobileNo == null || mobileNo.length() != 10) {
			return mobileNo;
		}
		StringBuffer returnStr = new StringBuffer();
		returnStr.append(mobileNo.substring(0, 3));
		returnStr.append("-");
		returnStr.append(mobileNo.subSequence(3, 6));
		returnStr.append("-");
		returnStr.append(mobileNo.subSequence(6, 10));
		return returnStr.toString();
	}

	public static String convertAsciiToSBMUnicode(String str) {
		StringBuffer ostr = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			/*
			 * if ((ch >= 0x0020) && (ch <= 0x007e)) // Does the char need to be
			 * converted to unicode? { ostr.append(ch); // No. } else {
			 */// Yes.

			// ostr.append("\\u") ; // standard unicode format.
			ostr.append("|u"); // SBM unicode format.
			String hex = Integer.toHexString(str.charAt(i) & 0xFFFF); // Get hex
			// value
			// of
			// the
			// char.
			for (int j = 0; j < 4 - hex.length(); j++)
				// Prepend zeros because unicode requires 4 digits
				ostr.append("0");
			ostr.append(hex.toUpperCase()); // standard unicode format.
			// ostr.append(hex.toLowerCase(Locale.ENGLISH));
			// }
		}
		return (new String(ostr)); // Return the stringbuffer cast as a string.
	}

	public static String convertEmpty(String object) {
		return (object == null) ? "" : object.trim();
	}

	public static String convertSBMUnicodeToAscii(String strCharAll) {

		if (strCharAll == null || "".equals(strCharAll.trim())) {
			return "";
		}

		StringBuffer strReturn = new StringBuffer();

		String[] charAll = StringUtil.splitStringByPipeU(strCharAll);

		for (int i = 0; i < charAll.length; i++) {
			int hex = Integer.parseInt(charAll[i], 16 /* radix */);
			char c = (char) hex;
			strReturn.append(c);
		}

		return strReturn.toString();
	}

	public static int countMatches(String str, String sub) {
		if (isEmpty(str) || isEmpty(sub)) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			count++;
			idx += sub.length();
		}
		return count;
	}

	public static String decodeISO08859_1ToTIS_620(String asRequestParameter) {
		try {
			String sReturn = asRequestParameter;
			sReturn = new String(sReturn.getBytes("ISO8859_1"), "tis-620");
			return (sReturn.replace("$", "%"));
		}
		catch (UnsupportedEncodingException UEE) {
			return (null);
		}
	}

	public static String decodeUTF8ToTIS_620(String asRequestParameter) {
		try {
			String sReturn = asRequestParameter;
			sReturn = new String(sReturn.getBytes("UTF-8"), "tis-620");
			return (sReturn.replace("$", "%"));
		}
		catch (UnsupportedEncodingException UEE) {
			return (null);
		}
	}

	public static String generateStringKey(
			Map<String, Object> keyMap,
				String delimater) {
		StringBuilder sbPattern = new StringBuilder();
		String result = "";
		return result;
	}

	public static String getStackTrace(Throwable throwable) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);
		return writer.toString();
	}

	/*
	 * public static void main(String[] args) { String[] toneCode =
	 * {"12034","12045"}; String oldStr = "</REQUEST>"; String newStr =
	 * "newtone"; String inString =
	 * "<REQUEST><operatoraccount>NewIVR</operatoraccount>" +
	 * "<operatorpwd>T10001</operatorpwd></REQUEST>";
	 * 
	 * String replaceString = StringUtil.replaceToneSetXML(oldStr, newStr,
	 * inString, toneCode); System.out.println(replaceString); }
	 */

	public static String getStackTrace4000(Throwable throwable) {
		try {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			throwable.printStackTrace(printWriter);
			String s = writer.toString();
			if (s != null && s.length() > 3950) s = s.substring(0, 3950);
			return s;
		}
		catch (Exception e) {
			return "Fail to show" + e.getMessage();
		}
	}

	public static String getValidString(Object str) {
		return str == null ? "" : str.toString().trim();
	}

	public static String getValidString(String str) {
		return str == null ? "" : str.trim();
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmpty(Object data) {
		if (getValidString(data).equals("")) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return false;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}

	public static String listStr2StrForSQL(List<String> listStr) {
		String str = "";

		for (int i = 0; i < listStr.size(); i++) {
			str += (0 == str.length() ? "'" + listStr.get(i) + "'" : ", '"
					+ listStr.get(i) + "'");
		}

		return str;
	}

	public static String lpad(String str, int length, String pad) {
		int strLength = str.length();
		for (int i = 0; i < length - strLength; i++) {
			str = pad + str;
		}

		return str;
	}

	public static String lpadZero(int x, int length) {
		String temp = String.valueOf(x);
		int strLength = temp.length();
		for (int i = 0; i < length - strLength; i++) {
			temp = "0" + temp;
		}

		return temp;
	}

	public static void main(String[] a) {
		System.out.println(lpad("2", 2, "0"));
		System.out.println(lpadZero(3, 2));
		String oldStr = "{CODE_NAME}";
		String newStr = "SUCCESS";
		String inString = "{CODE_NAME}";
		inString = replaceString(inString, newStr, inString);
		System.out.println(inString);
	}

	public static String removeFirstDigit(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}
		return str.substring(1);
	}

	/**
	 * This is result code {CODE} forReplace = {CODE} newReplace = 2001
	 * sourceString = This is result code {CODE} result = This is result code
	 * 2001
	 * 
	 * @param forReplace
	 * @param newReplace
	 * @param sourceString
	 * @return
	 */
	public static String replaceString(
			String forReplace,
				String newReplace,
				String sourceString) {
		return replaceXML(forReplace, newReplace, sourceString);
	}

	public static String replaceToneSetXML(
			String oldStr,
				String newToneCode,
				String inString,
				String[] toneCode) {
		int start = inString.indexOf(oldStr);
		if (start == -1) {
			return inString;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(inString.substring(0, start));

		if (toneCode != null) {
			for (String tonecode : toneCode) {
				sb.append("<tonecode>" + tonecode + "</tonecode>");
			}
		}

		sb.append("<tonecode>" + newToneCode + "</tonecode>");

		sb.append(oldStr);

		return sb.toString();
	}

	public static String replaceXML(
			String oldStr,
				String newStr,
				String inString) {
		int start = inString.indexOf(oldStr);
		if (start == -1) {
			return inString;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(inString.substring(0, start));
		sb.append(newStr);
		sb.append(inString.substring(start + oldStr.length()));

		start = sb.indexOf(oldStr);
		if (start != -1) {
			sb = new StringBuffer(replaceXML(oldStr, newStr, sb.toString()));
		}
		return sb.toString();
	}

	public static String[] splitString(String source, String delimiter) {

		if (source == null) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(source, delimiter);
		String[] strArray = new String[st.countTokens()];
		int count = 0;
		while (st.hasMoreTokens()) {
			strArray[count] = st.nextToken();
			count++;
		}
		return strArray;
	}

	public static String[] splitStringByPipe(String source) {

		if (source == null) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(source, "|");
		String[] strArray = new String[st.countTokens()];
		int count = 0;
		while (st.hasMoreTokens()) {
			strArray[count] = st.nextToken();
			count++;
		}
		return strArray;
	}

	public static String[] splitStringByRemark(String source, String remark) {

		StringTokenizer st = new StringTokenizer(source.trim(), remark.trim());
		String[] strArray = new String[st.countTokens()];
		int count = 0;
		while (st.hasMoreTokens()) {
			strArray[count] = st.nextToken();
			count++;
		}
		return strArray;
	}

	public static String Unicode2ASCII(String unicode) {

		StringBuffer ascii = new StringBuffer(unicode);

		int code;

		for (int i = 0; i < unicode.length(); i++) {

			code = (int) unicode.charAt(i);

			if ((0xE01 <= code) && (code <= 0xE5B))

			ascii.setCharAt(i, (char) (code - 0xD60));

		}

		return ascii.toString();

	}

	private static String[] splitStringByPipeU(String source) {

		if (source == null) {
			return null;
		}

		StringTokenizer st = new StringTokenizer(source, "|u");
		String[] strArray = new String[st.countTokens()];
		int count = 0;
		while (st.hasMoreTokens()) {
			strArray[count] = st.nextToken();
			count++;
		}
		return strArray;
	}

	public static String capture(String line, String matcher) {

		StringBuilder sb = new StringBuilder();
		boolean finish = false;

		int x = line.indexOf(matcher);
		String temp = null;
		if (x > -1) {
			temp = line.substring(x + (matcher.length()));
		}
		else {
			return "";
		}
		char[] l = temp.toCharArray();
		for (char c : l) {
			if ('/' == c) {
				finish = true;
			}
			if (finish) {
				break;
			}
			else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String capture(
			String line,
				String matcher,
				boolean checkLength) {
		String result = "";
		int start = 0;
		int decre = 6;
		int end = 0;
		String temp = "";
		// check has macther character
		if (line.indexOf(matcher) == -1) {
			return "";
		}
		// check ')' before matcher
		temp = line.substring((line.indexOf(matcher) - 1));
		if (!(temp.startsWith(")"))) {
			// bypass to normal capture
			return capture(line, matcher);
		}
		else {
			temp = "";
		}
		try {
			start = line.indexOf(matcher) - decre;
			if (start < 0) {
				start = 0;
			}
			end = line.indexOf(matcher);
			if (end >= line.length()) {
				end = line.length();
			}
			temp = line.substring(start, end);

			if (temp.indexOf("(") > -1) {
				temp = temp.substring((temp.indexOf("(") + 1), temp
						.lastIndexOf(")"));
				int indexStartCut = Integer.parseInt(temp);
				result = line.substring(end + matcher.length(), ((end + matcher
						.length()) + indexStartCut));

			}
			else {
				result = capture(line, matcher);
			}
			// System.out.println("Complete matcher ("+matcher +") @: " + line +
			// " start : " + start + ", end : "
			// + end);
		}
		catch (Exception e) {
			log.error("Error :" + e.getMessage(), e);
		}
		return result == null ? "" : result;

	}

	public static String captureKeyValue(String line, String key, char enclosed) {

		StringBuilder sb = new StringBuilder();
		boolean finish = false;

		int x = line.indexOf(key);
		String temp = null;
		if (x > -1) {
			temp = line.substring(x + (key.length()));
		}
		else {
			return "";
		}
		for (int i = 0; i < temp.length(); i++) {
			char c = temp.charAt(i);
			if (enclosed == c) {
				finish = true;
			}
			if (finish) {
				break;
			}
			else {
				if (!('=' == c) && !('\'' == c)) {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}
}
