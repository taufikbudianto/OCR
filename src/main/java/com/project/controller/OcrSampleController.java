package com.project.controller;

import com.project.entity.RequestController;
import com.project.util.AbstractManagedBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : User
 * @mailto : taufikagusbudiyanto@gmail.com
 * @created : 12/10/2022, Wednesday
 **/
@RestController
@RequestMapping("/api")
public class OcrSampleController extends AbstractManagedBean {

    @RequestMapping(value = "/testocr",method = RequestMethod.POST)
    public Map<String,Object> requestOcr(HttpServletRequest request,
                                         @RequestBody RequestController requestController){
        String ipRequest = request.getRemoteAddr();
        Map<String,Object> response = new HashMap<>();
        response.put("data",runningOcr(requestController));
        return response;
    }

}
