package pl.kolak.bookhotelroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kolak.bookhotelroom.models.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
}
