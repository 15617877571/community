package life.majiang.community.service;


import life.majiang.community.dto.PageDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
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
        Integer offset = size * (page - 1);//计算sql语句limit后边的值
        List<Question> questionList = questionMapper.findAll(offset, size);
        PageDTO pageDTO = new PageDTO();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {//用creator字段去查找user表中的数据
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//工具类很强大，数据快速放到另外一个链表里边
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        Integer count = questionMapper.count();//获取数据数量用于页面计算显示
        Integer totalPage;
        if (count % size == 0) { //计算出总共需要展示多少页
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }
        pageDTO.setPagination(totalPage, page);
        pageDTO.setQuestions(questionDTOList);
        pageDTO.setTotalpage(totalPage);
        return pageDTO;
    }

    public PageDTO list(Integer userId, Integer page, Integer size) {
        System.out.println("-============="+userId);
        Integer offset = size * (page - 1);//计算sql语句limit后边的值
        List<Question> questionList = questionMapper.findAllByUserID(userId,offset, size);
        PageDTO pageDTO = new PageDTO();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {//用creator字段去查找user表中的数据
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);//工具类很强大，数据快速放到另外一个链表里边
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        Integer count = questionMapper.countByUserID(userId);//获取数据数量用于页面计算显示
        Integer totalPage;
        if (count % size == 0) { //计算出总共需要展示多少页
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }
        pageDTO.setPagination(totalPage, page);
        pageDTO.setQuestions(questionDTOList);
        pageDTO.setTotalpage(totalPage);
        return pageDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }

        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question, questionDTO);
        return questionDTO;

    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else {
            //更新
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }

    public void incView(Integer id) {

        //更新数
        questionMapper.updateView(id);
    }
}
