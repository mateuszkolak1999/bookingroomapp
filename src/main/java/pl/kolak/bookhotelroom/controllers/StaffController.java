package pl.kolak.bookhotelroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kolak.bookhotelroom.exceptions.StaffDoNotAddedException;
import pl.kolak.bookhotelroom.exceptions.StaffDoNotUpdateException;
import pl.kolak.bookhotelroom.models.Role;
import pl.kolak.bookhotelroom.models.Staff;
import pl.kolak.bookhotelroom.models.StaffDetails;
import pl.kolak.bookhotelroom.services.StaffService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StaffController {
    StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staffList/all")
    public List<Staff> getAllStaff(){
        return staffService.getStaffList();
    }

    @GetMapping(value = "/staffList")
    public List<Staff> bookingGet(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "limit", defaultValue = "30") int limit) {
        return staffService.findAll(page, limit);
    }

    @GetMapping("/staff/{id}")
    public Staff getStaffById(@PathVariable(name = "id")Long id){
        return staffService.getStaffById(id);
    }

    @PostMapping("/staff/add")
    public ResponseEntity<Staff> addStaff(@RequestBody @Valid Staff staff, BindingResult bindingResultStaff){
        if(!bindingResultStaff.hasErrors()){
            StaffDetails staffDetails = staff.getStaffDetails();
            Role role = staff.getRole();
            staffService.addStaff(staff,staffDetails, role);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(staff.getId()).toUri();
            return ResponseEntity.created(location).body(staff);
        }else{
            throw new StaffDoNotAddedException();
        }
    }

    @DeleteMapping("/staff/{id}/delete")
    public void delete(@PathVariable(name = "id") Long id){
        staffService.delete(id);
    }

    @PutMapping("/staff/{id}/update")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody @Valid Staff staff, BindingResult result){
        if(!result.hasErrors()){
            staffService.modify(id,staff);
        }else
            throw new StaffDoNotUpdateException();
    }
}
