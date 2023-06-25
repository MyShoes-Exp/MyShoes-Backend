package com.acme.myshoes.platform.shoes.domain.model;

import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=150)
    @Column(unique = true)
    private String name;

    //Relationships

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="shoe_id", nullable = false)
    @JsonIgnore
    private Shoe shoe;

}
