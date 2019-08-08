package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages;


    //用来展示他有多少页可以展示

    /**
     *
     * @param count 数据库中总数据数
     * @param page
     * @param size 每一页要显示的数据数
     *             total：总数
     */
    public void setPagination(Integer count, Integer page, Integer size) {
        Integer totalPage;
        //计算出总共需要展示多少页
       if (count% size==0){
           totalPage = count/size;
       }else {
           totalPage = count/size+1;
       }
        pages=new ArrayList<>();
       pages.add(page);
       for (int i=1;i<=totalPage;i++){
           if (page-i>0){
               pages.add(page-i,0);
           }
           if (page+i<=totalPage){
               pages.add(page+i);
           }
       }



        //是否展示上一页
       if (page==1){
           showPrevious=false;
       }else {
           showPrevious=true;
       }
       //是否展示下一页
        if (page==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }
        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }

    }
}
