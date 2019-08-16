package com.online.college.core.artical.service.impl;

import com.online.college.common.page.TailPage;
import com.online.college.core.artical.dao.ArticalDao;
import com.online.college.core.artical.domain.Artical;
import com.online.college.core.artical.service.ArticalService;

import com.online.college.core.consts.domain.ConstsCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticalServiceImpl implements ArticalService {

	@Autowired
	private ArticalDao entityDao;

	@Override
	public List<Artical> queryAll() {
		return entityDao.queryAll();
	}

	@Override
	public Artical getById(Integer id) {
		return entityDao.getById(id);
	}

	@Override
	public void create(Artical artical) {
		entityDao.create(artical);
	}

	@Override
	public void update(Artical artical) {
		entityDao.update(artical);
	}

	@Override
	public TailPage<Artical> queryPage(Artical artical, TailPage<Artical> page) {
		Integer itemsTotalCount = entityDao.getTotalItemsCount(artical);
		List<Artical> items = entityDao.queryPage(artical,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public void deleteLogic(Artical artical) {
		entityDao.deleteLogic(artical);
	}

	@Override
	public void deleteMulti(String[] ids) {
		entityDao.deleteMulti(ids);
	}
}

