package it.catalogo.catalogo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import it.catalogo.catalogo.domains.Prodotto;
import it.catalogo.catalogo.domains.ProdottoForm;
import it.catalogo.catalogo.services.ProdottoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
    @Autowired
    private ProdottoService prodottoService;

    @GetMapping()
    public ModelAndView catalogoHandler() {

        return new ModelAndView("catalogo").addObject("prodotti", prodottoService.getAll());
    }

    @GetMapping(params="delete")
    public ModelAndView rimuoviProdotti(@RequestParam("delete") String param) {
        if(param==null || param.isEmpty() || param.isBlank()){
            prodottoService.deleteAll();
        }
        else{
            prodottoService.deleteByCodice(Integer.parseInt(param));
        }
        return new ModelAndView("redirect:/catalogo").addObject("prodotti", prodottoService.getAll());
    }

    @GetMapping("/nuovo")
    public ModelAndView aggiungiAlCatalogo() {
        return new ModelAndView("catalogo-aggiungi").addObject("prodotto", new ProdottoForm());
    }
    

    @PostMapping("/nuovo")
    public ModelAndView nuovoProdotto(@ModelAttribute ProdottoForm prodotto) {
        Prodotto prod = prodottoService.addProdotto(prodotto);
        return new ModelAndView("redirect:/catalogo/prodotto/"+prod.getCodice());
    }

    @GetMapping("/prodotto/{codice}")
    public ModelAndView mostraProdotto(@PathVariable String codice) {
        if(prodottoService.getByCodice(Integer.parseInt(codice))==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto inesistente");
        }
        return new ModelAndView("prodotto").addObject(prodottoService.getByCodice(Integer.parseInt(codice)));
    } 

    @GetMapping("/aggiorna")
    public ModelAndView modificaProdotto(@RequestParam("codice") String param) {
        if(param==null || param.isEmpty() || param.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Il codice non deve essere vuoto");
        }

        Prodotto prodotto = prodottoService.getByCodice(Integer.parseInt(param));

        if(prodotto==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prodotto inesistente");
 
        return new ModelAndView("catalogo-aggiorna").addObject("prodotto", prodotto);
    }

    @PostMapping("/aggiorna")
    public ModelAndView modificaProdotto(@ModelAttribute Prodotto prodotto) {
        prodottoService.updateProdotto(prodotto.getCodice(), prodotto);
        return new ModelAndView("redirect:/catalogo/prodotto/"+prodotto.getCodice());
    }

}
