package com.avalldeperas.awsimageupload.model;

import lombok.Getter;

public enum AWS_KeyLabel {
    AWS_ACCESS_KEY_ID("AWSAccessKeyId"),
    AWS_SECRET_KEY("AWSSecretKey");

    @Getter
    private final String keyLabel;

    AWS_KeyLabel(String keyLabel) {
        this.keyLabel = keyLabel;
    }

    public boolean equalValue(String passedValue) {
        return this.keyLabel.equals(passedValue);
    }
}
