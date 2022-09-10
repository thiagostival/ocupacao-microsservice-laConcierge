package com.laconcierge.ocupacao.establishment.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import com.laconcierge.ocupacao.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "establishment")
public class Establishment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull(message = "O id deve ser informado!")
    private UUID id;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull(message = "O estabelecimento deve ter uma capacidade máxima")
    @Min(0)
    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @NotNull(message = "O estabelecimento deve ter uma capacidade ocupada")
    @Min(0)
    @Column(name = "busy_capacity")
    private Integer busyCapacity;

    @Formula("(max_capacity - busy_capacity)")
    private Integer availableCapacity;

}
