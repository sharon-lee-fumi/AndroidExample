package com.pointclickcare.brian.bundletest.ui.recyclerview;

class ListItem<T> {
    public T data;
    public boolean selected;

    public ListItem(T data) {
        this.data = data;
        this.selected = false;
    }
}
