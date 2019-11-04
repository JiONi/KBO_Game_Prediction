package com.koreabaseball.koreabaseball.repository;

import com.koreabaseball.koreabaseball.entity.GameTeam;
import com.koreabaseball.koreabaseball.entity.GameTeamHitter;
import com.koreabaseball.koreabaseball.entity.GameTeamPitcher;
import com.koreabaseball.koreabaseball.entity.GameTeamRecord;
import com.koreabaseball.koreabaseball.entity.id.GameId;
import com.koreabaseball.koreabaseball.entity.id.GameRecordId;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GameRepository {
    @PersistenceContext
    EntityManager em;

    public void saveGameTeam(GameTeam gameTeam) {
        if(findOne(gameTeam.getId()) == null){
            em.persist(gameTeam);
        }else{
            em.merge(gameTeam);
        }
    }

    public void saveGameTeamRecordList(List<GameTeamRecord> gameTeamRecordList){
        for(int i=0; i<gameTeamRecordList.size(); i++){
            em.persist(gameTeamRecordList.get(i));
        }
    }

    public void saveGameTeamHitterList(List<GameTeamHitter> gameTeamHitterList){
        for(int i=0; i<gameTeamHitterList.size(); i++){
            em.persist(gameTeamHitterList.get(i));
        }
    }

    public void saveGameTeamPitcherList(List<GameTeamPitcher> gameTeamPitcherList){
        for(int i=0; i<gameTeamPitcherList.size(); i++){
            em.persist(gameTeamPitcherList.get(i));
        }
    }

    public GameTeam findOne(GameId id){
        return em.find(GameTeam.class, id);
    }

    public int getGameGroupTurn(String date){
        int gameGroupTurn = em.createQuery("SELECT gt.gameGroupTurn FROM GameTeam gt WHERE gt.id.gameDate = :date ORDER BY gt.gameGroupTurn DESC")
            .setParameter("date", date)
            .getFirstResult();
        return gameGroupTurn;
    }



    public int getHitterRecordTurn(GameId gameId, int playerId){
        List<GameTeamRecord> hitterRecord = em.createQuery("SELECT gtr FROM GameTeamRecord gtr " +
                                                "WHERE gtr.id.gameId.gameDate = :gameDate AND gtr.id.gameId.teamId = :teamId AND gtr.id.hitterId = :playerId " +
                                                "ORDER BY gtr.id.hitterRecordTurn DESC")
                                .setParameter("gameDate", gameId.getGameDate())
                                .setParameter("teamId", gameId.getTeamId())
                                .setParameter("playerId", playerId)
                                .getResultList();
        if(hitterRecord.size() == 0){
            return 0;
        }else{
            return hitterRecord.get(0).getId().getHitterRecordTurn();
        }
    }

    public int selectHitterRecordTurn(GameRecordId gameRecordIdId){
        GameTeamRecord gameTeamRecord = em.find(GameTeamRecord.class, gameRecordIdId);
        return 0;
    }
}


