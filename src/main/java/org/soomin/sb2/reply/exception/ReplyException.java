package org.soomin.sb2.reply.exception;

public class ReplyException extends RuntimeException{
    private int code;

    public ReplyException(int code) {
        super("REPLY-" + code);
    }

    public int getCode() {
        return this.code;
    }
}
