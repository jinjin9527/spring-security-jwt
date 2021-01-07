package com.sylinx.model;

public enum TokenStatus {
    TokenExpired(1, "トークン期限切れ"),
    TokenAuthenticated(2, "トークン認証した"),
    TokenInvalid(3, "トークン無効");

    private final String text;
    private final int id;
    private TokenStatus(final int id, final String text) {
        this.text = text;
        this.id = id;
    }
}
