package group17.HospitalWardManagementSystem.Service.WardDetails;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.WardNumbersDto;
import group17.HospitalWardManagementSystem.Model.Dto.WardDto.ShowWardDto;
import group17.HospitalWardManagementSystem.Repository.StaffRepository;
import group17.HospitalWardManagementSystem.Repository.UserRepository;
import group17.HospitalWardManagementSystem.Repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowWardService {

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    public ShowWardDto showWardDetails(String wardName){

        ShowWardDto wardDto=new ShowWardDto();

        Ward targetWard=findWard(wardName);

        wardDto.setWardNo(targetWard.getWardNo());
        wardDto.setWardName(targetWard.getWardName());
        wardDto.setNumberOfNurses(targetWard.getNumberOfNurses());
        wardDto.setSisterName(findSister(targetWard));

        return wardDto;

    }

    public Ward findWard(String wardName){
        return wardRepository.findByWardName(wardName);
    }

    public String findSister(Ward wardNo){
        Staff staff = staffRepository.findSisterByWard(wardNo);
        if (staff != null) {
            return findSisterName(staff.getNic());
        } else {
            System.out.println("FindSister error");
            return null; // or handle the case where staff is null
        }
    }


    public String findSisterName(String nic){
        Optional<User> user = userRepository.findByNic(nic);
        if(user.isPresent()){
            User user1 = user.get();
            return user1.getFullName();
        }
        else{
            System.out.println("user is null");
            return null;

        }
    }

}
