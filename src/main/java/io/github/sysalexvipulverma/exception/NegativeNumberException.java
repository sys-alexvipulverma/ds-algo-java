package io.github.sysalexvipulverma.exception;

import lombok.NonNull;

public class NegativeNumberException extends RuntimeException {
    public NegativeNumberException() {
    }

    ;

    public NegativeNumberException(@NonNull String msg) {
        super(msg);
    }
}
