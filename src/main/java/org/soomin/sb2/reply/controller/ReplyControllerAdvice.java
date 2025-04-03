package org.soomin.sb2.reply.controller;

import org.soomin.sb2.reply.entities.ReplyEntity;
import org.soomin.sb2.reply.exception.ReplyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ReplyControllerAdvice {

    @ExceptionHandler(ReplyException.class)
    public ResponseEntity<Map<String, String>> handle(ReplyException ex) {
        return ResponseEntity.status(ex.getCode())
                .body(Map.of("msg", ex.getMessage()));
    }
}
