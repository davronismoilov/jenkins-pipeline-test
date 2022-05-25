package task1.isdaha.entity;

import lombok.*;
import task1.isdaha.enums.TicketStatus;

import javax.persistence.*;

@Entity()
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class Ticket extends  BaseEntity<String > {

    @OneToOne
    private Seats seatNumber;

    private String telephoneNumber;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;


    @OneToOne
    private Program program;


}
