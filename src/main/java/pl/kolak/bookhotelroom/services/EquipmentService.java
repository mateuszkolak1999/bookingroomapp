package pl.kolak.bookhotelroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kolak.bookhotelroom.models.Equipment;
import pl.kolak.bookhotelroom.models.Role;
import pl.kolak.bookhotelroom.repositories.EquipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> equipmentList(){
        return equipmentRepository.findAll();
    }

    public List<Equipment> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Equipment> equipmentPage = equipmentRepository.findAll(pageRequest);
        return equipmentPage.getContent();
    }

    public Equipment equipmentById(Long id){
        return equipmentRepository.findById(id).get();
    }

    public void create(Equipment equipment){
        equipmentRepository.save(equipment);
    }

    public void delete(Long id){
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        equipment.ifPresent(e -> equipmentRepository.delete(e));
    }

    public void modify(Long id,Equipment equipment){
        equipment.setId(id);
        equipmentRepository.save(equipment);
    }
}
