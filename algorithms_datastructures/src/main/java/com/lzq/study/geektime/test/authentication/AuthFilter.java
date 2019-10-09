package com.lzq.study.geektime.test.authentication;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 权限过滤
 */
public class AuthFilter {
    private Map<String, List> userMap = Maps.newHashMap();//用户和对应的URL
    private Map<String,AuthTrie> stringAuthTrieMap = Maps.newHashMap(); // 使用Trie树进行存储
    private int TrieSize = 10;
    private static Map<String,Integer> initMap = Maps.newHashMap();
    static {
        initMap.put("user",0);initMap.put("info",1);initMap.put("base",2);initMap.put("detail",3);
        initMap.put("register",4);initMap.put("login",5);
        initMap.put("take1",6);initMap.put("take2",7);initMap.put("take3",8);initMap.put("take4",9);
    }

    private void insert(String user, List<String> urlList)
    {
        AuthTrie root = new AuthTrie("/");
        for (String url : urlList){
            AuthTrie p = root;
            url = url.substring(1);
            String[] textArr = url.split("/");
            for(String text : textArr){
                int index = initMap.get(text);
                if (p.children[index] == null){
                    AuthTrie authTrie = new AuthTrie(text);
                    p.children[index] = authTrie;
                }
                p = p.children[index];
            }
            p.isEndingChar = true;
            p.length = textArr.length;
        }
        stringAuthTrieMap.put(user,root);
    }

    private void buildFailurePointer(String user){
        AuthTrie root = stringAuthTrieMap.get(user);
        Queue<AuthTrie> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()){
            AuthTrie p = queue.remove();
            for (int i=0;i<TrieSize;++i){
                AuthTrie pc = p.children[i];
                if (pc == null) continue;
                if (p == root){
                    pc.fail = root;
                }else {
                    AuthTrie q = p.fail;
                    while (q != null){
                        int index = initMap.get(pc.data);
                        AuthTrie qc = q.children[index];
                        if (qc != null){
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null){
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

    public void initTrie(){
        insert("app_id_a",Lists.newArrayList("/user/info/base","/user/info/detail"));
        buildFailurePointer("app_id_a");
        insert("app_id_b",Lists.newArrayList("/user/register","/user/login"));
        buildFailurePointer("app_id_b");
        insert("app_id_c",Lists.newArrayList("/user/info/base","/user/login","/user/take1","/user/take2","/user/take3"));
        buildFailurePointer("app_id_c");
    }

    public boolean match(String user,String url)
    {
        url = url.substring(1);
        if (!stringAuthTrieMap.containsKey(user)) return false;
        String[] urlArr = url.split("/");
        int len = urlArr.length;
        AuthTrie root = stringAuthTrieMap.get(user);
        AuthTrie p = root;
        for (int i = 0; i < len; i++){
            int index = initMap.get(urlArr[i]);
            while (p.children[index] == null && p != root) p = p.fail;
            p = p.children[index];
            if (p == null) p = root;
            AuthTrie tmp = p;
            while (tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isEndingChar == true) {
                    return true;
                }
                tmp = tmp.fail;
            }
        }
        return false;
    }



    public void init(){
        List<String> urlListA = Lists.newArrayList("/user/info/base","/user/info/detail");
        userMap.put("app_id_a",urlListA);

        List<String> urlListB = Lists.newArrayList("/user/register","/user/login");
        userMap.put("app_id_b",urlListB);

        List<String> urlListC = Lists.newArrayList("/user/info/base","/user/login","/user/take1","/user/take2","/user/take3");
        userMap.put("app_id_c",urlListC);
    }

    /**
     * 对Map的Value按照url从小到大开始排序
     */
    public void sortUrl(){
        for (Map.Entry<String,List> user : userMap.entrySet())
        {
            quickSort(user.getValue());
        }
    }

    private void quickSort(List<String> urlList)
    {
        String[] sortStr = urlList.toArray(new String[urlList.size()]);
        sort(sortStr,0,urlList.size()-1);
        urlList.clear();
        urlList.addAll(Arrays.asList(sortStr));
    }

    private static void sort(String[] arr, int p, int r) {
        if (p >= r) return;
        int pivot = separate(arr, p, r);
        sort(arr, p, pivot - 1);
        sort(arr, pivot + 1, r);
    }

    private static int separate(String[] arr, int p, int r) {
        String value = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if ( arr[j].compareTo(value) < 0 ) {
                String tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }
        String tmp = arr[i];
        arr[i] = arr[r];
        arr[r] = tmp;
        return i;
    }


    /**
     * 精确匹配查找,顺序查找时间复杂度为O(n)
     */
    public boolean verifyAuth(String user,String url)
    {
        if(!userMap.keySet().contains(user)) return false;
        List<String> urlList = userMap.get(user);
        for (String everyUrl : urlList)
        {
            if (StringUtils.equalsIgnoreCase(everyUrl,url)){
                return true;
            }
        }
        return false;
    }

    /**
     * 精确匹配查找,二分查找时间复杂度为O(logn)
     */
    public boolean verifyAuth2(String user,String url)
    {
        if(!userMap.keySet().contains(user)) return false;
        List<String> urlList = userMap.get(user);
        String[] urlArr = urlList.toArray(new String[urlList.size()]);
        int low = 0;
        int high = urlArr.length - 1;
        while (low <= high){
            int mid = low + ((high-low)>>1);
            if (urlArr[mid].compareToIgnoreCase(url) == 0) return true;
            else if (urlArr[mid].compareToIgnoreCase(url) < 0){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return false;
    }

    private class AuthTrie{
        String data;
        AuthTrie[] children = new AuthTrie[TrieSize];//每个子字符集包括10个
        boolean isEndingChar = false;//结尾字符为true
        public int length = -1; // 当isEndingChar=true时，记录模式串长度
        public AuthTrie fail;//失败指针
        public AuthTrie(String data){
            this.data = data;
        }
    }

}
