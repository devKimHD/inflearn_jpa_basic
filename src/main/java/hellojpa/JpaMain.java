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
//            member.setUsername("teamA");
            member.setAge(10);
            member.setUsername("관리자");
//            member.changeTeam(team);
            member.setMemberType(MemberType.ADMIN);

            Member member2 = new Member();
//            member.setUsername("teamA");
            member2.setAge(60);
            member2.setUsername("관리자2");
//            member2.changeTeam(team);
            member2.setMemberType(MemberType.ADMIN);
            em.persist(member);
            em.persist(member2);
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

//            String query = "select " +
//                    "   case when  m.age <=10 then '학생'" +
//                    "       when m.age>=60 then '경로'" +
//                    "       else '일반요금'" +
//                    "   end" +
//                    " from Member m";
//            String query = "select" +
//                    " coalesce(m.username,'이름없음') from Member m";
//            String query = "select " +
//                    " nullif(m.username, '관리자') as username from Member m";

//            List<Object[]> resultList = em.createQuery(query).setParameter("userType",MemberType.ADMIN).getResultList();
//            System.out.println("resultList.size() = " + resultList.size());
//            List<String> resultList = em.createQuery(query, String.class).getResultList();
//            for (String s : resultList) {
//                System.out.println("s = " + s);
//            }
//            String query = "select concat('a','c') from Member m";
//            String query = "select locate('de','abcdef') from Member m";
            String query = "select size(t.members) from Team t";
            List<Integer> resultList = em.createQuery(query, Integer.class).getResultList();
            for (int s : resultList) {
                System.out.println("s = " + s);
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
