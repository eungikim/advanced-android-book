package kr.eungi.permissiondemob;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Util {

    /**
     * 예제 [13-15] 해시값을 구하는 코드
     * @param algoType "MD5" or "SHA1" or "SHA256"
     * @return hash string of key
     */
    @SuppressLint("PackageManagerGetSignatures")
    @Nullable
    public static String getHash(Context context, String packageName, String algoType) {
        if (packageName == null) return null;
        try {
            PackageManager pm = context.getApplicationContext().getPackageManager();
            PackageInfo info;
            Signature sig;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                info = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
                if (info.signatures.length != 1) return null;
                sig = info.signatures[0];
            } else {
                info = pm.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES);
                Signature[] signatures = info.signingInfo.getApkContentsSigners();
                if (signatures.length != 1) return null;
                sig = signatures[0];
            }
            byte[] cert = sig.toByteArray();

            MessageDigest md = MessageDigest.getInstance(algoType);
            md.update(cert);

            byte[] mdDigest = md.digest();
            String keyHash = Base64.encodeToString(mdDigest, Base64.DEFAULT);

            Log.d("mdDigest", byteArrayToHexString(mdDigest, ' ', true));
            Log.d("keyHash", keyHash);
            return keyHash;
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) { }
        return null;
    }

    /**
     * AES256
     * 예제 [13-16] ...
     * @param buf
     * @param key 비밀키 생성의 시드가 되는 임의의 바이트 배열
     * @param iv 초기화 벡터
     * @return
     */
    @Nullable
    public static byte[] cipherEncrypt(byte[] buf, String key, String iv) {
        try {
            // 비밀키 생성
            SecretKeySpec sksSpec = new SecretKeySpec(key.getBytes(), "AES");
            // IV 생성
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            // Cipher 클래스 초기화
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sksSpec, ivSpec);
            // 암호화
            return cipher.doFinal(buf);
        } catch (NoSuchPaddingException|NoSuchAlgorithmException|
                InvalidAlgorithmParameterException|InvalidKeyException|
                BadPaddingException|IllegalBlockSizeException e) {
        } finally {

        }
        return null;
    }

    public static String byteArrayToHexString(byte[] a, char regex, boolean isUpperCase) {
        StringBuilder sb = new StringBuilder();
        for (final byte b : a)
            sb.append(byteToHexString(b, isUpperCase)).append(regex);
        return sb.toString().trim();
    }


    public static String byteToHexString(byte b, boolean isUpperCase) {
        if (isUpperCase) {
            return String.format("%02X", b & 0xff);
        } else {
            return String.format("%02x", b & 0xff);
        }
    }

}
