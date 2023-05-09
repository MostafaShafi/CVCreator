package com.aras.cvcreator.repository;

import com.aras.cvcreator.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsersRepository extends CrudRepository<Users, Integer> {
    @Query(value = "SELECT *, 0 as clazz_ FROM Users WHERE email like :email", nativeQuery = true)
    Users getByEmail(@Param("email") String email);

    @Query(value = "SELECT * 0 as clazz_ FROM USERS WHERE EMAIL =:email AND PASSWORD =:pass", nativeQuery = true)
    Users getByEmailAndPass(@Param("email") String email, @Param("pass") String pass);

    @Query(value = "SELECT *, 0 as clazz_ FROM USERS WHERE ID = (SELECT USERID FROM PERSON WHERE ID =:id)", nativeQuery = true)
    Users getByPersonId(@Param("id") Integer id);
}
