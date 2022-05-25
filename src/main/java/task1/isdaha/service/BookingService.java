package task1.isdaha.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task1.isdaha.payload.BookingDto;
import task1.isdaha.repository.TicketRepository;
import task1.isdaha.response.Response;
import task1.isdaha.entity.Program;
import task1.isdaha.entity.Seats;
import task1.isdaha.entity.Ticket;
import task1.isdaha.enums.TicketStatus;
import task1.isdaha.exception.CustomException;
import task1.isdaha.repository.ProgramRepository;
import task1.isdaha.repository.SeatsRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final SeatsRepository seatsRepository;
    private final ProgramRepository programRepository;
    private final TicketRepository ticketRepository;

    public Response booking(BookingDto bookingDto) {
        Seats seat = seatsRepository.findById(bookingDto.getSeatId()).orElseThrow(
            () -> new CustomException("seat not found"));

        Program pr = programRepository.findById(bookingDto.getProgramId()).orElseThrow(
            () -> new CustomException("program not found"));

        Ticket ticket = ticketRepository.findBySeatNumber(seat).orElse(null);
        if (ticket == null) {
            Ticket ticketBuilder = Ticket.builder()
                .seatNumber(seat)
                .program(pr)
                .status(TicketStatus.ORDERED)
                .telephoneNumber(bookingDto.getTelephoneNumber()).build();

            ticketRepository.save(ticketBuilder);
            return Response.builder()
                .message("successfully booking ")
                .statusCode(12)
                .build();
        } else if (ticket.getStatus().equals(TicketStatus.ORDERED) || ticket.getStatus().equals(TicketStatus.BOUGHT))
            return Response.builder()
                .message("already bought or ordered")
                .statusCode(-90)
                .build();

        Ticket ticketBuilder = Ticket.builder()
            .seatNumber(seat)
            .program(pr)
            .status(TicketStatus.ORDERED)
            .telephoneNumber(bookingDto.getTelephoneNumber()).build();

        ticketRepository.save(ticketBuilder);
        return Response.builder()
            .message("successfully booking ")
            .statusCode(12)
            .build();

    }

    public Response changeStatus(long id, String status) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(
            () -> new CustomException("ticket not found")
        );

        ticket.setStatus(TicketStatus.valueOf(status));
        return Response.builder()
            .message("success")
            .statusCode(121)
            .data(ticketRepository.save(ticket))
            .build();
    }

    public Response allTicket() {
        return Response.builder()
            .message("all tickets")
            .statusCode(2212)
            .data(ticketRepository.findAll())
            .build();
    }

    public void savePdfTicket(long id) {
        //created PDF document instance

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new CustomException("not found"));

        StringBuffer sbuf = new StringBuffer();
        sbuf.append("Program name :  " +  ticket.getProgram().getProgramName());
        sbuf.append("\nDate :  " + ticket.getProgram().getDate());
        sbuf.append("\nTime : " +  ticket.getProgram().getTime());
        sbuf.append("\nLocation :  \n" + ticket.getSeatNumber().getRow() + " - row \n");
        sbuf.append(ticket.getSeatNumber().getNumber() +  " - seat");

        Document doc = new Document();
        try {

            PdfWriter writer = PdfWriter.getInstance(doc,
                new FileOutputStream("/Users/ismoilovdavron/Documents/Documents/task/TripProject-main/tickets/ticket"+ UUID.randomUUID()+".pdf"));
            System.out.println("PDF created.");

            doc.open();
            doc.add(new Paragraph(sbuf.toString()));
            doc.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
