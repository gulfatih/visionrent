package com.visionrent.repository;

import com.visionrent.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);

    /*
    User ve Role arasında ManyToMany ilişkide default olarak LAZY tanımlıydı
     @EntityGraph ile bunun EAGER olmasını sağladım.
     */
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    List<User> findAll();  // role bilgisi LAZY tanımlandığı için her rolde SQL kodu
    // oluşmaması için @EntityGraph kullanıldı ve fetch type EAGER oldu.

    @EntityGraph(attributePaths = "roles")
    Page<User> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findById(Long id);

}
