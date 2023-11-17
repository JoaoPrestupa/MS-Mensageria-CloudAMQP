package br.com.msuser.models;

import br.com.msuser.dtos.CursosEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private CursosEnum curso;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CursosEnum getCurso() {
        return curso;
    }

    public void setCurso(CursosEnum curso) {
        this.curso = curso;
    }
}
