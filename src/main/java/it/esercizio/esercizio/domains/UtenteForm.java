package it.esercizio.esercizio.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteForm {
    private String nickname;
    private String password;
    private String email;
}
