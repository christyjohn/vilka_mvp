package com.vilka.app.subscription.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "service_id", nullable = false)
    private Long offeringId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public enum Status {
        ACTIVE,
        CANCELLED
    }

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }
}
