package pl.kolak.bookhotelroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kolak.bookhotelroom.exceptions.RoomDoNotAddedException;
import pl.kolak.bookhotelroom.exceptions.RoomDoNotUpdateException;
import pl.kolak.bookhotelroom.models.Room;
import pl.kolak.bookhotelroom.services.RoomService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoomController {
    RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms/all")
    public List<Room> getAllRooms(){
        return roomService.rooms();
    }

    @GetMapping(value = "/rooms")
    public List<Room> bookingGet(@RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "limit", defaultValue = "30") int limit) {
        return roomService.findAll(page, limit);
    }

    @GetMapping("/room/{id}")
    public Room getRoomById(@PathVariable(name = "id")Long id){
        return roomService.roomById(id);
    }

    @GetMapping("/room/{id}/date")
    public List<Map<String, LocalDate>> dateAvailableRoom(@PathVariable(name = "id")Long id){
        return roomService.dateAvailableRoom(id);
    }

    @PostMapping("/room/add")
    public ResponseEntity<Room> addRoom(@RequestBody @Valid Room room, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){

            roomService.create(room);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(room.getId()).toUri();
            return ResponseEntity.created(location).body(room);
        }else{
            throw new RoomDoNotAddedException();
        }
    }

    @DeleteMapping("/room/{id}/delete")
    public void delete(@PathVariable(name = "id") Long id){
        roomService.delete(id);
    }

    @PutMapping("/room/{id}/update")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody @Valid Room room, BindingResult result){
        if(!result.hasErrors()){
            roomService.modify(id,room);
        }else
            throw new RoomDoNotUpdateException();
    }
}
