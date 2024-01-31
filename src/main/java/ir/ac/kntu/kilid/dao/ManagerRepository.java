package ir.ac.kntu.kilid.dao;

import ir.ac.kntu.kilid.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
