package com.abc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/******
 * 统一的异常处理控制类
 */
@ControllerAdvice
public class GloabalExpectionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultExperctionHandler(HttpServletRequest request,Exception e)
        throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.addObject("exmsg","这里是异常信息");
        mav.setViewName("madserror");//错误页面

        return mav;
    }
}
