package pl.kolak.bookhotelroom.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private long id;
    @NotEmpty
    private String booking_from;
    @NotEmpty
    private String booking_to;
    private float cost;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToMany
    @JoinTable(name = "booking_room",
            joinColumns = {@JoinColumn(name = "booking_id", referencedColumnName = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")})
    private List<Room> rooms;

    public Booking() {
    }

    public Booking(String booking_from, String booking_to, float cost, Customer customer) {
        this.booking_from = booking_from;
        this.booking_to = booking_to;
        this.cost = cost;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBooking_from(String booking_from) {
        this.booking_from = booking_from;
    }

    public String getBooking_form() {
        return booking_from;
    }

    public String getBooking_to() {
        return booking_to;
    }

    public void setBooking_to(String booking_to) {
        this.booking_to = booking_to;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id &&
                Float.compare(booking.cost, cost) == 0 &&
                Objects.equals(booking_from, booking.booking_from) &&
                Objects.equals(booking_to, booking.booking_to) &&
                Objects.equals(customer, booking.customer) &&
                Objects.equals(rooms, booking.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, booking_from, booking_to, cost, customer, rooms);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", booking_form='" + booking_from + '\'' +
                ", booking_to='" + booking_to + '\'' +
                ", cost=" + cost +
                ", customer=" + customer +
                ", rooms=" + rooms +
                '}';
    }
}
