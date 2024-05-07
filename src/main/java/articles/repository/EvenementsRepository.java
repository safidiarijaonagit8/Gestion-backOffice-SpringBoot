package articles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import articles.entity.Evenements;


@Repository
public interface EvenementsRepository extends JpaRepository <Evenements, Integer>{

}
