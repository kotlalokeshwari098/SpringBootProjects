package com.javaspring.server.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


//CREATE TABLE users (
//        user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        username VARCHAR(20) UNIQUE,
//email VARCHAR(50) UNIQUE,
//  ...
//          );
@Entity
@Table(name="users",
         uniqueConstraints = {
           @UniqueConstraint(columnNames="username"),
                 @UniqueConstraint(columnNames="email")
         }
        )
public class User {

       @Id
       @GeneratedValue(strategy=GenerationType.IDENTITY)
       @Column(name="user_id")
       private Long userId;

       @NotBlank
       @Size(max=20)
       @Column(name="username")
       private String username;

       @NotBlank
       @Size(max=50)
       @Email
       @Column(name="email")
       private String email;

       @Size(max=120)
       @Column(name = "password")
       @JsonIgnore
       //so that this field not show in the api response
       private String password;

       private boolean accountNonExpired=true;
       private boolean accountNonLocked=true;
       private boolean credentialsNonExpired=true;
       private boolean enabled=true;
       //these fields are used by spring security to track expired,
       //locked,password expired,or active

       private String twoFactorSecret;
       //stores the secret key for 2FA
       //(e.g., for Google Authenticator).
       private boolean isTwoFactorEnabled=false;
       private String signUpMethod;
       //storing how user signedup like using google,github

       private boolean credentialsEnabled=true;

       private LocalDate credentialsExpiryDate;
       private LocalDate accountExpiryDate;

       //fetch=Eager
       //whenever you load a user, it also loads their role.
       @ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE})
       @JoinColumn(name="role_id",referencedColumnName = "role_id")
       @JsonBackReference
       //prevents recursion in toString().
       @ToString.Exclude
       private Role role;

       @CreationTimestamp
       @Column(updatable = false)
       private LocalDateTime createdDate;

       @UpdateTimestamp
       private LocalDateTime lastModifiedDate;
       //@CreationTimestamp → automatically
       //fills creation date when a user is first inserted.
       //@UpdateTimestamp → automatically updates
       //timestamp whenever user record changes.

      public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
      }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
          if (this == o) return true;
          if(!(o instanceof User)) return false;
          return userId != null && userId.equals(((User)o).getUserId());
    }
    //Used to compare User objects.


    public Long getUserId() {
          return userId;
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    //ensures consistent hashing
    // (important for sets and persistence).
}
