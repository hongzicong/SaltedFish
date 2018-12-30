package hongzicong.saltedfish.utils;

import java.security.MessageDigest;

public class EncryptUtil {
	public static String encryptPsd(String password) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("md5");
		byte[] cipherData = md5.digest(password.getBytes());
		StringBuilder builder = new StringBuilder();
		for(byte cipher : cipherData) {
			String toHexStr = Integer.toHexString(cipher & 0xff);
			builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
		}
		return builder.toString();
	}
}
