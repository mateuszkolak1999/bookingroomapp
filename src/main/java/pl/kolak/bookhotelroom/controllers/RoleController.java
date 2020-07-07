package pl.kolak.bookhotelroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kolak.bookhotelroom.exceptions.RoleDoNotAddedException;
import pl.kolak.bookhotelroom.exceptions.RoleDoNotUpdateException;
import pl.kolak.bookhotelroom.models.Role;
import pl.kolak.bookhotelroom.services.RoleService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {
    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roleList/all")
    public List<Role> getAllRole(){
        return roleService.roleList();
    }

    @GetMapping(value = "/roleList")
    public List<Role> bookingGet(@RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "limit", defaultValue = "30") int limit) {
        return roleService.findAll(page, limit);
    }

    @GetMapping("/role/{id}")
    public Role getRoleById(@PathVariable(name = "id")Long id){
        return roleService.roleById(id);
    }

    @PostMapping("/role/add")
    public ResponseEntity<Role> addRole(@RequestBody @Valid Role role, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            roleService.create(role);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(role.getId()).toUri();
            return ResponseEntity.created(location).body(role);
        }else{
            throw new RoleDoNotAddedException();
        }
    }

    @DeleteMapping("/role/{id}/delete")
    public void delete(@PathVariable(name = "id") Long id){
        roleService.delete(id);
    }

    @PutMapping("/role/{id}/update")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody @Valid Role role, BindingResult result){
        if(!result.hasErrors()){
            roleService.modify(id,role);
        }else
            throw new RoleDoNotUpdateException();
    }
}
