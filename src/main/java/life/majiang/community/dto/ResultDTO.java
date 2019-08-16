package life.majiang.community.dto;


import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

@Data
public class ResultDTO {
    private Integer code;
    private String message;
    public static ResultDTO error(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return  resultDTO;
    }

    public static ResultDTO error(CustomizeErrorCode errorCode) {
        return error(errorCode.getCode(),errorCode.getMessage());
    }
    public  static  ResultDTO okOF(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return  resultDTO;
    }

    public static ResultDTO error(CustomizeException e) {
        return error(e.getCode(),e.getMessage());
    }
}
