package io.github.sysalexvipulverma.exception;

import lombok.NonNull;

public class InvalidRangeException extends IllegalArgumentException {

    public InvalidRangeException() {
    }

    public InvalidRangeException(@NonNull String msg) {
        super(msg);
    }
}
