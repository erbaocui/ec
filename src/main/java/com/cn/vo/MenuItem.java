package com.cn.vo;

import java.util.List;

/**
 * Created by home on 2017/11/14.
 */
public class MenuItem {
   private String  title;
    private String  icon;

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    private Boolean isCurrent;
    private String  href;
    private List<MenuItem> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuItem() {

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }
}
