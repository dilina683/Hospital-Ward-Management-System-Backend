package group17.HospitalWardManagementSystem.Model.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requested_leaves")
public class RequestLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;

    @ManyToOne
    @JoinColumn(name = "staff_nic",referencedColumnName = "nic")
    private Staff staff;

    @Column(nullable = false)
    private LocalDate leaveBeginDate;

    @Column(nullable = false)
    private LocalDate leaveEndDate;

    @Column(nullable = false)
    private LocalDateTime requestedDateAndTime;

    @Column(nullable = false)
    private String reason;

}
