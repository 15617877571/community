package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你要找的问题不存在了，要不换个试试？");
    private String message;
    @Override
    public String getMessage() {
        return message;
    }
    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
