package banque.entite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestBanque {
    public static void main(String[] args) {
        // Init persistence-unit "banque"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Banque
        Banque banque = new Banque("Ma Super Banque");
        em.persist(banque);

        // Adresse Embeddable
        Adresse adresse = new Adresse(12, "Rue de la Paix", 75000, "Paris");

        // Client
        Client client = new Client("Dupont", "Jean", LocalDate.of(1980, 5, 15), adresse);
        client.setBanque(banque); // Lien ManyToOne
        em.persist(client);

        // Comptes Héritage
        LivretA livretA = new LivretA("FR12345", 1500.0, 3.0);
        AssuranceVie assuranceVie = new AssuranceVie("FR98765", 5000.0, LocalDate.of(2030, 12, 31), 4.5);
        em.persist(livretA);
        em.persist(assuranceVie);

        // Client
        // @JoinTable
        client.getComptes().add(livretA);
        client.getComptes().add(assuranceVie);

        // Opération (Virement)
        Virement virement = new Virement(LocalDateTime.now(), 250.0, "Cadeau d'anniversaire", livretA, "Marie Dupont");
        em.persist(virement);

        // Validation
        em.getTransaction().commit();

        System.out.println("Les tables ont été générées et les données insérées avec succès !");

        em.close();
        emf.close();
    }
}