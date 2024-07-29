package articles.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import articles.entity.Articles;
import articles.entity.Evenements;


@Repository
public interface EvenementsRepository extends JpaRepository <Evenements, Integer>{

Page<Evenements> findByTitreContainingIgnoreCase(String keyword, Pageable pageable);

}
