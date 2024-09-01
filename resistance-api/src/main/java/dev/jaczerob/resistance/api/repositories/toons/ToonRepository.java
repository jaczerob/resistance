package dev.jaczerob.resistance.api.repositories.toons;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ToonRepository extends JpaRepository<ToonEntity, UUID> {
}
