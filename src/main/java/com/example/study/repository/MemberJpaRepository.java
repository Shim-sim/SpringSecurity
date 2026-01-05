package com.example.study.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public int bulkAge(int age) {
        return em.createQuery("update Member m set m.age = :age + 1 where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
    }
}
