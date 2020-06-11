package com.example.common.pagehelper;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.common.tool.ToolUtil;
import com.example.common.tool.ValidateUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 20:31
 * @Version: 1.0
 * @Description:分页工具
 */
public class PageFactory {
    private static final String ASC = "asc";
    private static final String PAGE_SIZE_PARAM_NAME = "pageSize";
    private static final String PAGE_NO_PARAM_NAME = "pageNo";
    private static final String SORT_PARAM_NAME = "sort";
    private static final String ORDER_BY_PARAM_NAME = "orderBy";
    private static final int defaultPageSize = 20;
    private static final int defaultPageNo = 1;

    public PageFactory() {
    }

    public static <T> Page<T> defaultPage() {
        int pageSize = 20;
        int pageNo = 1;
        HttpServletRequest request = HttpContext.getRequest();
        if (request == null) {
            return new Page(pageNo, pageSize);
        } else {
            String pageSizeString = getFieldValue(request, "pageSize");
            if (ValidateUtil.isNotEmpty(pageSizeString)) {
                pageSize = Integer.valueOf(pageSizeString);
            }

            String pageNoString = getFieldValue(request, "pageNo");
            if (ValidateUtil.isNotEmpty(pageNoString)) {
                pageNo = Integer.valueOf(pageNoString);
            }

            String sort = getFieldValue(request, "sort");
            String orderByField = getFieldValue(request, "orderBy");
            Page page;
            if (ToolUtil.isEmpty(sort)) {
                page = new Page(pageNo, pageSize);
                return page;
            } else {
                page = new Page(pageNo, pageSize, orderByField);
                if ("asc".equalsIgnoreCase(sort)) {
                    page.setAsc(true);
                } else {
                    page.setAsc(false);
                }

                return page;
            }
        }
    }

    private static String getFieldValue(HttpServletRequest request, String fieldName) {
        String parameter = request.getParameter(fieldName);
        return parameter == null ? null : parameter;
    }
}
