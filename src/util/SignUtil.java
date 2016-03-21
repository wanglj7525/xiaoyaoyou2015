package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SignUtil {
	// �뿪��ģʽ�ӿ�������Ϣ�е�Token����һ��
	  private static String token = "weixin";

	  /**
	 * У��ǩ��
	 *
	 * @param signature ΢�ż���ǩ��
	 * @param timestamp ʱ���
	 * @param nonce �����
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
	    // ��token��timestamp��nonce���ֵ�����
	    String[] paramArr = new String[] { token, timestamp, nonce };
	    Arrays.sort(paramArr);

	    // �������Ľ��ƴ�ӳ�һ���ַ���
	    String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

	    String ciphertext = null;
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        // ��ƴ�Ӻ���ַ�������sha1����
	        byte[] digest = md.digest(content.toString().getBytes());
	        ciphertext = byteToStr(digest);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }

	    // ��sha1���ܺ���ַ�����signature���жԱ�
	    return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}
	/**
	 * ���ֽ�����ת��Ϊʮ�������ַ���
	 *
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
	     String strDigest = "";
	     for (int i = 0; i < byteArray.length; i++) {
	          strDigest += byteToHexStr(byteArray[i]);
	     }
	     return strDigest;
	}

	/**
	 * ���ֽ�ת��Ϊʮ�������ַ���
	 *
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
	    char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
	                     'B', 'C', 'D', 'E', 'F' };
	    char[] tempArr = new char[2];
	    tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
	    tempArr[1] = Digit[mByte & 0X0F];

	    String s = new String(tempArr);
	    return s;
	 }
	
	/** 
	 * ��΢����Ϣ�е�CreateTimeת���ɱ�׼��ʽ��ʱ�䣨yyyy-MM-dd HH:mm:ss�� 
	 *  
	 * @param createTime ��Ϣ����ʱ�� 
	 * @return 
	 */  
	public static String formatTime(String createTime) {  
	    // ��΢�Ŵ����CreateTimeת����long���ͣ��ٳ���1000  
	    long msgCreateTime = Long.parseLong(createTime) * 1000L;  
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    return format.format(new Date(msgCreateTime));  
	}  
	

}
