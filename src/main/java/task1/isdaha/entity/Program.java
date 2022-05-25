package task1.isdaha.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import task1.isdaha.enums.ProgramType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "program")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Program extends BaseEntity<String> {

    @Column(nullable = false)
    private String programName;
    @Enumerated(EnumType.STRING)
    private ProgramType programType;
    private String date;
    private String time;
}
