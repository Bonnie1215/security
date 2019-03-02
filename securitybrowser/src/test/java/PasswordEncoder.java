public interface PasswordEncoder {

    /**
     * 对密码进行加密，将密码存入数据库时开发人员调用。
     * @param rawPassword
     * @return
     */
    String encode(CharSequence rawPassword);

    /**
     * 加密以后的密码和用户传进来的密码是否匹配，springsecurity调用
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);

}
