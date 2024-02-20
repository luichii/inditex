package com.inditex.entity;

import com.inditex.CurrencyEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "currency")
@NoArgsConstructor
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currencyCode;
}
