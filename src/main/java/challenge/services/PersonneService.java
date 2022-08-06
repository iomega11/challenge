package challenge.services;

import challenge.domain.Personne;
import challenge.repositories.PersonneRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonneService {

    public static final int MAX_AUTHORIZED_AGE = 150;

    @Autowired
    @Getter
    @Setter
    private PersonneRepository personneRepository;

    public Personne savePersonne(Personne personne) {
        if (calculateAge(personne) > MAX_AUTHORIZED_AGE)
            throw new InvalidDataAccessApiUsageException("attention seule les personnes de moins de 150 ans peuvent être enregistrées");
        return personneRepository.savePersonne(personne);
    }

    private int calculateAge(Personne personne) {
        LocalDate birth = personne.getDateNaissance();
        LocalDate now = LocalDate.now();
        return now.minusYears(birth.getYear())
                .minusMonths(birth.getMonthValue())
                .minusDays(birth.getDayOfMonth())
                .getYear();

    }

    public List<Personne> findAllPersonnesAlphabeticalOrder() {
        return personneRepository.findAllPersonnesOrderByName();
    }

}
