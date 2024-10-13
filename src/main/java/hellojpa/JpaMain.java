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

            String sql="select t from Team t  ";
            //하이버네이트 6이상부터는 자동중복 제거가 기본
            List<Team> resultList = em.createQuery(sql, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
            //이러면 DB에서 일단 가져온 후 메모리가 페이징 처리 쿼리에 페이징이 안됨
            //일대다 -> 다대일로 변환 처리
            for (Team team1 : resultList) {
                System.out.println("team1 = " + team1.getName()+","+ team1.getMembers().size());
                List<Member> members = team1.getMembers();
                for (Member member1 : members) {
                    System.out.println(" -  member1 = " + member1);
                }
            }

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
