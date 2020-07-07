package pl.kolak.bookhotelroom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kolak.bookhotelroom.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
}
