package ir.ac.kntu.kilid.dao;

import ir.ac.kntu.kilid.models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("select h from History h where h.userId = :userId order by h.creation desc")
    List<History> findPreviousHistoriesOfUser(Long userId);

    @Query("select h.query from History h group by h.query order by count(h.query) DESC")
    List<String> findTopQueries();
}
