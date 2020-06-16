package com.avalldeperas.awsimageupload.profile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
public class UserProfileController {

    private static final Logger logger = LogManager.getLogger(UserProfileController.class);
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles(){
        logger.info("inside getUserProfiles()");
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable UUID userProfileId, @RequestParam MultipartFile file){
        logger.info("inside uploadUserProfileImage() - userProfileId = " + userProfileId);
        userProfileService.uploadUserProfileImage(userProfileId, file);
    }

}
