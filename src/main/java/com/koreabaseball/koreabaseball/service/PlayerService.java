package com.koreabaseball.koreabaseball.service;

import com.koreabaseball.koreabaseball.entity.HitterRecord;
import com.koreabaseball.koreabaseball.entity.PitcherRecord;
import com.koreabaseball.koreabaseball.entity.Player;
import com.koreabaseball.koreabaseball.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public List<Player> getEntryPlayer(String teamId, String playerSectCd){
        List<Player> playerList = playerRepository.getEntryPlayer(teamId,playerSectCd);
        return playerList;
    }

    public void updateFirstStringYn(List<Integer> backNoList, String teamId){
        playerRepository.firstStringToFalse(teamId);    //1군 여부 false로 일괄 업데이트
        playerRepository.firstStringToTrueByBackNo(backNoList, teamId);
    }

    public void insertPlayerRecord(String seasonCode){
        List<Player> playerList = playerRepository.findAll();

        for(Player player : playerList){
            if("H".equals(player.getSectCode())){
                HitterRecord hitterRecord = new HitterRecord(player.getId(), seasonCode, true);
                hitterRecord.setPlayer(player);
                playerRepository.save(hitterRecord);
                HitterRecord hitterRecord2 = new HitterRecord(player.getId(), seasonCode, false);
                hitterRecord2.setPlayer(player);
                playerRepository.save(hitterRecord2);
            }else if("P".equals(player.getSectCode())){
                PitcherRecord pitcherRecord = new PitcherRecord(player.getId(), seasonCode, true);
                pitcherRecord.setPlayer(player);
                playerRepository.save(pitcherRecord);
                PitcherRecord pitcherRecord2 = new PitcherRecord(player.getId(), seasonCode, false);
                pitcherRecord2.setPlayer(player);
                playerRepository.save(pitcherRecord2);
            }
        }
    }
}
