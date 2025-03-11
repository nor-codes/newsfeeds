package com.learn.newsfeed.storage;
import com.learn.newsfeed.storage.model.StorageResponse;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class AWSStorageManager implements CouldStorageManager{

    private final S3Client s3Client;
    private final S3Utilities s3Utilities;
    public AWSStorageManager(S3Client s3Client) {
        this.s3Client = s3Client;
        this.s3Utilities = s3Client.utilities();
    }

    @Override
    public StorageResponse putObject(String bucketName, String key, byte[] file) {
        try{
            PutObjectRequest putObjectRequest = PutObjectRequest
                    .builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file));
            String objectUrl = s3Utilities.getUrl(builder -> builder.bucket(bucketName).key(key)).toString();
            return new StorageResponse.Builder()
                    .responseCode(200)
                    .responseMessage("File uploaded")
                    .objectUrl(objectUrl)
                    .build();

        }catch (Exception ex){
            if (ex instanceof S3Exception s3Exception){

                return new StorageResponse.Builder()
                        .responseCode(s3Exception.statusCode())
                        .responseMessage(s3Exception.getMessage())
                        .build();
            }

            return new StorageResponse.Builder()
                    .responseCode(500)
                    .responseMessage("Failed")
                    .build();
        }
    }

    @Override
    public StorageResponse getObject(String bucketName, String key) {
        try{
            var response = s3Client.getObject(GetObjectRequest.builder().bucket(bucketName).key(key).build());
            byte[] payload  = response.readAllBytes();
            return new StorageResponse.Builder()
                    .responseCode(200)
                    .responseMessage("File uploaded")
                    .build();
        }catch (Exception ex){

            if (ex instanceof S3Exception s3Exception){
                return new StorageResponse.Builder()
                        .responseCode(s3Exception.statusCode())
                        .responseMessage(s3Exception.getMessage())
                        .build();
            }

            return new StorageResponse.Builder()
                    .responseCode(500)
                    .responseMessage("Failed")
                    .build();
        }
    }
}
