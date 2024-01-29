package ir.ac.kntu.kilid.dao;

import ir.ac.kntu.kilid.controllers.AdvertisementController;
import ir.ac.kntu.kilid.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
