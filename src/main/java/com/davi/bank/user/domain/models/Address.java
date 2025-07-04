package com.davi.bank.user.domain.models;

import com.davi.bank.shared.auditing.EntityDateAuditing;
import com.davi.bank.user.domain.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.davi.bank.user.domain.models.Users;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address extends EntityDateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    @Enumerated(EnumType.STRING)
    private State estado;
}
