package task1.isdaha.payload;

import lombok.Data;
import task1.isdaha.enums.ProgramType;

@Data
public class ProgramDto {
    private String programName;
    private ProgramType programType;
    private String date;
    private String time;
}
