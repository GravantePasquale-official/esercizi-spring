package it.catalogo.catalogo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.catalogo.catalogo.domains.Prodotto;
import it.catalogo.catalogo.domains.ProdottoForm;
import it.catalogo.catalogo.repositories.ProdottoRepository;

@Service
public class ProdottoService {
    @Autowired
    private ProdottoRepository prodottoRepo;

    public List<Prodotto> getAll(){
        return prodottoRepo.findAll();
    }

    public Prodotto addProdotto(ProdottoForm p){
        return prodottoRepo.save(map(p));
    }

    public void deleteAll(){
        prodottoRepo.deleteAll();
    }

    public Prodotto getByCodice(int codice){
        return prodottoRepo.getReferenceById(codice);
    }

    public void deleteByCodice(int codice){
        prodottoRepo.deleteById(codice);
    }

    public Prodotto updateProdotto(int codice, Prodotto p){
        Prodotto p1 = prodottoRepo.getReferenceById(codice);
        p1.setNome(p.getNome());
        p1.setCategoria(p.getCategoria());
        p1.setAnno_produzione(p.getAnno_produzione());
        p1.setQuantita(p.getQuantita());
        
        prodottoRepo.flush();
        return p1;
    }

    private Prodotto map(ProdottoForm pf){
        Prodotto prodotto = new Prodotto();
        
        prodotto.setNome(pf.getNome());
        prodotto.setCategoria(pf.getCategoria());
        prodotto.setAnno_produzione(pf.getAnno_produzione());
        prodotto.setQuantita(pf.getQuantita());

        return prodotto;
    }
}
