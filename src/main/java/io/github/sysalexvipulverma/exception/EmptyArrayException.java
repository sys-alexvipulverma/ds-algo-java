package io.github.sysalexvipulverma.exception;

import lombok.NonNull;

public class EmptyArrayException extends InvalidArrayException {
    public EmptyArrayException() {
    }

    public EmptyArrayException(@NonNull String msg) {
        super(msg);
    }
}
