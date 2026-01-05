package com.example.study.repository;

import com.example.study.entity.Member;
import java.math.BigInteger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, BigInteger> {

    Slice<Member> findByAge(int age, Pageable pageable);

}
