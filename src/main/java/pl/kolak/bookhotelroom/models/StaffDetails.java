package pl.kolak.bookhotelroom.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "staff_details")
public class StaffDetails implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_details_id")
    private long id;
    @NotEmpty
    @Column(name = "firstname")
    private String firstName;
    @NotEmpty
    @Column(name = "lastname")
    private String lastName;
    @NotEmpty
    @Digits(integer = 11,fraction = 0)
    private String pesel;
    @JsonIgnore
    @OneToOne(mappedBy = "staffDetails")
    private Staff staff;

    public StaffDetails() {
    }

    public StaffDetails(String firstName, String lastName, String pesel, Staff staff) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.staff = staff;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffDetails that = (StaffDetails) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(pesel, that.pesel) &&
                Objects.equals(staff, that.staff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, pesel, staff);
    }

    @Override
    public String toString() {
        return "StaffDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", staff=" + staff +
                '}';
    }
}
