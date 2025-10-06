package com.javaspring.server.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="roles")
public class Role {
    public Role() {
    }

    public Role(Integer roleId, AppRole roleName, Set<User> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public AppRole getRoleName() {
        return roleName;
    }

    public void setRoleName(AppRole roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Integer roleId;


//    @ToString.Exclude
    @Enumerated(EnumType.STRING)
    @Column(length=20,name="role_name")
    private AppRole roleName;

    //The role field in User class owns the relationship.
    //Don’t load users automatically;
    //load them only if needed (good for performance).
    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST})
    //If you save a Role,
    //its users will also be saved automatically.
    @JsonBackReference
    //Prevents infinite recursion when converting to JSON:
    //Role → Users → Role → Users … would loop forever.
    //This stops Jackson
    //from serializing the users set when serializing Role.
    private Set<User> users=new HashSet<>();

    //AppRole is the enum here
    public Role(AppRole roleName){
        this.roleName=roleName;
    }


}
