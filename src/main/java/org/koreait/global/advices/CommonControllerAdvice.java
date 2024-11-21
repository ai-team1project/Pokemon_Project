package org.koreait.global.advices;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.global.exceptions.CommonException;
import org.koreait.global.exceptions.script.AlertBackException;
import org.koreait.global.exceptions.script.AlertException;
import org.koreait.global.exceptions.script.AlertRedirectException;
import org.koreait.global.libs.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class CommonControllerAdvice {
    private final Utils utils;

    @ExceptionHandler(Exception.class)
    public ModelAndView errorHandler(Exception e, HttpServletRequest request){
        Map<String, Object> data=new HashMap<>();
        HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
        String tpl="error/error";

        String message=e.getMessage();
        data.put("method",request.getMethod());
        data.put("path", request.getContextPath()+request.getRequestURI());
        data.put("querystring",request.getQueryString());
        data.put("exception", e);

        if (e instanceof CommonException commonException){

            status=commonException.getStatus();
           message=commonException.isErrorCode()?utils.getMessage(message):message;
           StringBuffer sb=new StringBuffer(1000);
            if (e instanceof AlertException){
                tpl="common/_execute_script";
                sb.append(String.format("alert('%s');",message));
            }
            if (e instanceof AlertBackException backException){
                String target=backException.getTarget();
                sb.append(String.format("%s.history.back();",target));
            }
            if (e instanceof AlertRedirectException redirectException){
                String target=redirectException.getTarget();
                String url=redirectException.getUrl();
                sb.append(String.format("%s.location.replace('%s');",target,url));
            }
            if (sb.isEmpty()){
                data.put("script",toString());
            }
        }
        data.put("status", status.value());
        data.put("_status", status);
        data.put("message",message);
        ModelAndView mv=new ModelAndView();
        mv.setStatus(status);
        mv.addAllObjects(data);
        mv.setViewName(tpl);
        return mv;
    }
}
