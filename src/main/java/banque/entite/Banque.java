package banque.entite;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Banque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    // Relation 1 Banque -> Plusieurs Clients (selon l'UML)
    @OneToMany(mappedBy = "banque")
    private Set<Client> clients;

    public Banque() {
    }

    public Banque(String nom) {
        this.nom = nom;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Set<Client> getClients() { return clients; }
    public void setClients(Set<Client> clients) { this.clients = clients; }
}