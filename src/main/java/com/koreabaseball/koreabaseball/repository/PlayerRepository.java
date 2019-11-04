package com.koreabaseball.koreabaseball.repository;

import com.koreabaseball.koreabaseball.entity.HitterRecord;
import com.koreabaseball.koreabaseball.entity.PitcherRecord;
import com.koreabaseball.koreabaseball.entity.Player;
import com.koreabaseball.koreabaseball.entity.id.RecordId;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PlayerRepository {
    @PersistenceContext
    EntityManager em;

    public List<Player> getEntryPlayer(String teamId, String playerSectCd){
        String query = "SELECT p FROM Player p WHERE p.firstStringYn = true AND p.team.id = :teamId AND p.sectCode = :sectCode";
        List<Player> playerList = em.createQuery(query, Player.class)
                .setParameter("teamId", teamId)
                .setParameter("sectCode", playerSectCd)
                .getResultList();

        return playerList;
    }

    public void save(Player p){
        if(findOne(p.getId()) == null){
            em.persist(p);
        }else{
            em.merge(p);
        }
    }

    public void save(HitterRecord hitterRecord){
        if(hitterRecordFindOne(hitterRecord.getId()) == null){
            em.persist(hitterRecord);
        }else{
            em.merge(hitterRecord);
        }
    }

    public void save(PitcherRecord pitcherRecord){
        if(pitcherRecordFindOne(pitcherRecord.getId()) == null){
            em.persist(pitcherRecord);
        }else{
            em.merge(pitcherRecord);
        }
    }

    public Player findOne(int playerId){
        return em.find(Player.class, playerId);
    }

    public List<Player> findAll() { return em.createQuery("SELECT p FROM Player p", Player.class).getResultList(); }

    public HitterRecord hitterRecordFindOne(RecordId recordId) { return em.find(HitterRecord.class, recordId); }

    public PitcherRecord pitcherRecordFindOne(RecordId recordId){ return em.find(PitcherRecord.class, recordId); }

    public void firstStringToFalse(String teamId){
        String query = "UPDATE Player p SET p.firstStringYn = false WHERE p.team.id = :teamId AND p.firstStringYn = true";
        em.createQuery(query)
                .setParameter("teamId", teamId)
                .executeUpdate();
    }

    public void firstStringToTrueByBackNo(List<Integer> backNoList, String teamId){
        String query = "UPDATE Player p SET p.firstStringYn = true WHERE p.team.id = :teamId AND p.backNo IN :backNos";
        em.createQuery(query)
                .setParameter("teamId", teamId)
                .setParameter("backNos", backNoList)
                .executeUpdate();
    }
}
