package banque.entite;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
// JOINED : une table COMPTE, une table LIVRETA, une table ASSURANCEVIE
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;
    private double solde;

    // Comptes <-> Clients
    @ManyToMany(mappedBy = "comptes")
    private Set<Client> clients;

    // Constructeurs
    public Compte() {
        this.clients = new HashSet<>();
    }

    public Compte(String numero, double solde) {
        this.numero = numero;
        this.solde = solde;
        this.clients = new HashSet<>();
    }

    @OneToMany(mappedBy = "compte")
    private Set<Operation> operations = new HashSet<>();

    public Set<Operation> getOperations() { return operations; }
    public void setOperations(Set<Operation> operations) { this.operations = operations; }

    // --- Getters et Setters ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    public Set<Client> getClients() { return clients; }
    public void setClients(Set<Client> clients) { this.clients = clients; }
}