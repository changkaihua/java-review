package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 想要减少复杂度, 要从 hash 下手
 *
 * @author ckh
 * @since 2020/12/14
 */
public class GroupAnagrams {


    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> ans = new ArrayList<>();
        Map<Integer, List<String>> shortMap = new HashMap<>();
        Map<String, List<String>> longMap = new HashMap<>();
        int[] code = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

        for (String str : strs) {

            // 长度小于 5 的字符串, 通过26个质数表示唯一标识, 乘积为 hash
            // 过长会溢出, 所以, 超过5的, 用排过序的自身作为hash
            if (str.length() < 5) {
                int key = 1;
                for (char c : str.toCharArray()) {
                    key *= code[c - 'a'];
                }
                List<String> shortList = shortMap.getOrDefault(key, new ArrayList<>());
                shortList.add(str);
                shortMap.put(key, shortList);
            } else {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = new String(array);
                List<String> longList = longMap.getOrDefault(key, new ArrayList<>());
                longList.add(str);
                longMap.put(key, longList);
            }
        }

        ans.addAll(shortMap.values());
        ans.addAll(longMap.values());
        return ans;

    }

    public List<List<String>> groupAnagramsV2(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            int key = Arrays.hashCode(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagramsV3(String[] strs) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        int[] chars = new int[26];
        int key;
        for (String str : strs) {
            for (int i = 0; i < str.length(); i++) {
                chars[str.charAt(i) - 'a']++;
            }
            key = Arrays.hashCode(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
            Arrays.fill(chars, 0);
        }
        return new ArrayList<>(map.values());
    }


    public List<List<String>> groupAnagramsV4(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors.groupingBy(str -> {
                    // 返回 str 排序后的结果。
                    // 按排序后的结果来grouping by，算子类似于 sql 里的 group by。
                    char[] array = str.toCharArray();
                    Arrays.sort(array);
                    return new String(array);
                })).values());


    }


    @Test
    public void test() {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        // System.out.println(Math.log(Integer.MAX_VALUE) / Math.log(101));
        // System.out.println(Math.pow(101, 5) - Integer.MAX_VALUE);
        System.out.println(groupAnagramsV4(strs));

        ArrayList<List<String>> lists = new ArrayList<>(
                Arrays.stream(strs).collect(
                        Collectors.groupingBy(
                                str -> str.chars().sorted().collect(
                                        StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append
                                ).toString()
                        )
                ).values()
        );

    }


    @Test
    public void testHashcode() {
        String[] strs = {"eat", "tea", "ate"};
        int[] chars = new int[26];
        for (String str : strs) {

            char[] array = str.toCharArray();
            System.out.println("before: " + array.hashCode());
            Arrays.sort(array);
            System.out.println(array.hashCode());

            System.out.println("Arrays.hashCode(array) = " + Arrays.hashCode(array));
//
//            for (int i = 0; i < str.length(); i++) {
//                chars[str.charAt(i) - 'a']++;
//            }
//            Arrays.hashCode(chars);
        }

    }
}
