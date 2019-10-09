package com.lzq.study.geektime.algorithms.hash;

public class Hash {

    private static final int SIZE = 256;

    /**
     * BF算法
     * @param source
     * @param target
     * @return
     */
    public int indexOf(String source, String target)
    {
        int targetLen = target.length();
        int sourceLen = source.length();
        int count = sourceLen - targetLen + 1;
        for (int i = 0; i < count ; i++){
            String sub = source.substring(i,targetLen+i);
            if (sub.equalsIgnoreCase(target)){
                return i;
            }
        }
        return -1;
    }

    /**
     * RK算法
     * @param source
     * @param target
     * @return
     */
    public int indexOf2(String source, String target){
        int targetHash = getHashValue(target);
        int targetLen = target.length();
        int sourceLen = source.length();
        int count = sourceLen - targetLen + 1;
        for (int i = 0; i < count ; i++){
            String sub = source.substring(i,targetLen+i);
            int sourceHash = getHashValue(sub);
            if (sourceHash == targetHash) return i;
        }
        return -1;
    }

    private int getHashValue(String str){
        char[] chars = str.toCharArray();
        int hashValue = 0;
        int len = chars.length;
        for (int i = 0; i < len; i++){
            hashValue += ( chars[i] - 'a') * 26^(len-1-i);
        }
        return hashValue;
    }

    /**
     * BM 算法坏字符算法
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int i = 0; // i 表示主串与模式串对齐的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是 j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            // 这里等同于将模式串往后滑动 j-bc[(int)a[i+j]] 位
            i = i + (j - bc[(int)a[i+j]]);
        }
        return -1;
    }

    /**
     *
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    public int bm2(char[] a, int n, char[] b, int m)
    {
        int[] bc = new int[SIZE];
        generateBC(b,m,bc);
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b,m,suffix,prefix);
        int i = 0;// j表示主串与模式串匹配的第一个字符
        while (i <= n - m){
            int j;
            for (j = m - 1; j >= 0 ; --j){
                if (a[i+j] != b[j]) break;// 坏字符对应模式串中的下标
            }
            if (j < 0){
                return i;
            }
            int x = j - bc[a[i+j]];
            int y = 0;
            if ( j < m-1){
                y = moveByGS(j,m,suffix,prefix);
            }
            i = i + Math.max(x,y);
        }
        return -1;
    }
    private void generateBC(char[] b,int m, int[] bc){
        for (int i = 0; i < SIZE; ++i){
            bc[i] = -1;
        }
        for (int i = 0; i < m; ++i){
            int ascii = (int)b[i];
            bc[ascii] = i; // 如果ascii相同只需要存储 bc[ascii] = 最后一个
        }
    }
    /**
     *
     * @param b = 模式串
     * @param m = 表示长度
     * @param suffix = 数组
     * @param prefix
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix)
    {
        for (int i = 0; i < m ; ++i){ //初始化
            suffix[i]  = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; ++i){ // b[0,i]
            int j = i;
            int k = 0;
            while (j >= 0 && b[j] == b[m-1-k]){
                --j;
                ++k;
                suffix[k] = j + 1;//记录模式串每个可以匹配前缀子串的长度 等于 最大下标值
            }
            if (j == -1) prefix[k] = true;
        }
    }
    /**
     *
     * @param j 表示坏字符对应模式串中的字符下标
     * @param m 模式串长度
     * @param suffix
     * @param prefix
     * @return
     */
    private int moveByGS(int j,int m,int[] suffix, boolean[] prefix)
    {
        int k = m - 1 -j; // 好后缀的长度
        if(suffix[k] != -1) return j - suffix[k] + 1;
        for (int r = j + 2; r <= m-1; ++r){
            if (prefix[m-r] == true) return r;
        }
        return m;
    }

    /**
     * KMP算法
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public static int kmp(char[] a, int n, char[] b, int m)
    {
        int[] next = getNexts(b,m);//获取模式子串的最长前缀匹配子串的下表
        int j = 0;
        for (int i = 0; i < n; ++i){
            while (j>0&&a[i]!=b[j]){//模式串和主串不等，模式串对应下表为j, 匹配的前缀为最后下标为j-1, 获取j-1的最大匹配前缀，比较 前缀+1 和 坏字符是否相同即可
                j = next[j-1]+1;
            }
            if (a[i] == b[j]){//如果主串和模式串一样
                ++j;
            }
            if (j == m) return i - m + 1; // i是下标，m是长度 所有需要 i + 1 - m
        }
        return -1;
    }

    /**
     * b表示模式串，m表示模式串的长度
     * @param b
     * @param m
     * @return
     */
    private static int[] getNexts(char[] b, int m)
    {
        int[] next = new int[m];
        next[0] = -1; // 只有一个字符的前缀时，没有前缀
        int k = -1;
        for (int i = 1; i < m; ++i){ //遍历模式串 1 到 m-1
            /**
             * 如：模式串 ababacd.
             * i从[1,6],k从[0,5]
             * i = 1, k = -1 b[0] != b[1] => next[1] = -1
             * i = 2, k = -1 b[0] = b[2] ++k => next[2] = 0
             * i = 3, k = 0  b[1] = b[3] ++k  => next[3] = 1
             * i = 4, k = 1 b[2] = b[4] ++k => next[4] = 2
             * i = 5, k = 2 b[3] != b[5]  k = next[2] = 0, b[1] != b[5]  k = next[0] = -1 => b[0] != b[5] , next[5] = -1
             * i = 6, k = -1 b[0] != b[6] => next[6] = -1
             */
            while (k != -1 && b[k+1] != b[i]){ // 如果 在前基础上下个字符不相等时，就会比较最长比较子串的 最长匹配字串，就是次长匹配子串，直到找到为止
                k = next[k];
            }

            if (b[k+1] == b[i]){ // 思想next[2] = 0,表示 aba =》 a?ba? 只要 ？=？就会有 next[3] = 1
                ++k;
            }

            next[i] = k;
        }
        return next;
    }

    public static void main(String[] args) {
        Hash hash = new Hash();
        System.out.println(hash.indexOf2("zhengqiuliu","eng"));
    }
}
