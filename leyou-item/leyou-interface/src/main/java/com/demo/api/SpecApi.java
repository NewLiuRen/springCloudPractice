package com.demo.api;

import com.demo.pojo.SpecGroup;
import com.demo.pojo.SpecParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("spec")
public interface SpecApi {
    // 根据参数查询参数
    @GetMapping("params")
    List<SpecParam> findParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching
    );

     // 根据分类id查询分组
    @GetMapping("groups/{cid}")
    List<SpecGroup> findSpecGroupByCid(@PathVariable("cid") Long cid);

    @PostMapping("group")
    Void addSpecGroup(@RequestBody SpecGroup group);

    @PutMapping("group")
    Void updateSpecGroup(@RequestBody SpecGroup group);

    @DeleteMapping("group/{id}")
    Void deleteSpecGroupById(@PathVariable Long id);

    @GetMapping("params/{gid}")
    List<SpecParam> findSpecParamByGid(@PathVariable("gid") Long gid);

    @GetMapping("params/cid/{cid}")
    List<SpecParam> findSpecParamsByCid(@PathVariable("cid") Long cid);

    @PostMapping("param")
    Void addSpecParam(@RequestBody SpecParam param);

    @PutMapping("param")
    Void updateSpecParam(@RequestBody SpecParam param);

    @DeleteMapping("param/{id}")
    Void deleteSpecParam(@PathVariable Long id);

    @GetMapping("groups/params/{cid}")
    public List<SpecGroup> findSpecWithParamByCid(@PathVariable("cid") Long cid);
}
