package de.thro.inf.pvs.bsp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MemberRepo extends CrudRepository<Member, Long> {
    List<Member> findByFirstName(String firstName);
    List<Member> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select m from Member m where m.lastName like %?1%")
    List<Member> findWithLike(String lastName);

    @Transactional
    @Modifying
    @Query("update Member m set m.lastName = ?1 where m.lastName = ?2")
    int changeAllLastNames(String lastName, String old);
}
