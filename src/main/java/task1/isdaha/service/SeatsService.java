package task1.isdaha.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import task1.isdaha.entity.Seats;
import task1.isdaha.exception.CustomException;
import task1.isdaha.repository.SeatsRepository;
import task1.isdaha.response.Response;
import task1.isdaha.payload.SeatDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatsService  implements  BaseService<SeatDto> {
    private  final SeatsRepository seatsRepository;

    // seatlar faqat bir marta kiritiladi keyin avtomatik yaratiladi
    @Override
    public Response add(SeatDto seatDto) {
        List<Seats> seatsList = new ArrayList<>();
        for (int i = 1; i <= seatDto.getRowCount(); i++) {
            for (int j = 1; j <= seatDto.getColumnCount(); j++) {
                seatsList.add(seatsRepository.save(new Seats(i , j)));
            }
        }

        return Response.builder()
            .message("success")
            .statusCode(12)
            .data(seatsList).build();
    }

    @Override
    public Response getById(Long id) {
        return Response.builder()
            .message("succes")
            .statusCode(12)
            .data(seatsRepository.findById(id).orElseThrow(
                ()-> new CustomException("Not founs")
            )).build();
    }

    @Override
    public Response all(int pageSize, int pageNumber) {
        return Response.builder()
            .message("succes")
            .statusCode(12)
            .data(
                seatsRepository.findAll((PageRequest.of(pageNumber , pageSize))).getContent()).
            build();
    }

    @Override
    public Response update(Long id, SeatDto seatDto) {
        //TODO
//        Program map = modelMapper.map(programDto, Program.class);
//        map.setId(id);
//        return Response.builder()
//            .message("success")
//            .statusCode(12)
//            .data(seatsRepository.save(map)).build();
        return null;
    }

    @Override
    public Response deleteById(long id) {
        try {
            seatsRepository.deleteById(id);
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
