package com.avalldeperas.awsimageupload.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AWSCredential {

    @Getter @Setter
    private String aWSAccessKeyId;
    @Getter @Setter
    private String aWSSecretKey;
}
