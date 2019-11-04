package com.koreabaseball.koreabaseball.controller;

import com.koreabaseball.koreabaseball.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    @Autowired
    TeamService teamService;

    @RequestMapping(value="/teamInfoSave", method = RequestMethod.POST)
    public void teamInfoSave(){
        teamService.saveAllTeam();
    }
}
