package ir.ac.kntu.kilid.dao;

import ir.ac.kntu.kilid.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByUser_Username(String user_username);
}
