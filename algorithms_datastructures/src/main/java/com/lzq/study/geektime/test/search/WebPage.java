package com.lzq.study.geektime.test.search;

import lombok.Data;

import java.util.List;

@Data
public class WebPage {
    private int id; // 网页的编号
    private String url; //链接
    private String text; //文本内容
    private List<WebPage> links; //网页关联的其它链接

    public WebPage(int id,String url,String text){
        this.id = id;
        this.url = url;
        this.text = text;
    }
}
