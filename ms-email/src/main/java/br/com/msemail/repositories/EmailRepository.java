package br.com.msemail.repositories;

import br.com.msemail.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
