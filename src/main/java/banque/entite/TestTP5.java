package banque.entite;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestTP5 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Création d'une banque et d'une adresse
        Banque banque = new Banque("Banque 2");
        em.persist(banque);
        Adresse adresse = new Adresse(10, "Rue de machin", 44000, "Nantes");

        // client avec plusieurs comptes : 1 assurance vie, 1 livret A
        Client client1 = new Client("Lourgouilloux", "Renaud", LocalDate.of(1990, 7, 16), adresse);
        client1.setBanque(banque);

        AssuranceVie assuranceVie = new AssuranceVie("AV-111222", 5000.0, LocalDate.of(2035, 12, 31), 4.5);
        LivretA livretA = new LivretA("LA-333444", 2500.0, 3.0);

        em.persist(assuranceVie);
        em.persist(livretA);

        client1.getComptes().add(assuranceVie);
        client1.getComptes().add(livretA);
        em.persist(client1);

        // compte associé à 2 clients
        Client client2 = new Client("Jean", "Jean", LocalDate.of(1990, 3, 15), adresse);
        client2.setBanque(banque);

        Compte compteJoint = new Compte("CJ-999888", 1200.50);
        em.persist(compteJoint);

        // Associer le compte joint aux deux clients
        client1.getComptes().add(compteJoint);
        client2.getComptes().add(compteJoint);
        em.persist(client2); // client1 est déjà persisté

        // Opérations de type opérations sur un compte
        Operation operationClassique = new Operation(LocalDateTime.now(), -50.0, "Achat", compteJoint);
        em.persist(operationClassique);

        // Opérations de type virements sur un compte
        Virement virementLoyer = new Virement(LocalDateTime.now(), -800.0, "Loyer", compteJoint, "Proprio");
        em.persist(virementLoyer);

        // Validation DB
        em.getTransaction().commit();

        System.out.println("TestTP5 OK");

        em.close();
        emf.close();
    }
}