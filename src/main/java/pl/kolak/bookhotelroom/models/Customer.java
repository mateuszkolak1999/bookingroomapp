package pl.kolak.bookhotelroom.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;
    @NotEmpty
    @Size(min = 4)
    @Column(name = "username")
    private String userName;
    @NotEmpty
    @Size(min = 4)
    private String password;
    @NotEmpty
    @Email
    private String email;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_details_id")
    private CustomerDetails customerDetails;
    @JsonIgnore
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Booking> bookingList;

    public Customer() {
    }

    public Customer(String userName, String password, String email, CustomerDetails customerDetails) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.customerDetails = customerDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
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
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(userName, customer.userName) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(customerDetails, customer.customerDetails) &&
                Objects.equals(bookingList, customer.bookingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email, customerDetails, bookingList);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", customerDetails=" + customerDetails +
                ", bookingList=" + bookingList +
                '}';
    }
}
