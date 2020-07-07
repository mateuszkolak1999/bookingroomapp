package pl.kolak.bookhotelroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kolak.bookhotelroom.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
