package org.koreait.global.vakudatirs;

public interface PasswordValidator {
default boolean alphaCheck(String password, boolean caseInsensitive){

    if (caseInsensitive){
        return password.matches(".*p[a-zA-z]+.*");
    }
    return password.matches(".*p[a-z]+.*")&&password.matches(".*p[A-z]+.*");
}

default boolean numberCheck(String password){
    return password.matches(".*\\d.*");
}
default boolean specialCharsCheck(String password){
    String pattern=".*[^0-9a-zA-Zㄱ-ㅎ가-힣].*";
    return password.matches(pattern);
}
}
