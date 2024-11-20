package com.otsi.tconnect.ms.fup.controller;

public class Notification {
    private String content;
    private String recipient;

    public Notification() {}

    public Notification(String content, String recipient) {
        this.content = content;
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
