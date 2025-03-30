package com.algomau.bank.domain;

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
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {

    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    @Column(name = "email")
    private String email;

    @Column(name = "sin_number")
    private String sinNumber;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    @JsonManagedReference(value = "user-account")
    private List<Account> accounts;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public enum Type {
        EMPLOYEE,
        CUSTOMER,
    }
}
