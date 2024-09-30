package com.altas.iot.sys.utils.page;

import com.altas.iot.sys.utils.Convert;
import com.altas.iot.sys.utils.ServletUtils;
import com.altas.iot.sys.utils.StringUtils;
import com.altas.iot.sys.utils.exception.GlobalException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * 表格数据处理
 *
 * @author hc
 */
public class TableSupport {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 分页参数合理化
     */
    public static final String REASONABLE = "reasonable";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(Convert.toInt(ServletUtils.getParameter(PAGE_NUM), 1));
        pageDomain.setPageSize(Convert.toInt(ServletUtils.getParameter(PAGE_SIZE), 10));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
        pageDomain.setReasonable(ServletUtils.getParameterToBool(REASONABLE));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }

    /**
     * 封装分页对象
     */
    public static <T> Page<T> getPage() {
        Page<T> page = new Page<>();
        Integer pageNum = ServletUtils.getParameterToInt(PAGE_NUM);
        Integer pageSize = ServletUtils.getParameterToInt(PAGE_SIZE);
        if (StringUtils.isNull(pageNum) || StringUtils.isNull(pageSize)) {
            throw new GlobalException("分页参数不能为空！");
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return page;
    }
}
