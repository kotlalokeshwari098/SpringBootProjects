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
import java.util.Optional;


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
//    private AppRole role;

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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public @NotBlank @Size(max = 20) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(max = 20) String username) {
        this.username = username;
    }

    public @NotBlank @Size(max = 50) @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Size(max = 50) @Email String email) {
        this.email = email;
    }

    public @Size(max = 120) String getPassword() {
        return password;
    }

    public void setPassword(@Size(max = 120) String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getTwoFactorSecret() {
        return twoFactorSecret;
    }

    public void setTwoFactorSecret(String twoFactorSecret) {
        this.twoFactorSecret = twoFactorSecret;
    }

    public boolean isTwoFactorEnabled() {
        return isTwoFactorEnabled;
    }

    public void setTwoFactorEnabled(boolean twoFactorEnabled) {
        isTwoFactorEnabled = twoFactorEnabled;
    }

    public String getSignUpMethod() {
        return signUpMethod;
    }

    public void setSignUpMethod(String signUpMethod) {
        this.signUpMethod = signUpMethod;
    }

    public boolean isCredentialsEnabled() {
        return credentialsEnabled;
    }

    public void setCredentialsEnabled(boolean credentialsEnabled) {
        this.credentialsEnabled = credentialsEnabled;
    }

    public LocalDate getCredentialsExpiryDate() {
        return credentialsExpiryDate;
    }

    public void setCredentialsExpiryDate(LocalDate credentialsExpiryDate) {
        this.credentialsExpiryDate = credentialsExpiryDate;
    }

    public LocalDate getAccountExpiryDate() {
        return accountExpiryDate;
    }

    public void setAccountExpiryDate(LocalDate accountExpiryDate) {
        this.accountExpiryDate = accountExpiryDate;
    }

    public String getRole() {
        return role.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

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
