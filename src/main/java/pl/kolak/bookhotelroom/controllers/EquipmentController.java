package pl.kolak.bookhotelroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kolak.bookhotelroom.exceptions.EquipmentDoNotAddedException;
import pl.kolak.bookhotelroom.exceptions.EquipmentDoNotUpdateException;
import pl.kolak.bookhotelroom.models.Equipment;
import pl.kolak.bookhotelroom.services.EquipmentService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EquipmentController {
    EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/equipmentList/all")
    public List<Equipment> getAllEquipments(){
        return equipmentService.equipmentList();
    }

    @GetMapping(value = "/equipmentList")
    public List<Equipment> bookingGet(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "30") int limit) {
        return equipmentService.findAll(page, limit);
    }

    @GetMapping("/equipment/{id}")
    public Equipment getEquipmentById(@PathVariable(name = "id")Long id){
        return equipmentService.equipmentById(id);
    }

    @PostMapping("/equipment/add")
    public ResponseEntity<Equipment> addEquipment(@RequestBody @Valid Equipment equipment, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            equipmentService.create(equipment);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(equipment.getId()).toUri();
            return ResponseEntity.created(location).body(equipment);
        }else{
            throw new EquipmentDoNotAddedException();
        }
    }

    @DeleteMapping("/equipment/{id}/delete")
    public void delete(@PathVariable(name = "id") Long id){
        equipmentService.delete(id);
    }

    @PutMapping("/equipment/{id}/update")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody @Valid Equipment equipment, BindingResult result){
        if(!result.hasErrors()){
            equipmentService.modify(id,equipment);
        }else
            throw new EquipmentDoNotUpdateException();
    }
}
