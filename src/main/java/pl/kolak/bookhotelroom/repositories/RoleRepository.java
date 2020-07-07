package pl.kolak.bookhotelroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kolak.bookhotelroom.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
