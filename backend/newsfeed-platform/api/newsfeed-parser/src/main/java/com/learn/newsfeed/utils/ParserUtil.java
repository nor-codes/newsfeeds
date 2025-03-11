package com.learn.newsfeed.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.newsfeed.logging.EventLog;
import com.learn.newsfeed.model.FileEvent;
import com.learn.newsfeed.storage.CouldStorageManager;
import com.learn.newsfeed.storage.model.StorageResponse;
import net.sf.saxon.s9api.*;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ParserUtil {

    private final String MODULE = ParserUtil.class.getCanonicalName();
    private final CouldStorageManager storageManager;
    public ParserUtil(CouldStorageManager storageManager) {
        this.storageManager = storageManager;
    }

    private void parse(String event){
        FileEvent mappedEvent = getObjectFromJson(event);

        if (mappedEvent==null){
            EventLog.module(MODULE)
                    .message("Event was parsed to null")
                    .error()
                    .log();
            return;
        }

        StorageResponse storageResponse = storageManager.getObject(mappedEvent.getObjectBucket(), mappedEvent.getObjectName());


    }

    private FileEvent getObjectFromJson(String event){
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(event, FileEvent.class);
        } catch (IOException e) {
            EventLog.module(MODULE)
                    .message(e.getMessage())
                    .error()
                    .log();
            return null;
        }
    }
}
