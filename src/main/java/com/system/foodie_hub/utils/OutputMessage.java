package com.system.foodie_hub.utils;

import com.system.foodie_hub.entity.user_management.Message;

public class OutputMessage extends Message {

    private String time;

    public OutputMessage(final String from, final String text, final String time) {
        setSender(from);
        setContent(text);
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) { this.time = time; }
}