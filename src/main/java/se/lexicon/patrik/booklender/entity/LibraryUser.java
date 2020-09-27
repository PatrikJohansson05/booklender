package se.lexicon.patrik.booklender.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private LocalDate RegDate;
    private String name;
    @Column(unique = true)
    private String email;

    public LibraryUser(LocalDate regDate, String name, String email){
        RegDate = regDate;
        this.name = name;
        this.email = email;
    }

    public LibraryUser() {
    }

    public int getUserId() {
        return userId;
    }


    public LocalDate getRegDate() {
        return RegDate;
    }

    public void setRegDate(LocalDate regDate) {
        RegDate = regDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, RegDate, name, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LibraryUser other = (LibraryUser) obj;
        return Objects.equals(email, other.email) && Objects.equals(name, other.name)
                && Objects.equals(RegDate, other.RegDate) && userId == other.userId;
    }

    @Override
    public String toString() {
        return "LibraryUser{" +
                "userId=" + userId +
                ", RegDate=" + RegDate +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
