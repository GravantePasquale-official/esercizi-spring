package it.esercizio.esercizio.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.esercizio.esercizio.domains.UtenteForm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/utente")
public class UtenteController {
    private static UtenteForm utente;
    
    @GetMapping(params = "type")
    public ModelAndView handleUtente(@RequestParam("type") String type) {
        if(type.equals("new")){
            return new ModelAndView("registrazione").addObject(new UtenteForm());
        }
        else if(type.equals("login")){
            return new ModelAndView("accesso").addObject(new UtenteForm());
        }
        return null;
    }

    @PostMapping()
    public ModelAndView handleUtente(@ModelAttribute UtenteForm utenteForm) {
        //prg del bazar
        utente=utenteForm;
        return new ModelAndView("redirect:/utente/" + utenteForm.getNickname()); 
    }
    
    @GetMapping("/{nickname}")
    public ModelAndView handleNickname(@PathVariable String nickname) {
        return new ModelAndView("utente").addObject(utente);
    }
    
    
    
}
