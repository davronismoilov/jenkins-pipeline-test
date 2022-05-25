package task1.isdaha.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import task1.isdaha.payload.ProgramDto;
import task1.isdaha.response.Response;
import task1.isdaha.entity.Program;
import task1.isdaha.exception.CustomException;
import task1.isdaha.repository.ProgramRepository;

@Service
@RequiredArgsConstructor
public class ProgramService implements BaseService<ProgramDto> {

    private final ProgramRepository programRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response add(ProgramDto programDto) {
        Program map = modelMapper.map(programDto, Program.class);
        return Response.builder()
            .message("success")
            .statusCode(12)
            .data(programRepository.save(map)).build();
    }

    @Override
    public Response getById(Long id) {
        return Response.builder()
            .message("succes")
            .statusCode(12)
            .data(programRepository.findById(id).orElseThrow(
                ()-> new CustomException("Not founs")
            )).build();
    }

    @Override
    public Response all(int pageSize, int pageNumber) {
        return Response.builder()
            .message("succes")
            .statusCode(12)
            .data(
                programRepository.findAll((PageRequest.of(pageNumber , pageSize))).getContent()).
            build();
    }

    @Override
    public Response update(Long id, ProgramDto programDto) {
        Program map = modelMapper.map(programDto, Program.class);
        map.setId(id);
        return Response.builder()
            .message("success")
            .statusCode(12)
            .data(programRepository.save(map)).build();
    }

    @Override
    public Response deleteById(long id) {
        try {
            programRepository.deleteById(id);
        } catch (Exception e){
            Response.builder()
                .message("not found")
                .statusCode(-10).build();
        }



        return Response.builder()
            .message("success")
            .statusCode(12).build();
    }
}
