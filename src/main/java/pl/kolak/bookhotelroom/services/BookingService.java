package pl.kolak.bookhotelroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.kolak.bookhotelroom.exceptions.BookingDoNotDeletedException;
import pl.kolak.bookhotelroom.exceptions.RoomIsNotAvailableException;
import pl.kolak.bookhotelroom.models.Booking;
import pl.kolak.bookhotelroom.models.Room;
import pl.kolak.bookhotelroom.repositories.BookingRepository;
import pl.kolak.bookhotelroom.repositories.RoomRepository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    BookingRepository bookingRepository;
    RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public List<Booking> bookingList(){
        return bookingRepository.findAll();
    }

    public List<Booking> findAll(int page, int limit, String orderBy, String direction) {
        Sort sort=null;
        if (direction.equals("DESC"))
            sort = Sort.by(orderBy).descending();
        else
            sort = Sort.by(orderBy).ascending();
        PageRequest pageRequest = PageRequest.of(page, limit,sort);
        Page<Booking> bookings = bookingRepository.findAll(pageRequest);
        return bookings.getContent();
    }

    public Booking bookingById(Long id){
        return bookingRepository.findById(id).get();
    }

    @Transactional
    public void create(Booking booking){
        float cost=0;
        LocalDate booking_from = booking.getBooking_from();
        LocalDate booking_to = booking.getBooking_to();
        int days = (int)ChronoUnit.DAYS.between(booking_from, booking_to);
        List<Room> rooms = booking.getRooms();
        for (Room r:rooms) {
            long roomId = r.getId();
            Room room = roomRepository.findById(roomId).get();
            if(room.getAvailable()){
                float costPerDay = room.getCostPerDay();
                cost+=costPerDay*days;
                room.setLeftDay(days);
                room.setAvailable(false);
                roomRepository.save(room);
            }else{
                throw new RoomIsNotAvailableException();
            }
        }
        booking.setCost(cost);
        bookingRepository.save(booking);
    }

    public void delete(Long id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isPresent())
            bookingRepository.delete(booking.get());
        else
            throw new BookingDoNotDeletedException();
    }

    public void modify(Long id,Booking booking){
        booking.setId(id);
        bookingRepository.save(booking);
    }
}
