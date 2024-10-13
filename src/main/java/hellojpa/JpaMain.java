package hellojpa;

import hellojpa.jpql.*;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try
        {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);
            Team team2= new Team();
            team2.setName("teamB");
            em.persist(team2);
            Member member = new Member();

            member.setAge(10);
            member.setUsername("u1");
            member.changeTeam(team);
            member.setMemberType(MemberType.ADMIN);
            em.persist(member);

            Member member2 = new Member();
            member2.setAge(60);
            member2.setUsername("u2");
            member2.changeTeam(team);
            member2.setMemberType(MemberType.ADMIN);
            em.persist(member2);

            Member member3 = new Member();
            member3.setAge(60);
            member3.setUsername("u3");
            member3.changeTeam(team2);
            member3.setMemberType(MemberType.USER);
            em.persist(member3);
            em.flush();
            em.clear();

            String sql="select m from Member m join fetch m.team";
            List<Member> resultList = em.createQuery(sql, Member.class).getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1 +","+ member1.getTeam().getName());
            }
            //u1 sql
            //u2 persistance 1 cache
            //u3 sql
            //u1~u100 회원 100명 팀이?? 100개 면 -> N+1 ==> 즉시나 지연이나 같은현상 그래서 fetch
            tx.commit();
        }
        catch (Exception e)
        {
            tx.rollback();
        }
        finally {
            em.close();
        }
        //code
        emf.close();
    }
}
