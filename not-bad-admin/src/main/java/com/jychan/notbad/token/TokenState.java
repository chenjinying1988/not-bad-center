package com.jychan.notbad.token;

/**
 * Created by chenjinying on 2017/5/22.
 * mail: 415683089@qq.com
 */
public enum TokenState {

    /** 过期 */
    EXPIRED("EXPIRED"),
    /** 无效(token不合法) */
    INVALID("INVALID"),
    /** 有效的 */
    VALID("VALID");

    private String  state;

    private TokenState(String state) {
        this.state = state;
    }

    public static TokenState getTokenState(String tokenStr) {
        TokenState[] states = TokenState.values();
        TokenState ts = null;
        for (TokenState state : states) {
            if (state.toString().equals(tokenStr)) {
                ts = state;
                break;
            }
        }
        return ts;
    }

    @Override
    public String toString() {
        return state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
