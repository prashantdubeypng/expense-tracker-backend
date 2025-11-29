package org.expense.expensetrackerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    public Long getId() {
        return userid;
    }

    public void setId(Long id) {
        this.userid = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userid;
    private String Username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    /**
     * we are there @ManyToMany(fetch = FetchType. EAGER)
     * because wwe are making the many-to-many relationship in
     * the user's table and users_roles table
     * we know when ever we are making the many-to-many relationship,
     * the new table gets created*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    when the table gets connected, we save the data into hashSet
    /**
     * userid == 1234566
     * user_roleid = 77644(admin)
     * in the hashset we will map it like
     * 1234566 --> 77644
     */

    private Set<UserRole> roles = new HashSet<>();


}
