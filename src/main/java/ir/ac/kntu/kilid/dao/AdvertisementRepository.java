package ir.ac.kntu.kilid.dao;

import ir.ac.kntu.kilid.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, JpaSpecificationExecutor<Advertisement> {
    @Query("SELECT a FROM Advertisement a WHERE " +
            "a.title LIKE CONCAT('%',:query, '%')" +
            "Or a.description LIKE CONCAT('%', :query, '%')" +
            "Or a.address LIKE CONCAT('%', :query, '%')" +
            "Or a.district.name LIKE CONCAT('%', :query, '%')" +
            "Or a.district.city.name LIKE CONCAT('%', :query, '%')" +
            "Or a.district.city.province.name LIKE CONCAT('%', :query, '%')"
    )
    List<Advertisement> searchAdvertisements(String query);


    List<Advertisement> findAllByEstateAgencyId(Long estateAgency_id);
}
