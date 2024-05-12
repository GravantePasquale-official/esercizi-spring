package it.catalogo.catalogo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.catalogo.catalogo.domains.Prodotto;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>{
    
}
