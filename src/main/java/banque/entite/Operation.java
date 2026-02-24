package banque.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;
    private double montant;
    private String motif;

    // Relation Plusieurs Opérations -> 1 Compte
    @ManyToOne
    @JoinColumn(name = "ID_COMPTE")
    private Compte compte;

    public Operation() {
    }

    public Operation(LocalDateTime date, double montant, String motif, Compte compte) {
        this.date = date;
        this.montant = montant;
        this.motif = motif;
        this.compte = compte;
    }

    // --- Getters et Setters ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public Compte getCompte() { return compte; }
    public void setCompte(Compte compte) { this.compte = compte; }
}