package com.mkaz.website.service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author mkazmyryk
 */
public class AmazonS3Service {
    private static final String accessKey = "AKIAIYOQEA7BVHMXWUUQ";
    private static final String secretKey = "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";
    private static final String bucket_name = "topgameswebapp";

    public static void uploadFile(File file) {
        String file_path = file.getAbsolutePath();
        String key_name = file.getName();

        System.out.format("Uploading %s to S3 bucket %s...\n", file_path, bucket_name);

        Region region = com.amazonaws.services.s3.model.Region.EU_Frankfurt.toAWSRegion();
        BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3 = AmazonS3Client.builder().withRegion(region.toString())
                .withCredentials(new AWSStaticCredentialsProvider(creds)).build();

        try {
            s3.putObject(bucket_name, key_name, new File(file_path));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }

    public static void downloadFile(String fileName) {
        Region region = com.amazonaws.services.s3.model.Region.EU_Frankfurt.toAWSRegion();
        BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3 = AmazonS3Client.builder().withRegion(region.toString())
                .withCredentials(new AWSStaticCredentialsProvider(creds)).build();

        try {
            S3Object o = s3.getObject(bucket_name, fileName);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static String getURL(String fileName) {
        return "https://s3.eu-central-1.amazonaws.com/" + bucket_name + "/" + fileName;
    }

}
