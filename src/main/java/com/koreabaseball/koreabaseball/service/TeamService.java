package com.koreabaseball.koreabaseball.service;

import com.koreabaseball.koreabaseball.entity.Team;
import com.koreabaseball.koreabaseball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public void saveAllTeam(){
        String[][] teamList = {{"OB","두산 베어스","두산"  ,"잠실야구장"},
                                {"HT","KIA 타이거즈","KIA","광주-기아 챔피언스 필드"},
                                {"LG","LG 트윈스","LG","잠실야구장"},
                                {"NC","NC 다이노스","NC","창원NC파크"},
                                {"SK","SK 와이번스","SK","인천SK행복드림구장"},
                                {"KT","KT WIZ","KT","수원 케이티 위즈 파크"},
                                {"LT","롯데 자이언츠","롯데","사직야구장"},
                                {"SS","삼성 라이온즈","삼성","대구 삼성 라이온즈 파크"},
                                {"WO","키움 히어로즈","키움","고척 스카이돔"},
                                {"HH","한화 이글스","한화","한화생명 이글스 파크"}};
        for(String[] team : teamList){
            Team insertTeam = new Team();
            insertTeam.setId(team[0]);
            insertTeam.setName(team[1]);
            insertTeam.setShortName(team[2]);
            insertTeam.setHomeGround(team[3]);

            teamRepository.save(insertTeam);
        }
    }

    public List<Team> getTeamList() {
        List<Team> teamList = teamRepository.findAll();
        return teamList;
    }
}
