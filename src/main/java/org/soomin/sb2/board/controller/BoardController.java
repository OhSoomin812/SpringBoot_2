package org.soomin.sb2.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.soomin.sb2.board.dto.BoardRegisterDTO;
import org.soomin.sb2.board.dto.PageRequestDTO;
import org.soomin.sb2.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/read/{bno}")
    public String read(@PathVariable("bno") Long bno, PageRequestDTO requestDTO, Model model) {
        log.info("Read Board with bno: " + bno);

        model.addAttribute("board", service.get(bno));
        model.addAttribute("requestDTO", requestDTO);

        return "board/read"; // read.html 페이지로 이동
    }

    @GetMapping("register")
    public void register() {

    }

    @PostMapping("register")
    public String register(@Valid BoardRegisterDTO dto, BindingResult bindingResult, RedirectAttributes rttr){
        log.info("----------------------------");
        log.info(dto);
        log.info(bindingResult);

        if(bindingResult.hasErrors()){
            log.info("has errors.......................");

            Map<String, String> errorMap = new HashMap<>();

            bindingResult.getFieldErrors().forEach(fieldError -> {
                log.info("==========================");
                log.info("Field: " + fieldError.getField());  // 에러가 발생한 필드명
                log.info("Rejected Value: " + fieldError.getRejectedValue()); // 사용자가 입력한 잘못된 값
                log.info("Error Message: " + fieldError.getDefaultMessage()); // 에러 메시지

                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                rttr.addFlashAttribute("errors", errorMap);
            });

            return "redirect:/board/register";
        }

        service.register(dto);
        return "redirect:/board/list";
    }

}
