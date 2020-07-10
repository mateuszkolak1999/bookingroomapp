package pl.kolak.bookhotelroom.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;
    @Min(0)
    private int people;
    private boolean available;
    private float costPerDay;
    @ManyToMany
    @JoinTable(name = "room_equipment",
        joinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")},
    inverseJoinColumns = {@JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id")})
    private List<Equipment> equipment = new ArrayList<>();
    @ManyToMany(mappedBy = "rooms")
    @JsonIgnore
    private List<Booking> bookingList;

    public Room() {
    }

    public Room(int people, boolean available, float costPerDay) {
        this.people = people;
        this.available = available;
        this.costPerDay = costPerDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public float getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(float costPerDay) {
        this.costPerDay = costPerDay;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                people == room.people &&
                available == room.available &&
                Float.compare(room.costPerDay, costPerDay) == 0 &&
                Objects.equals(equipment, room.equipment) &&
                Objects.equals(bookingList, room.bookingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, people, available, costPerDay, equipment, bookingList);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", people=" + people +
                ", available=" + available +
                ", costPerDay=" + costPerDay +
                ", equipment=" + equipment +
                ", bookingList=" + bookingList +
                '}';
    }
}
