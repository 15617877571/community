package life.majiang.community.service;


import life.majiang.community.dto.PageDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    public PageDTO list(Integer page, Integer size) {


        Integer offset = size*(page-1);

        List<Question> questionList = questionMapper.findAll(offset,size);


        PageDTO pageDTO = new PageDTO();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
           User user =  userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        //获取数据数量用于页面计算显示
        Integer count = questionMapper.count();

        //page里边
        pageDTO.setPagination(count,page,size);

        pageDTO.setQuestions(questionDTOList);
        return  pageDTO;
    }
}
