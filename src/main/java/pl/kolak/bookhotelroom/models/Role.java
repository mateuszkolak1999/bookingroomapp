package pl.kolak.bookhotelroom.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;
    @NotEmpty
    @Size(min = 4)
    private String name;
    private float salary;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<Staff> staff;

    public Role() {
    }

    public Role(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Float.compare(role.salary, salary) == 0 &&
                Objects.equals(name, role.name) &&
                Objects.equals(staff, role.staff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, staff);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", staff=" + staff +
                '}';
    }
}
