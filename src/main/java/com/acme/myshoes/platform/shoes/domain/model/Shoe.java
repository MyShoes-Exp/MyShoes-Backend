package com.acme.myshoes.platform.shoes.domain.model;

import com.acme.myshoes.platform.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity //para la creación de su mapeo en base de datos
@Table(name = "shoe") // especificar el nombre para seguir la convencion correcta
public class Shoe extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max=20)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Size(max=20)

    private Float size;

    //Relationships
    @ManyToOne
    @JoinColumn(name="shoe_id", nullable = false)
    @JsonIgnore
    private Collection collection;

}
