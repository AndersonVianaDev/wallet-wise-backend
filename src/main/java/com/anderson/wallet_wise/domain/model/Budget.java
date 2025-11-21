package com.anderson.wallet_wise.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Category category;

    private BigDecimal limitAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean active;
}
