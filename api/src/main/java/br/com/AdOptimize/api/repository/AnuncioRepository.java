package br.com.AdOptimize.api.repository;

import br.com.AdOptimize.api.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio,Long> {
}
