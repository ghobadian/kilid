package ir.ac.kntu.kilid.dao;

import ir.ac.kntu.kilid.models.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByName(String name);
}
