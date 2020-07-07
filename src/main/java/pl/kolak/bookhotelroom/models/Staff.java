package pl.kolak.bookhotelroom.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "staff")
public class Staff implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private long id;

    @NotEmpty
    @Size(min = 4)
    private String username;

    @NotEmpty
    @Size(min = 4)
    private String password;

    @NotEmpty
    @Email
    private String email;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "staff_details_id")
    private StaffDetails staffDetails;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Staff() {
    }

    public Staff(String username, String password, String email, StaffDetails staffDetails, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.staffDetails = staffDetails;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public StaffDetails getStaffDetails() {
        return staffDetails;
    }

    public void setStaffDetails(StaffDetails staffDetails) {
        this.staffDetails = staffDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id &&
                Objects.equals(username, staff.username) &&
                Objects.equals(password, staff.password) &&
                Objects.equals(email, staff.email) &&
                Objects.equals(staffDetails, staff.staffDetails) &&
                Objects.equals(role, staff.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, staffDetails, role);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", staffDetails=" + staffDetails +
                ", role=" + role +
                '}';
    }
}
