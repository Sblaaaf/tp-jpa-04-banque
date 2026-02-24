package banque.entite;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Virement extends Operation {

    private String beneficiaire;

    public Virement() {
    }

    public Virement(LocalDateTime date, double montant, String motif, Compte compte, String beneficiaire) {
        super(date, montant, motif, compte);
        this.beneficiaire = beneficiaire;
    }

    public String getBeneficiaire() { return beneficiaire; }
    public void setBeneficiaire(String beneficiaire) { this.beneficiaire = beneficiaire; }
}