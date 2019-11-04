package com.koreabaseball.koreabaseball.repository;

import com.koreabaseball.koreabaseball.entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TeamRepository{

    @PersistenceContext
    EntityManager em;

    public void save(Team team){
        if(findOne(team.getId()) == null){
            em.persist(team);
        }else{
            em.merge(team);
        }
    }

    public Team findOne(String teamId){
        return em.find(Team.class, teamId);
    }

    public List<Team> findAll(){
        List<Team> teamList = em.createQuery("SELECT t FROM Team t", Team.class).getResultList();
        return teamList;
    }


}
