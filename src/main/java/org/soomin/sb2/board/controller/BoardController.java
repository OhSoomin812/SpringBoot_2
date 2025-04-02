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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

        return "redirect:/board/list";
    }

}
