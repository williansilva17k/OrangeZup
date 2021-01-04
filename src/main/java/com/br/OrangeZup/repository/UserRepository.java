package com.br.OrangeZup.repository;

import com.br.OrangeZup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);

    Long deleteByCpf(String cpf);
}
