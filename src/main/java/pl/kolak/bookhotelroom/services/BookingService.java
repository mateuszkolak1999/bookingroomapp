package pl.kolak.bookhotelroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.kolak.bookhotelroom.exceptions.BookingDoNotDeletedException;
import pl.kolak.bookhotelroom.models.Booking;
import pl.kolak.bookhotelroom.repositories.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> bookingList(){
        return bookingRepository.findAll();
    }

    public List<Booking> findAll(int page, int limit, String orderBy, String direction) {
        Sort sort=null;
        if (direction.equals("ASC"))
            sort = Sort.by(orderBy).ascending();
        else if(direction.equals("DESC"))
            sort = Sort.by(orderBy).descending();
        PageRequest pageRequest = PageRequest.of(page, limit,sort);
        Page<Booking> bookings = bookingRepository.findAll(pageRequest);
        return bookings.getContent();
    }

    public Booking bookingById(Long id){
        return bookingRepository.findById(id).get();
    }

    public void create(Booking booking){
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
