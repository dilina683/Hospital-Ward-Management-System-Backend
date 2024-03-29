package group17.HospitalWardManagementSystem.Repository;

import group17.HospitalWardManagementSystem.Model.Domain.Staff;
import group17.HospitalWardManagementSystem.Model.Domain.User;
import group17.HospitalWardManagementSystem.Model.Domain.Ward;
import group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    Optional<User> findByNic(String nic);

    Optional<User> findByEmail(String email);

    @Query("SELECT A.fullName FROM User A INNER JOIN Staff B ON B.nic=A.nic WHERE B.wardNo=:wardNo AND A.Position='Sister'")
    String findBy(@Param("wardNo") Ward wardNo);

    @Query("SELECT new group17.HospitalWardManagementSystem.Model.Dto.StaffDto.ShowStaffDto(A.nic,A.fullName,A.mobileNo,A.email)  FROM User A INNER JOIN Staff B ON B.nic=A.nic WHERE B.wardNo=:wardNo ANd A.Position='Nurse'")
    List<ShowStaffDto> findByWardNo(@Param("wardNo") Ward wardNo);
}

