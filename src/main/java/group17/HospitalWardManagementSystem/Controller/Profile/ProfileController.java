package group17.HospitalWardManagementSystem.Controller.Profile;

import group17.HospitalWardManagementSystem.Service.Profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile-picture")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/add")
    public ResponseEntity<String> addProfilePicture(@RequestParam("profilePicture")MultipartFile file,
                                                    @RequestParam("nic") String nic){
        try{
            profileService.addProfilePicture(file,nic);
            return ResponseEntity.ok("Successfully Updated the Profile Picture");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Profile Picture Updated Failed");
        }
    }

    @GetMapping("/load/{nic}")
    public ResponseEntity<String> loadProfilePicture(@PathVariable String nic){
        try{
            return ResponseEntity.ok(profileService.retrieveProPicture(nic));
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}