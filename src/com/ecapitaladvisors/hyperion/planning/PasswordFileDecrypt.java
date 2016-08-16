package com.ecapitaladvisors.hyperion.planning;

import com.hyperion.planning.HspUtils;
import java.io.*;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordFileDecrypt {

    private static final byte OBFUSCATION_BASE_BYTE = 65;
    private static final Key ENCRYPTION_KEY = getKey("7a4^8@(vAJXr^.VR");
    
	public static void main(String[] args)
	{   
		System.out.println("");
		System.out.println("PASSWORD : "+args[0].toString());
		System.out.println("DECRYPTED: "+decryptBlowFish(args[0].toString()));
	}

    private static String decryptBlowFish(String value)
    {
        String decValue = "";
        try
        {
            Cipher cipher = Cipher.getInstance("Blowfish/ECB/NoPadding");
            cipher.init(2, ENCRYPTION_KEY);
            byte bytes[] = HspUtils.byteArrayFromFourBit(value, (byte)65);
            byte result[] = cipher.doFinal(bytes);
            decValue = (new String(result, "UTF-16LE")).trim();
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
        return decValue.toString();
    }

    private static Key getKey(String key)
    {
        try
        {
            return new SecretKeySpec(key.getBytes("ASCII"), "Blowfish");
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
   
}