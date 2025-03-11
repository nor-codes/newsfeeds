package com.learn.newsfeed.model;

public class FileEvent {
    private String id;
    private String objectBucket;
    private String objectName;
    private String timestamp;
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public FileEvent(String id, String objectBucket, String objectName, String timestamp, String source) {
        this.id = id;
        this.objectBucket = objectBucket;
        this.objectName = objectName;
        this.timestamp = timestamp;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectBucket() {
        return objectBucket;
    }

    public void setObjectBucket(String objectBucket) {
        this.objectBucket = objectBucket;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
