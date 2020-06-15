package com.avalldeperas.awsimageupload.datastore;

import com.avalldeperas.awsimageupload.model.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = Arrays.asList(
            new UserProfile(UUID.randomUUID(), "avalldeperas", null),
            new UserProfile(UUID.randomUUID(), "pgarcia", null),
            new UserProfile(UUID.randomUUID(), "emartinez", null)
    );

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
