package com.algomau.bank.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Id
    private UUID id;

    @Column(name = "balance")
    private double balance;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "account", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Transaction> transactions;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bank_id")
    @JsonBackReference(value = "bank-account")
    private Bank bank;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-account")
    private User user;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public enum Type {
        CHEQUEING ,
        SAVING,
        CREDIT;
    }
}
