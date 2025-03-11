package com.learn.newsfeed.storage;

import com.learn.newsfeed.storage.model.StorageResponse;

public interface CouldStorageManager {
    StorageResponse putObject(String bucketName, String key, byte[] file);
    StorageResponse getObject(String bucketName, String key);
}
