package com.algomau.bank.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.util.UUID;

@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Id
    private UUID id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "organization_name")
    private String organizationName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}
