package cn.bdqn.util;

/**
 * Created by hasee on 2017/8/8.
 */
public class PageUtil {
    private Integer pageIndex=1;//当前页码
    private Integer pageSize=5;//页面大小
    private Integer pageCount=1;//总页数
    private Integer SqlCount=0;//数据库总记录数

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setSqlCount(Integer sqlCount) {
        if (sqlCount>0) {
            SqlCount = sqlCount;
            pageCount=sqlCount%pageSize==0?sqlCount/pageSize:(sqlCount/pageSize)+1;
        }
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Integer getSqlCount() {
        return SqlCount;
    }
}
