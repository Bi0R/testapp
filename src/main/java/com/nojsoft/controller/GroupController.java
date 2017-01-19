package com.nojsoft.controller;

import com.nojsoft.dao.GroupDao;
import com.nojsoft.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alan on 1/14/17.
 */
@RestController
@Transactional
public class GroupController {

    @Autowired
    private GroupDao groupDao;

    @PostMapping("/group/save")
    public Group save(@RequestBody Group group) {
        return groupDao.saveOrUpdate(group);
    }

}