package de.thro.inf.pvs;

import de.thro.inf.pvs.bsp.Address;
import de.thro.inf.pvs.bsp.Member;
import de.thro.inf.pvs.bsp.MemberRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class PvsApplication {

    @Bean
    InitializingBean seedDatabase(MemberRepo repo, ProjectRepo projectRepo) {
        return () -> {

            Project p = new Project();
            p.setName("VV-Project");
            projectRepo.save(p);
            Member m1 = new Member("Harry", "Hirsch", LocalDate.parse("1971-10-01"),
                    new Address("Schuetteweg 7", "26384", "Wilhelmshaven"));
            m1.setProject(p);
            repo.save(m1);
            Member m2 = new Member("Fred", "Feuerstein", LocalDate.parse("1968-04-09"),
                    new Address("Osteroederstr. 5", "36567", "Clausthal"));
            m2.setProject(p);
            repo.save(m2);
            Member m3 = new Member("Horst", "Hurtig", LocalDate.parse("1922-08-25"),
                    new Address("Implerstr. 14", "83923", "Muenchen"));
            m3.setProject(p);
            repo.save(m3);
            Member m4 = new Member("Harry", "Hurtig", LocalDate.parse("1922-08-25"),
                    new Address("Implerstr. 14", "83923", "Muenchen"));
            m4.setProject(p);
            repo.save(m4);

        };
    }

    @Bean
    CommandLineRunner beispiel(MemberRepo repo, ProjectRepo projectRepo) {
        return args -> {
/*            repo.findAll().forEach(System.out::println);
            Optional<Member> aMember = repo.findById(1L);
            System.out.println(aMember.get().toString());
            System.out.println(repo.count());
            repo.findByFirstName("Harry").forEach(System.out::println);
            repo.findByFirstNameAndLastName("Fred", "Feuerstein").forEach(System.out::println);
            repo.findWithLike("H").forEach(System.out::println);
            int number = repo.changeAllLastNames("Beneken", "Hirsch");
            repo.findWithLike("B").forEach(System.out::println);
            System.out.println(number);*/
            //projectRepo.findById(1L).get().getMembers().forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PvsApplication.class, args);
    }

}
