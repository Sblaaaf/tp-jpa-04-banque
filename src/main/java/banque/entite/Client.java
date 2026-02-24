package banque.entite;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    // 1. L'adresse est "embarquée" (ses colonnes seront directement dans la table CLIENT)
    @Embedded
    private Adresse adresse;

    // 2. Relation Plusieurs Clients -> 1 Banque
    @ManyToOne
    @JoinColumn(name = "ID_BANQUE")
    private Banque banque;

    // 3. Relation Plusieurs Clients <-> Plusieurs Comptes
    @ManyToMany
    @JoinTable(name = "CLIENT_COMPTE",
            joinColumns = @JoinColumn(name = "ID_CLIENT", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ID_COMPTE", referencedColumnName = "id")
    )
    private Set<Compte> comptes;

    public Client() {
        this.comptes = new HashSet<>();
    }

    public Client(String nom, String prenom, LocalDate dateNaissance, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.comptes = new HashSet<>();
    }

    // --- Getters et Setters ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }

    public Banque getBanque() { return banque; }
    public void setBanque(Banque banque) { this.banque = banque; }

    public Set<Compte> getComptes() { return comptes; }
    public void setComptes(Set<Compte> comptes) { this.comptes = comptes; }
}