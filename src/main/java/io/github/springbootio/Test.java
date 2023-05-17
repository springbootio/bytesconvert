package io.github.springbootio;

import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String[] args) {
        BytesConvert bc = new BytesConvert();

        byte[] bytes = bc.convert("aa".getBytes(StandardCharsets.UTF_8), 0);
        String s = new String(encodeHex(bytes));
        System.out.println("s :: " + s);


        byte[] bytes2 = bc.convert2("aa".getBytes(StandardCharsets.UTF_8));
        String s2 = new String(encodeHex(bytes2));
        System.out.println("s2 :: " + s2);
    }

    static char[] encodeHex(final byte[] data) {
        final char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        final int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = digits[(0xF0 & data[i]) >>> 4];
            out[j++] = digits[0x0F & data[i]];
        }
        return out;
    }

}
