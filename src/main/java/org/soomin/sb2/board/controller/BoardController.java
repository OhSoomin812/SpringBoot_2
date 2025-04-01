package org.soomin.sb2.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("list")
    public void list(@ModelAttribute("requestDTO")PageRequestDTO requestDTO, Model model) {
        log.info("Board List");

        model.addAttribute("data", service.list(requestDTO));
    }

}
