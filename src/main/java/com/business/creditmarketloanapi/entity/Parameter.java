package com.business.creditmarketloanapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents the different parameters of the business logic and the application
 *
 * @author Julian Alvarado
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parameters")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String strValue;
}
