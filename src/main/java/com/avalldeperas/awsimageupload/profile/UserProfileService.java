package com.avalldeperas.awsimageupload.profile;

import com.amazonaws.services.kendra.model.ContentType;
import com.avalldeperas.awsimageupload.buckets.BucketName;
import com.avalldeperas.awsimageupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        ifFileEmpty(file);
        isFileImage(file);
        UserProfile user = getUserProfileOrThrow(userProfileId);
        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String fileName = String.format("%s-%s", file.getName(), user.getUserProfileId());
        try {
            fileStore.save(path, fileName, file.getInputStream(), Optional.of(metadata));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService.getUserProfiles().stream()
                    .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }

    private void isFileImage(MultipartFile file) {
        if(!Arrays.asList(IMAGE_JPEG, IMAGE_GIF, IMAGE_PNG).contains(file.getContentType()))
            throw new IllegalStateException("File must be an image.");
    }

    private void ifFileEmpty(MultipartFile file) {
        if(file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file ["+ file.getSize() + "]");
    }
}
