package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;

@Service
public class ZoneService {

    @Resource
    ZoneRepository zoneRepository;

    public void listZone(Model model){
        var l = zoneRepository.findAll();
        model.addAttribute("zoneList", l);
    }
}
