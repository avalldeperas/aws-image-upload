package com.avalldeperas.awsimageupload.buckets;

import lombok.Getter;

public enum BucketName {
    PROFILE_IMAGE("avalldeperas-image-upload-15062020");

    @Getter
    private final String bucketName;

    BucketName(String name) {
        this.bucketName = name;
    }
}
