package com.online.college.opt.controller;


import com.online.college.common.page.TailPage;
import com.online.college.common.web.JsonView;
import com.online.college.core.artical.domain.Artical;
import com.online.college.core.artical.service.ArticalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文章管理
 */
@Controller
@RequestMapping("/artical")
public class ArticalController {

    @Autowired
    private ArticalService articalService;


    /**
     * 根据id获取
     */
    @RequestMapping(value="/getById")
    @ResponseBody
    public String getById(Integer id){
        Artical artical =articalService.getById(id);
        return JsonView.render(artical);

    }
    /**
     * 根据id获取
     */
    @RequestMapping(value="/getByIdDetail")
    @ResponseBody
    public ModelAndView getByIdDetail(Integer id){
        ModelAndView mv = new ModelAndView("cms/artical/detail");
        Artical artical =articalService.getById(id);
        mv.addObject("artical",artical);
        return mv;

    }
    /**
     * 分页展示文章
     */
    @RequestMapping("/queryPageList")
    public ModelAndView queryPageList(Artical artical, TailPage<Artical> page){
        ModelAndView mv = new ModelAndView("cms/artical/articalPagelist");
        mv.addObject("curNav", "artical");
        if(StringUtils.isNotEmpty(artical.getTitle())){
            artical.setTitle(artical.getTitle().trim());
        }else{
            artical.setTitle(null);
        }
        page = articalService.queryPage(artical,page);
        mv.addObject("page",page);
        mv.addObject("queryEntity",artical);
        return mv;
    }


    @RequestMapping(value = "/addArtical")
    public ModelAndView toMerge(Artical artical){
        ModelAndView mv = new ModelAndView("cms/artical/add");
        mv.addObject("curNav", "artical");

        if(artical.getId() != null){
            artical = articalService.getById(artical.getId());
        }
        mv.addObject("entity",artical);
        return mv;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(Artical artical) throws  Exception{
            articalService.create(artical);
        return new ModelAndView("redirect:/artical/queryPageList.html");
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Artical artical) throws  Exception{
        articalService.update(artical);
        return new JsonView().toString();
    }
    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(Artical artical){
        articalService.deleteLogic(artical);
        return new JsonView().toString();
    }

    @RequestMapping(value = "/deleteMulti")
    @ResponseBody
    public String deleteMulti(String ids){
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            articalService.deleteMulti(idArray);
        }
        return new JsonView().toString();
    }
}
