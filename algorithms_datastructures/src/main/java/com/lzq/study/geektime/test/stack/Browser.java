package com.lzq.study.geektime.test.stack;

/**
 * 实现浏览器前进后退
 */
public class Browser {
    private LinkStack back;
    private LinkStack forward;

    public void init(){
        this.back = new LinkStack();
        this.forward = new LinkStack();
        forward.push("123");
        forward.push("234");
        forward.push("567");
    }

    /**
     * 后退
     */
    public String backOff()
    {
      if (forward.isEmpty()) return null;
      String url = forward.pop();
      back.push(url);
      return url;
    }

    /**
     * 前进
     * @return
     */
    public String forward()
    {
        if (back.isEmpty()) return null;
        String url = back.pop();
        forward.push(url);
        return url;
    }

    public static void main(String[] args) {
        Browser browser = new Browser();
        browser.init();
        System.out.println(browser.backOff()+","+browser.backOff());
        System.out.println(browser.forward());
    }
}
