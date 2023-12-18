package com.nthokar.spring2023.main.application.services.feign;

import com.nthokar.spring2023.main.application.DTO.archive.GameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("archive")
public interface ArchiveService {
    @RequestMapping(method = RequestMethod.POST, value = "/api/archive/save")
    void saveGame(GameDTO game);
}
