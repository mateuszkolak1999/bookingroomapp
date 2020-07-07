package pl.kolak.bookhotelroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kolak.bookhotelroom.models.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
}
