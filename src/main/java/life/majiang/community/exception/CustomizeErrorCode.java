package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你要找的问题不存在了，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题？"),
    NO_LOGIN(2002,"未登录不能评论,请登录后重试"),
    SYS_ERROR(2004,"服务器冒烟了,稍后再试试"),
    TYPE_PARAM_WRONG(2005,"服务器冒烟了"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在，要不换个试试？");
    private String message;
    private Integer code;
    @Override
    public String getMessage() {
        return message;
    }
    @Override
     public Integer getCode(){
        return  code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

}
