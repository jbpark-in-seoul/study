package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("memberNameA");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();

            List<Member> members = findTeam.getMembers();
            for(Member m : members) {
                System.out.println("JpaMain.main" + m);
            }

            System.out.println("findTeam = " + findTeam.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}

/**
 * JPQL
 *  - 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
 *  - SQL을 추상화해서 특정 데이터베이스 SQL에 의존 X
 *  - JPQL을 한 마디로 정의하면 객체 지향 SQL
 * EntityManagerFactory
 *  - 애플리케이션에 생성 후 전체에서 공유
 * EntityManager
 *  - 고객 요청당 1개씩 생성, 쓰레드간 공유 X
 * EntityTransaction
 *  - JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
 */