package br.com.AdOptimize.api.repository;

import br.com.AdOptimize.api.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha,Long> {
}
