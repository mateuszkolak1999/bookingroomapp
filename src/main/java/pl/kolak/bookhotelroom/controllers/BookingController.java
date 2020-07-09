package pl.kolak.bookhotelroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kolak.bookhotelroom.exceptions.BookingDoNotCreatedException;
import pl.kolak.bookhotelroom.exceptions.BookingDoNotUpdateException;
import pl.kolak.bookhotelroom.exceptions.RoomIsNotAvailableException;
import pl.kolak.bookhotelroom.models.Booking;
import pl.kolak.bookhotelroom.services.BookingService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookingList/all")
    public List<Booking> bookingAll(){
        return bookingService.bookingList();
    }

    @GetMapping(value = "/bookingList")
    public List<Booking> bookingGet(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "limit", defaultValue = "30") int limit,
                                    @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                    @RequestParam(value = "direction", defaultValue = "ASC")String direction){
        return bookingService.findAll(page, limit, orderBy, direction);
    }

    @GetMapping("/booking/{id}")
    public Booking bookingById(@PathVariable(name = "id")Long id){
        return bookingService.bookingById(id);
    }

    @PostMapping(path = "/booking/create")
    public ResponseEntity<Booking> create(@RequestBody @Valid Booking booking, BindingResult result){
        if(!result.hasErrors()) {
            bookingService.create(booking);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(booking.getId()).toUri();
            return ResponseEntity.created(location).body(booking);
        }else{
            throw new BookingDoNotCreatedException();
        }
    }

    @DeleteMapping("/booking/{id}/delete")
    public void delete(@PathVariable(name = "id") Long id){
        bookingService.delete(id);
    }

    @PutMapping("/booking/{id}/update")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody @Valid Booking booking, BindingResult result){
        if(!result.hasErrors()){
            bookingService.modify(id,booking);
        }else
            throw new BookingDoNotUpdateException();
    }

    @ExceptionHandler(RoomIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error userNotFound(RoomIsNotAvailableException e){
        return new Error("Booking canceled. Room is not available.");
    }
}
