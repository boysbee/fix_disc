package util;

import org.apache.commons.lang.StringUtils;

public class MobilePhoneUtils {
	public static final String _STR_668 = "668";
	public static final String _STR_08 = "08";
	public static final String _STR_0 = "0";
	public static final String _STR_66 = "66";

	public static String replace668(String str) {
		if (StringUtils.isBlank(str)) {
			throw new NullPointerException();
		}
		if (str.startsWith(_STR_668)) {
			return _STR_0 + str.substring(2);
		}
		return str;
	}

	public static String replace08(String str) {
		if (StringUtils.isBlank(str)) {
			throw new NullPointerException();
		}
		if (str.startsWith(_STR_08)) {
			return _STR_66 + str.substring(1);
		}
		return str;
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
}
