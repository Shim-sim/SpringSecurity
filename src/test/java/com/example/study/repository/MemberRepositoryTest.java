package com.example.study.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.study.entity.Member;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@DataJpaTest
@Transactional
@Import(MemberJpaRepository.class)
class MemberRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(MemberRepositoryTest.class);
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void test() {
        memberRepository.save(new Member("user1", 1));
        memberRepository.save(new Member("user2", 1));
        memberRepository.save(new Member("user3", 1));
        memberRepository.save(new Member("user4", 1));
        memberRepository.save(new Member("user5", 1));

        int age = 1;

        PageRequest useranme = PageRequest.of(0, 3, Direction.DESC, "userName");
        Slice<Member> members = memberRepository.findByAge(age, useranme);

        List<Member> content = members.getContent();
//        long totalElements = members.getTotalElements();

//        Assertions.assertEquals(5, totalElements);
    }
    @Test
    void bulkTest() {
        memberRepository.save(new Member("user1", 10));
        memberRepository.save(new Member("user2", 10));
        memberRepository.save(new Member("user3", 10));
        memberRepository.save(new Member("user4", 1));
        memberRepository.save(new Member("user5", 1));

        int resultCount = memberRepository.bulkAge(10);
        Assertions.assertEquals(3, resultCount);
    }


}