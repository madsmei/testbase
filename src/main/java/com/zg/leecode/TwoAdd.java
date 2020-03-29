package com.zg.leecode;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 两数只和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * @Date 2020/3/27
 * @Version V1.0
 * @Author Mads
 **/
public class TwoAdd {

    public static void main(String[] args) {
        int[] nums = {2, 11,7, 15};
        int target = 9;

        int[] s = twoSum(nums, target);
        System.out.println("");
    }
    public static int[] twoSum(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i <nums.length ; i++) {
            if(map.containsKey(target - nums[i])){
                return new int[] {map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
}
