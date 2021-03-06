package life.majiang.community.mapper;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {


    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag}) ")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> findAll(@Param("offset") Integer offset,@Param("size") Integer size);

    //查找里边的数据数量，用于页面显示
    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserID(@Param("userId") Long userId);
    @Select("select * from question where creator = #{userId} limit #{offset},#{size}")
    List<Question> findAllByUserID(@Param("userId") Long userId,@Param("offset") Integer offset,@Param("size") Integer size);
    @Select("select * from question where id=#{id}")
    Question getById(@Param("id")long id);
    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id = #{id}")
    void update(Question question);

    //更新浏览数
    @Update("update question set view_count=view_count+1 where id = #{id}")
    void updateView(@Param("id")Long id);
    @Update("update question set COMMENT_COUNT=COMMENT_COUNT+#{commentCount} where id = #{id}")
    int incCommentCount(Question question);
}
