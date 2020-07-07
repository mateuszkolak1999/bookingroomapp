package pl.kolak.bookhotelroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kolak.bookhotelroom.models.Booking;
import pl.kolak.bookhotelroom.models.Role;
import pl.kolak.bookhotelroom.models.Staff;
import pl.kolak.bookhotelroom.models.StaffDetails;
import pl.kolak.bookhotelroom.repositories.StaffRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getStaffList(){
        List<Staff> all = staffRepository.findAll();
        return all;
    }

    public List<Staff> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Staff> staffPage = staffRepository.findAll(pageRequest);
        return staffPage.getContent();
    }

    public Staff getStaffById(Long id){
        return staffRepository.findById(id).get();
    }

    @Transactional
    public void addStaff(Staff staff, StaffDetails staffDetails, Role role){
        staff.setStaffDetails(staffDetails);
        staff.setRole(role);
        staffRepository.save(staff);
    }

    @Transactional
    public void delete(Long id){
        Optional<Staff> booking = staffRepository.findById(id);
        booking.ifPresent(staff -> staffRepository.delete(staff));
    }

    @Transactional
    public void modify(Long id, Staff staff){
        staff.setId(id);
        Staff staffFind = staffRepository.findById(id).get();
        staff.getStaffDetails().setId(staffFind.getStaffDetails().getId());
        staffRepository.save(staff);
    }
}
