package group17.HospitalWardManagementSystem.Controller.LeaveRequest;

import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.MemberDto;
import group17.HospitalWardManagementSystem.Model.Dto.RequestLeaveDto.RequestLeaveDto;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_DisplayData;
import group17.HospitalWardManagementSystem.Service.RequestLeave.RequestLeaveService_SaveData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeaveRequestController {

    @Autowired
    private RequestLeaveService_DisplayData requestLeaveService;

    @Autowired
    private RequestLeaveService_SaveData requestLeaveServiceSaveData;


    @GetMapping("/get-user/{username}")
    public MemberDto getUserDetails(@PathVariable String username){
        return requestLeaveService.provideAutoFilings(username);
    }

    @PostMapping("/request-leave")
    public ResponseEntity<String> requestLeave (@RequestBody RequestLeaveDto requestLeaveDto){

        boolean saveSuccessful = requestLeaveServiceSaveData.saveLeave(requestLeaveDto);

        if (saveSuccessful) {
            return ResponseEntity.ok("Leave requested successfully");
        } else {
            // If the save operation fails, you might want to return an appropriate error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to request leave");
        }
    }
}
