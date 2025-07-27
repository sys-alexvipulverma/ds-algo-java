package io.github.sysalexvipulverma.exception;

import lombok.NonNull;

public class InvalidArrayException extends RuntimeException {
    public InvalidArrayException() {
    }

    public InvalidArrayException(@NonNull String msg) {
        super(msg);
    }
}
