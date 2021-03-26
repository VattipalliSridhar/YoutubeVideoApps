package com.apps.videochannel.base;

public class YtData {
    private static YtData INSTANCE;
    String youtube;

    private YtData() {
    }

    public static YtData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new YtData();
        }
        return INSTANCE;
    }

    public String getData() {
        return this.youtube;
    }

    public void setData(String str) {
        this.youtube = str;
    }
}
