package com.example.common.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuyl
 * @Date: 2020/6/12 12:33
 * @Version: 1.0
 * @Description: 访问页面路由
 * 在resources文件夹或与其并列的文件夹下建立public文件夹,在public文件夹下的html文件可以通过浏览器中输入文件+后缀名的方式直接访问的.
 * 一、public文件夹，就相当于在eclipse的web项目中的web-inf文件夹外的文件，是不需要通过服务器内部进行访问的。
 * 二、templates文件夹，是放置模板文件的，因此需要视图解析器来解析它。所以必须通过服务器内部进行访问，也就是要走控制器--服务--视图解析器这个流程才行。
 * 三、static文件夹，既不能直接访问，也不能通过服务器访问到。因此，这个文件夹，可能是放一些css、图片这样的文件供服务器内部引用。
 */
@Controller
public class PageController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }
}
