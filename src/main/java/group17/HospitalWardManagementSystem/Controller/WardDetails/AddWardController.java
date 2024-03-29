package group17.HospitalWardManagementSystem.Controller.WardDetails;

import group17.HospitalWardManagementSystem.Model.Dto.WardDto.AddWardDto;
import group17.HospitalWardManagementSystem.Service.WardDetails.AddWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ward")
public class AddWardController {

    @Autowired
    public AddWardService addWardService;

    @PostMapping("/add")
    public String addWard(@RequestBody AddWardDto addWardDto){

        return addWardService.saveWard(addWardDto);
    }
}
