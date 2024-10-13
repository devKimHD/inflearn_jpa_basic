package hellojpa;

import hellojpa.jpql.*;
import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try
        {
//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUsername("mem"+i);
//                member.setAge(i);
//

//                em.persist(member);
//            }
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);
            em.flush();
            em.clear();
//            List<Object[]> resultList = em.createQuery("select (m.username, m.age) from Member m").getResultList();

//            Member findMem = resultList.get(0);
//            findMem.setAge(11);
//            Object o = resultList.get(0);
//            Object[] result = (Object[]) o;
//            System.out.println("username[0] = " + result[0]);
//            System.out.println("age[0] = " + result[1]);

//            List<MemberDTO> resultList = em.createQuery("select new hellojpa.jpql.MemberDTO(m.username, m.age) m from Member m", MemberDTO.class)
//                    .getResultList();
//            MemberDTO memberDTO = resultList.get(0);
//            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
//            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

//            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

            String query = "select m from Member m left join Team t on m.username=t.name";
            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
            System.out.println("resultList.size() = " + resultList.size());
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
