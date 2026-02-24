package banque.entite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestBanque {
    public static void main(String[] args) {
        // Initialisation avec le nom de la persistence-unit "banque"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // 1. Création d'une Banque
        Banque banque = new Banque("Ma Super Banque");
        em.persist(banque);

        // 2. Création d'une Adresse (Embeddable, elle n'est pas persistée toute seule)
        Adresse adresse = new Adresse(12, "Rue de la Paix", 75000, "Paris");

        // 3. Création d'un Client
        Client client = new Client("Dupont", "Jean", LocalDate.of(1980, 5, 15), adresse);
        client.setBanque(banque); // Lien ManyToOne
        em.persist(client);

        // 4. Création des Comptes (Héritage)
        LivretA livretA = new LivretA("FR12345", 1500.0, 3.0);
        AssuranceVie assuranceVie = new AssuranceVie("FR98765", 5000.0, LocalDate.of(2030, 12, 31), 4.5);
        em.persist(livretA);
        em.persist(assuranceVie);

        // 5. Lier le Client aux Comptes (ManyToMany)
        // C'est la classe Client qui possède le @JoinTable, c'est donc elle qui met à jour la base
        client.getComptes().add(livretA);
        client.getComptes().add(assuranceVie);

        // 6. Création d'une Opération (Virement)
        Virement virement = new Virement(LocalDateTime.now(), 250.0, "Cadeau d'anniversaire", livretA, "Marie Dupont");
        em.persist(virement);

        // Validation en base de données
        em.getTransaction().commit();

        System.out.println(">>> Toutes les tables ont été générées et les données insérées avec succès !");

        // Fermeture des ressources
        em.close();
        emf.close();
    }
}