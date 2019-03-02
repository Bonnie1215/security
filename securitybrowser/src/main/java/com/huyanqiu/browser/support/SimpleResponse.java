package com.huyanqiu.browser.support;

/**
 * 返回前端json数据格式
 * @ClassName SimpleResponse
 * @author: huyanqiu
 * @since: 2019/2/16 19:19
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
