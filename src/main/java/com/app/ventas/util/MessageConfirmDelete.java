package com.app.ventas.util;

public record MessageConfirmDelete(
        String message,
        String backMessage,
        String method,
        String url
) {
}
