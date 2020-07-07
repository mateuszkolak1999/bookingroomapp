package pl.kolak.bookhotelroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kolak.bookhotelroom.models.Role;
import pl.kolak.bookhotelroom.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> roleList(){
        return roleRepository.findAll();
    }

    public List<Role> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Role> roles = roleRepository.findAll(pageRequest);
        return roles.getContent();
    }

    public Role roleById(Long id){
        return roleRepository.findById(id).get();
    }

    public void create(Role role){
        roleRepository.save(role);
    }

    public void delete(Long id){
        Optional<Role> role = roleRepository.findById(id);
        role.ifPresent(r -> roleRepository.delete(r));
    }

    public void modify(Long id,Role role){
        role.setId(id);
        roleRepository.save(role);
    }
}
