package com.avalldeperas.awsimageupload.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
public class UserProfile {

    @Getter @Setter
    private UUID userProfileId;
    @Getter @Setter
    private String username;
    @Setter
    private String userProfileImageLink;

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }
}
