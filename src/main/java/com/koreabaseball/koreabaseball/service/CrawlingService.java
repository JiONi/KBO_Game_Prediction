package com.koreabaseball.koreabaseball.service;

import com.koreabaseball.koreabaseball.entity.Player;
import com.koreabaseball.koreabaseball.entity.Team;
import com.koreabaseball.koreabaseball.repository.PlayerRepository;
import com.koreabaseball.koreabaseball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;

@Service
public class CrawlingService {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    public void crawlingPlayerInfo(int playerId){

        try{
            URL url = new URL("https://www.koreabaseball.com/Record/Player/PitcherDetail/Basic.aspx?playerId="+playerId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

            String line = "";
            Player player = new Player();
            player.setId(playerId);

            while((line = reader.readLine()) != null){
                if(line.contains("cphContents_cphContents_cphContents_playerProfile_lblName") == true){
                    String data = line.split(">")[4].split("<")[0];
                    player.setName(data);
                    System.out.println("선수 이름:" + data);
                }
                if(line.contains("cphContents_cphContents_cphContents_playerProfile_lblBackNo") == true){
                    String data = line.split(">")[4].split("<")[0];
                    if(!"".equals(data)){
                        player.setBackNo(Integer.parseInt(data));
                    }
                    System.out.println("선수 백넘버:" + data );
                }
                if(line.contains("cphContents_cphContents_cphContents_playerProfile_lblPosition") == true){
                    String data = line.split(">")[4].split("<")[0];
                    if(data.contains("투수")){
                        player.setSectCode("P");
                    }else{
                        player.setSectCode("H");
                    }
                    System.out.println("선수 포지션:"+data);
                }
                if(line.contains("cphContents_cphContents_cphContents_playerProfile_lblHeightWeight") == true){
                    String data = line.split(">")[4].split("<")[0];
                    int height = Integer.parseInt(data.split("cm")[0]);
                    int weight = Integer.parseInt(data.split("/")[1].split("kg")[0]);
                    System.out.println("선수 키:"+height);
                    System.out.println("선수 몸무게:"+weight);

                    player.setHeight(height);
                    player.setWeight(weight);
                }
                if(line.contains("cphContents_cphContents_cphContents_playerProfile_lblBirthday") == true){
                    String data = line.split(">")[4].split("<")[0];
                    player.setBirthDay(data);
                }
                if(line.contains("regular/2019/emblem_") == true){
                    String data = line.split("\"")[1].split("\"")[0].split("_")[1];
                    Team team = teamRepository.findOne(data);
                    System.out.println("team : "+team.getName());
                    player.setTeam(team);
                }
                if(line.contains("regular/2018/emblem_") == true){
                    String data = line.split("\"")[1].split("\"")[0].split("_")[1];
                    Team team = teamRepository.findOne(data);
                    System.out.println("team : "+team.getName());
                    player.setTeam(team);
                }
                if(line.contains("cphContents_cphContents_cphContents_playerProfile_lblPosition") == true){
                    String data = line.split(">")[4].split("<")[0];
                    String position = data.split("\\(")[0];
                    String pitchPosition = data.split("\\(")[1].split("\\)")[0].substring(0,2);
                    String hitPosition = data.split("\\(")[1].split("\\)")[0].substring(2,4);
                    System.out.println("포지션 : "+position);
                    System.out.println("히트포지션 : "+hitPosition);
                    System.out.println("피칭포지션 : "+pitchPosition);

                    if(position.equals("투수")){
                        player.setPosition("PITCHER");
                    }else if(position.equals("포수")){
                        player.setPosition("CATCHER");
                    }else if(position.equals("내야수")){
                        player.setPosition("IN_FIELDER");
                    }else if(position.equals("외야수")){
                        player.setPosition("OUT_FIELDER");
                    }

                    if(pitchPosition.equals("좌투")){
                        player.setPitchPosition("LEFT_OVER");
                    }else if(pitchPosition.equals("우투")){
                        player.setPitchPosition("RIGHT_OVER");
                    }else if(pitchPosition.equals("좌언")){
                        player.setPitchPosition("LEFT_UNDER");
                    }else if(pitchPosition.equals("우언")){
                        player.setPitchPosition("RIGHT_UNDER");
                    }

                    if(hitPosition.equals("우타")){
                        player.setHitPosition("RIGHT");
                    }else if(hitPosition.equals("좌타")){
                        player.setHitPosition("LEFT");
                    }else if(hitPosition.equals("양타")){
                        player.setHitPosition("SWITCH");
                    }
                }
            }

            playerRepository.save(player);
        }catch (Exception e){

        }
    }

    public void playerFirstStringYnUpdate(){

    }
}
