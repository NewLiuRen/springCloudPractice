package com.demo.service.impl;

import com.demo.mapper.ISpecGroupMapper;
import com.demo.mapper.ISpecParamMapper;
import com.demo.pojo.SpecGroup;
import com.demo.pojo.SpecParam;
import com.demo.service.ISpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecServiceImpl implements ISpecService {
    @Autowired
    private ISpecGroupMapper specGroupMapper;
    @Autowired
    private ISpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> findSpecGroupsByCid(Long cid) {
        SpecGroup group = new SpecGroup();
        group.setCid(cid);
        return this.specGroupMapper.select(group);
    }

    @Override
    public void addSpecGroup(SpecGroup group) {
        this.specGroupMapper.insertSelective(group);
    }

    @Override
    public void updateSpecGroup(SpecGroup group) {
        this.specGroupMapper.updateByPrimaryKeySelective(group);
    }

    @Override
    public void deleteSpecGroup(Long id) {
        this.specGroupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SpecParam> findSpecParamsByGid(Long gid) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        return this.specParamMapper.select(param);
    }

    @Override
    public List<SpecParam> findSpecParamsByCid(Long cid) {
        SpecParam param = new SpecParam();
        param.setCid(cid);
        return this.specParamMapper.select(param);
    }

    @Override
    public void addSpecParam(SpecParam param) {
        this.specParamMapper.insertSelective(param);
    }

    @Override
    public void updateSpecParam(SpecParam param) {
        this.specParamMapper.updateByPrimaryKeySelective(param);
    }

    @Override
    public void deleteSpecParam(Long id) {
        this.deleteSpecParam(id);
    }
}
