package com.learn.newsfeed.storage.model;

public class StorageResponse {
    private final int responseCode;
    private final String responseMessage;
    private final byte[] payload;
    private final String objectUrl;

    private StorageResponse(Builder builder) {
        this.responseCode = builder.responseCode;
        this.responseMessage = builder.responseMessage;
        this.payload = builder.payload;
        this.objectUrl = builder.objectUrl;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public byte[] getPayload() {
        return payload;
    }

    public String getObjectUrl() {
        return objectUrl;
    }

    public static class Builder {
        private int responseCode;
        private String responseMessage;
        private byte[] payload;
        private String objectUrl;

        public Builder responseCode(int responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder responseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        public Builder payload(byte[] payload) {
            this.payload = payload;
            return this;
        }

        public Builder objectUrl(String objectUrl) {
            this.objectUrl = objectUrl;
            return this;
        }

        public StorageResponse build() {
            return new StorageResponse(this);
        }
    }
}
