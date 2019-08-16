package com.online.college.core.artical.service;

import com.online.college.common.page.TailPage;
import com.online.college.core.artical.domain.Artical;

import java.util.List;


public interface ArticalService {

    /**
     * 获取所有文章
     */
    public List<Artical> queryAll();

    /**
     *根据id获取
     **/
    public Artical getById(Integer id);

    /**
     *创建文章
     **/
    public void create(Artical artical);

    /**
     *更新文章
     */
    public  void update(Artical artical);

    /**
     *分页获取
     **/
    public TailPage<Artical> queryPage(Artical artical , TailPage<Artical> page);

    /**
     * 逻辑删除
     * @param artical
     */
    public void deleteLogic(Artical artical);

    /**
     * 批量删除
     * @param ids
     */
    public void deleteMulti(String[] ids);
}

