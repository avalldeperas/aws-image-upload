package com.avalldeperas.awsimageupload.profile;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
public class UserProfile {

    @Getter
    private final UUID userProfileId;
    @Getter
    private final String username;
    @Setter
    private String userProfileImageLink;

    public Optional<String> getUserProfileImageLink() {
        return Optional.ofNullable(userProfileImageLink);
    }
}
