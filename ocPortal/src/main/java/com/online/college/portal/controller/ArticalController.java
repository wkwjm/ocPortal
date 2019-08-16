package com.online.college.portal.controller;

import com.online.college.common.page.TailPage;
import com.online.college.core.artical.domain.Artical;
import com.online.college.core.artical.service.ArticalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/artical")
public class ArticalController {
    @Autowired
    private ArticalService articalService;

    /**
     * 文章首页
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("artical/list");
        List<Artical> articals = articalService.queryAll();
        mv.addObject("articals", articals);
        return mv;
    }

    /**
     * 根据id获取
     */
    @RequestMapping(value="/getByIdDetail")
    @ResponseBody
    public ModelAndView getByIdDetail(Integer id){
        ModelAndView mv = new ModelAndView("/artical/detail");
        Artical artical =articalService.getById(id);
        mv.addObject("artical",artical);
        return mv;

    }
}


