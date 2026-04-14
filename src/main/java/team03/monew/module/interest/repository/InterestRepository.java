package team03.monew.module.interest.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import team03.monew.module.interest.entity.Interest;

public interface InterestRepository extends JpaRepository<Interest, UUID>,
    CustomInterestRepository {

}
