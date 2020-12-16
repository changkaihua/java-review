package com.joe.leetbook.array;

/**
 * @author ckh
 * @since 2020/12/15
 */
public class ArrayPairSum {
    public int arrayPairSum(int[] nums){
        int [] cnt = new int[20001];
        for(int num : nums)
            cnt[num + 10000]++;

        int idx = 0;
        for(int i = -10000;i <= 10000;i++)
        {
            while(cnt[i + 10000] > 0)
            {
                nums[idx++] = i;
                cnt[i + 10000]--;
            }
        }
        int sum = 0;
        for(int i = 0;i < nums.length;i+=2)
            sum += nums[i];

        return sum;
    }
}
