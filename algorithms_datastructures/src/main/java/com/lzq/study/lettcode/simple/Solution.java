package com.lzq.study.lettcode.simple;

import com.lzq.study.lettcode.common.ListNode;

public class Solution {

    //这个ReturnNode是参考我描述的递归套路的第二步：思考返回值是什么
    //一棵树是BST等价于它的左、右俩子树都是BST且俩子树高度差不超过1
    //因此我认为返回值应该包含当前树是否是BST和当前树的高度这两个信息
    private class ReturnNode {
        boolean isB;
        int depth;

        public ReturnNode(int depth, boolean isB) {
            this.isB = isB;
            this.depth = depth;
        }
    }

    //主函数
    public boolean isBalanced(TreeNode root) {
        return isBST(root).isB;
    }

    //参考递归套路的第三部：描述单次执行过程是什么样的
    //这里的单次执行过程具体如下：
    //是否终止?->没终止的话，判断是否满足不平衡的三个条件->返回值
    public ReturnNode isBST(TreeNode root) {
        if (root == null) {
            return new ReturnNode(0, true);
        }
        //不平衡的情况有3种：左树不平衡、右树不平衡、左树和右树差的绝对值大于1
        ReturnNode left = isBST(root.left);
        ReturnNode right = isBST(root.right);
        if (left.isB == false || right.isB == false) {
            return new ReturnNode(0, false);
        }
        if (Math.abs(left.depth - right.depth) > 1) {
            return new ReturnNode(0, false);
        }
        //不满足上面3种情况，说明平衡了，树的深度为左右俩子树最大深度+1
        return new ReturnNode(Math.max(left.depth, right.depth) + 1, true);
    }


    /**
     * s 源字符串
     * p 模式字符串
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean[][] value = new boolean[p.length() + 1][s.length() + 1];
        value[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
            value[0][i] = false;
        }
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                value[i][0] = value[i - 1][0];
                for (int j = 1; j <= s.length(); j++) {
                    value[i][j] = (value[i][j - 1] || value[i - 1][j]);
                }
            } else if (p.charAt(i - 1) == '?') {
                value[i][0] = false;
                for (int j = 1; j <= s.length(); j++) {
                    value[i][j] = value[i - 1][j - 1];
                }
            } else {
                value[i][0] = false;
                for (int j = 1; j <= s.length(); j++) {
                    value[i][j] = s.charAt(j - 1) == p.charAt(i - 1) && value[i - 1][j - 1];
                }
            }
        }
        return value[p.length()][s.length()];
    }


    /**
     * 整数求和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rl1 = reverse(l1);
        ListNode rl2 = reverse(l2);
        StringBuilder value1 = new StringBuilder();
        while (rl1 != null) {
            value1.append(rl1.val);
            rl1 = rl1.next;
        }
        StringBuilder value2 = new StringBuilder();
        while (rl2 != null) {
            value2.append(rl2.val);
            rl2 = rl2.next;
        }
        String result = String.valueOf(Long.valueOf(value1.toString()) + Long.valueOf(value2.toString()));
        int len = result.length();
        ListNode head = new ListNode(Integer.valueOf(result.charAt(len - 1) + ""));
        ListNode tmp = head;
        for (int i = len - 2; i >= 0; i--) {
            tmp.next = new ListNode(Integer.valueOf(result.charAt(i) + ""));
            tmp = tmp.next;
        }
        return head;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode node = null;
        while (head != null) {
            node = head;
            head = head.next;
            node.next = prev;
            prev = node;
        }
        return node;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        int sum = 0;
        ListNode cur = listNode;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10); // 相加然后%10
            sum /= 10;
            cur = cur.next;
        }
        if (sum >= 1) {
            cur.next = new ListNode(sum);
        }
        return listNode.next;
    }

    /**
     * 求最大长度无重复子串
     * abcakad
     * 获取 第二次a出现时的前一个位置，a=0,下次从b开始计算长度，获取最长的。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int i = 0, max = 0;
        int size = s.length();
        for (int j = 0; j < size; j++) {
            for (int k = i; k < j; k++) {
                if (s.charAt(k) == s.charAt(j)) {
                    i = k + 1;
                    break;
                }
            }
            if (j - i + 1 > max) max = j - i + 1;
        }
        return max;
    }

    /**
     * 求中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /**
     * i: nums1的起始位置
     * j: nums2的起始位置
     *
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @param k
     * @return
     */
    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if (j >= nums2.length) return nums1[i + k - 1];//nums2的空数组
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        else return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
    }

    /**
     * 以每个字符为中心点 向左右扩散就可以
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
//      保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
//             把回文看成中间的部分全是同一字符，左右部分相对称
//             找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }
    public static int findLongest(char[] str, int low, int[] range) {
//       查找中间部分
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
//      定位中间部分的最后一个字符 abccccba
        int ans = high;
//         从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
//         记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        StringBuilder strb = new StringBuilder();
        for(int i=1;i<=numRows;i++){
            if(i == 1 || i == numRows){
                for(int j=i-1;j<s.length();j+=2*(numRows-1)){
                    strb.append(s.charAt(j));
                }
            }
            else{
                int tag = 0;
                int j=i-1;
                while(j<s.length()){
                    strb.append(s.charAt(j));
                    if(tag % 2 == 0){
                        tag++;
                        j+=2*(numRows-i);
                    }
                    else{
                        tag++;
                        j+=2*(i-1);
                    }
                }
            }
        }
        return strb.toString();
    }

    /**
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        StringBuilder result = new StringBuilder();
        String value = String.valueOf(x);
        int len = value.toCharArray().length;
        int start = 0;
        if (x < 0){
            result.append("-");
            start = 1;
        }
        for (int i = len - 1; i >= start; --i){
            result.append(value.charAt(i));
        }

        try {
            return Integer.valueOf(result.toString());
        }catch (Exception exception){
            return 0;
        }
    }

    /**
     * 是否为回文
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x<0) return false;
        String str = String.valueOf(x);
        StringBuilder reverse = new StringBuilder();
        for (int i=str.length()-1;i>=0;--i){
            reverse.append(str.charAt(i));
        }
        return str.equals(reverse.toString());
    }
}
