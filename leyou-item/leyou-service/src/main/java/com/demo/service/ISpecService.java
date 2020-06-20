package com.demo.service;

import com.demo.pojo.SpecGroup;
import com.demo.pojo.SpecParam;

import java.util.List;

public interface ISpecService {
    List<SpecGroup> findSpecGroupsByCid(Long cid);

    void addSpecGroup(SpecGroup group);

    void updateSpecGroup(SpecGroup group);

    void deleteSpecGroup(Long id);

    List<SpecParam> findSpecParamsByGid(Long gid);

    List<SpecParam> findSpecParamsByCid(Long cid);

    void addSpecParam(SpecParam param);

    void updateSpecParam(SpecParam param);

    void deleteSpecParam(Long id);

    List<SpecParam> findParams(Long gid, Long cid, Boolean generic, Boolean searching);

    List<SpecGroup> findSpecGroupsWithParamByCid(Long cid);
}
