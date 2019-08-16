package life.majiang.community.mapper;



import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommentExtMapper {

    int incCommentCount(Comment comment);

}