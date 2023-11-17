package br.com.msuser.dtos;

import jakarta.validation.constraints.Email;

public record UsersRecordDto(String name, @Email String email, CursosEnum curso) {
}
