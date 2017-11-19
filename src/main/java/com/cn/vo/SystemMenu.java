package com.cn.vo;

import java.util.List;

/**
 * Created by home on 2017/11/14.
 */
public class SystemMenu {

    private String title;
    private String icon;
    private Boolean isCurrent;
    private List<MenuItem> menu;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public SystemMenu() {

    }

}
