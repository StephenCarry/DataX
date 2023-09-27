package com.alibaba.datax.common.util;

import java.math.BigInteger;

public class G62Util {
    private static final char[] allChar = new char[] {
            '0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f','g','h','i','j',
            'k','l','m','n','o','p','q','r','s','t',
            'u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T',
            'U','V','W','X','Y','Z'
    };

    private static final BigInteger base = new BigInteger("62");

    public static BigInteger string2BigInteger(String s) {
        if (null == s) throw new IllegalArgumentException("参数不能为空");
        BigInteger result = BigInteger.ZERO;
        BigInteger index;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int pow = chars.length - 1 - i;
            index = getCharIndex(chars[i]);
            result = result.add(index.multiply(base.pow(pow)));
        }
        return result;
    }

    public static String bigInteger2String(BigInteger n) {
        if (null == n) throw new IllegalArgumentException("参数不能为空");
        StringBuilder s = new StringBuilder();
        if ((n.compareTo(BigInteger.ZERO)) == 0) {
            s = s.insert(0, "0");
        }
        BigInteger mod;
        while (n.compareTo(BigInteger.ZERO) != 0) {
            mod = n.mod(base);
            char c = allChar[mod.intValue()];
            s.insert(0, c);
            n = n.divide(base);
        }
        return s.toString();
    }

    private static BigInteger getCharIndex(char c) {
        int index = 0;
        for (int i = 0; i < allChar.length; i++) {
            char s = allChar[i];
            if (s == c) {
                index = i;
                break;
            }
        }
        return new BigInteger(String.valueOf(index));
    }
}
