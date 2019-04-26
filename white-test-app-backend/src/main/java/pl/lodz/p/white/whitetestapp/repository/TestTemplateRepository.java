package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;

import javax.persistence.PersistenceException;

public interface TestTemplateRepository extends JpaRepository<TestTemplate, Long> {

    @Transactional
    @Modifying
    @Query(value = "update test_template set position_id=?2 where test_template.id=?1", nativeQuery = true)
    int setPositionForTest(Long testId, String positionId) throws PersistenceException;
}
