package org.expense.expensetrackerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tokens")
public class RefereshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private String tokenId;
    private String token;
    private Instant expiryDate;
    @OneToOne
    @JoinColumn(name = "id" , referencedColumnName = "user_id")
    private User user;

}
