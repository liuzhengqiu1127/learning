package com.lzq.study.geektime.test.search;

import com.google.common.collect.Lists;

import java.util.List;

public class SearchEngine {

    public static void main(String[] args) {

        WebGraph webGraph = new WebGraph(10); //构建网页的图

        WebPage webPage = new WebPage(0,"https://www2.bing.com/search?q=0","<style>hello world</style><script>my pleasure</script>if i have a dream,I will go to the world");
        WebPage webPage1 = new WebPage(1,"https://www2.bing.com/search?q=1","<style>hello world</style><script>my pleasure</script>if i have a dream,I will go to the world");
        WebPage webPage2 = new WebPage(2,"https://www2.bing.com/search?q=2","<style>hello world</style><script>my pleasure</script>if i have a dream,I will go to the world");
        WebPage webPage3 = new WebPage(3,"https://www2.bing.com/search?q=3","<style>hello world</style><script>my pleasure</script>if i have a dream,I will go to the world");
        WebPage webPage4 = new WebPage(4,"https://www2.bing.com/search?q=4","<style>hello world</style><script>my pleasure</script>if i have a dream,I will go to the world");
        WebPage webPage5 = new WebPage(5,"https://www2.bing.com/search?q=5","<style>hello world</style><script>my pleasure</script>if i have a dream,I will go to the world");
        WebPage webPage6 = new WebPage(6,"https://www2.bing.com/search?q=6","<style>hello world</style><script>my pleasure</script>if i have a dream,I will go to the world");
        WebPage webPage7 = new WebPage(7,"https://www2.bing.com/search?q=7","<style>hello world</style><script>my pleasure</script>if i have a dream,I will go to the world");

        webPage.getLinks().add(webPage1);
        webPage.getLinks().add(webPage2);
        webPage2.getLinks().add(webPage3);

        webPage4.getLinks().add(webPage5);
        webPage5.getLinks().add(webPage6);
        webPage5.getLinks().add(webPage7);

        webGraph.addWebGraph(webPage);
        webGraph.addWebGraph(webPage4);

        Trie trie = new Trie();
        trie.insert("<style>".toCharArray());
        trie.insert("<script>".toCharArray());
        trie.insert("<option>".toCharArray());


        String text = webPage.getText();
        List<Integer> indexList = trie.match(text.toCharArray());
        List<String> endText = Lists.newArrayList("</style>","</script>","</option>");
        for (Integer index : indexList)
        {
            for (int j = index; j < text.length(); ++j){
                //匹配结束符
            }
        }

    }

}
