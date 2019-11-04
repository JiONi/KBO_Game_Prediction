package com.koreabaseball.koreabaseball.entity.id;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Embeddable
public class GamePlayerId implements Serializable {
    @Column(name = "GAME_DATE")
    private String gameDate;
    @Column(name = "TEAM_ID")
    private String teamId;

    public String getGameDate(){
        return this.gameDate;
    }
    public void setGameDate(String gameDate){
        this.gameDate = gameDate;
    }
    public String getTeamId(){
        return this.teamId;
    }
    public void setTeamId(String teamId){
        this.teamId = teamId;
    }

    public GamePlayerId(String gameDate, String teamId){
        this.gameDate = gameDate;
        this.teamId = teamId;
    }
}
