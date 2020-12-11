package com.joe.tophot;

import org.junit.Test;

/**
 * @author ckh
 * @since 2020/12/9
 */
public class WildcardMatching {
    public boolean isMatch(String str, String pattern) {
        int sLen = str.length();
        int pLen = pattern.length();

        int idxS = 0, idxP = 0, start = -1, match = 0;
        while (idxS < sLen) {
            if (idxP < pLen && (str.charAt(idxS) == pattern.charAt(idxP) || pattern.charAt(idxP) == '?')) {
                idxP++;
                idxS++;
            } else if (idxP < pLen && pattern.charAt(idxP) == '*') {
                start = idxP;
                match = idxS;
                idxP++;
            } else if (start != -1) {
                // idxP 相当于没动, 让 idxS 增加了;
                // 上一位是 * 这一位没有通过前面的相等判断, 直接跳过
                idxP = start + 1;
                match++;
                idxS = match;
            } else {
                return false;
            }

        }
        while (idxP < pLen) {
            if (pattern.charAt(idxP) != '*') return false;
            idxP++;
        }
        return true;
    }

    @Test
    public void test(){
        boolean match = isMatch("asdfxcjkhsafd", "asd*jk*s???");
        System.out.println(match);
    }
}
