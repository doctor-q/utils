package cc.doctor.utils.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AesUtils {
    /**
     * 加密算法
     */
    private static final String AES_ALGORITHM = "AES";

    /**
     * 加密算法/加密模式/填充类型
     * 本例采用AES加密，ECB加密模式，PKCS5Padding填充
     */
    private static final String CIPHER_MODE = "AES/ECB/PKCS5Padding";

    /**
     * 设置加密字符集
     * 本例采用 UTF-8 字符集
     */
    private static final String CHARACTER = "UTF-8";

    /**
     * 设置加密密码处理长度。
     * 不足此长度补0；
     */
    private static final int PWD_SIZE = 16;

    private AesUtils() {
    }

    /**
     * 密码处理方法
     * 如果加解密出问题，
     * 请先查看本方法，排除密码长度不足补"0",导致密码不一致
     */
    private static byte[] paddingKey(String key) throws UnsupportedEncodingException {
        if (key == null) {
            key = "";
        }
        StringBuilder sb = new StringBuilder(PWD_SIZE);
        sb.append(key);
        while (sb.length() < PWD_SIZE) {
            sb.append("0");
        }
        if (sb.length() > PWD_SIZE) {
            sb.setLength(PWD_SIZE);
        }

        return sb.toString().getBytes(CHARACTER);
    }

    //======================>原始加密<======================

    /**
     * 原始加密
     *
     * @param clearTextBytes 明文字节数组，待加密的字节数组
     * @param pwdBytes       加密密码字节数组
     * @return 返回加密后的密文字节数组，加密错误返回null
     */
    private static byte[] encrypt(byte[] clearTextBytes, byte[] pwdBytes) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(pwdBytes, AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(clearTextBytes);
    }

    /**
     * 原始解密
     *
     * @param cipherTextBytes 密文字节数组，待解密的字节数组
     * @param pwdBytes        解密密码字节数组
     * @return 返回解密后的明文字节数组，解密错误返回null
     */
    private static byte[] decrypt(byte[] cipherTextBytes, byte[] pwdBytes) throws Exception {

        SecretKeySpec keySpec = new SecretKeySpec(pwdBytes, AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(cipherTextBytes);
    }

    //======================>HEX<======================

    /**
     * HEX加密
     *
     * @param clearText 明文，待加密的内容
     * @param password  密码，加密的密码
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptHex(String clearText, String password) throws Exception {
        byte[] cipherTextBytes = encrypt(clearText.getBytes(CHARACTER), paddingKey(password));
        return byte2hex(cipherTextBytes);
    }

    /**
     * HEX解密
     *
     * @param cipherText 密文，带解密的内容
     * @param password   密码，解密的密码
     * @return 返回明文，解密后得到的内容。解密错误返回null
     */
    public static String decryptHex(String cipherText, String password) throws Exception {
        byte[] cipherTextBytes = hex2byte(cipherText);
        byte[] clearTextBytes = decrypt(cipherTextBytes, paddingKey(password));
        return new String(clearTextBytes, CHARACTER);
    }

    /*字节数组转成16进制字符串  */
    private static String byte2hex(byte[] bytes) { // 一个字节的数，
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        String tmp;
        for (int n = 0; n < bytes.length; n++) {
            // 整数转成十六进制表示
            tmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase(); // 转成大写
    }

    /*将hex字符串转换成字节数组 */
    private static byte[] hex2byte(String str) {
        if (str == null || str.length() < 2) {
            return new byte[0];
        }
        str = str.toLowerCase();
        int l = str.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = str.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }
}
