package com.lzq.study.geektime.test.search;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
public class WebGraph {
    private int v;// 网页的个数
    private LinkedList<WebPage> adj[]; //每个网页之间的邻接关系
    private List<WebPage> linksBin;//待爬取网页链接文件
    private BitMap bitMap;//布隆过滤器

    public WebGraph(int v){
        this.v = v;
        adj = new LinkedList[v]; // 表示有v个网页
        for (int i = 0; i < v; ++i){
            adj[i] = new LinkedList<>(); // 表示每个网页没有关系
        }
        linksBin = new ArrayList<>();
        bitMap = new BitMap(v);
    }

    public void addWebGraph(WebPage webPage){
        Queue<WebPage> queue = new LinkedList<>();
        if (bitMap.get(webPage.getId())){
            return;
        }
        bitMap.set(webPage.getId());
        queue.add(webPage);
        linksBin.add(webPage);
        while (queue.size()!=0){
            WebPage temp = queue.poll();
            if (CollectionUtils.isNotEmpty(temp.getLinks()))
            {
                adj[webPage.getId()].addAll(temp.getLinks());
                queue.addAll(temp.getLinks());
                linksBin.addAll(temp.getLinks());
            }
        }
    }
}
