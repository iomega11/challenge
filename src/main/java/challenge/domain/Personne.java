package challenge.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class Personne {

    @PositiveOrZero
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String nom;

    @Getter
    @Setter
    private String prenom;

    @NotNull
    @Getter
    @Setter
    private LocalDate dateNaissance;
}
