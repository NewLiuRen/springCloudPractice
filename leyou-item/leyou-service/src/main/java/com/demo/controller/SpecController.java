package com.demo.controller;

import com.demo.pojo.SpecGroup;
import com.demo.pojo.SpecParam;
import com.demo.service.ISpecService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("spec")
public class SpecController {
    private final ISpecService specService;

    public SpecController(ISpecService specService) {
        this.specService = specService;
    }

    // 根据参数查询参数
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> findParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching
    ) {
        List<SpecParam> params = this.specService.findParams(gid, cid, generic, searching);
        if (CollectionUtils.isEmpty(params)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(params);
    }

    // 根据分类id查询分组
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> findSpecGroupByCid(@PathVariable("cid") Long cid) {
        if (cid == null || cid < 0) return ResponseEntity.badRequest().build();
        List<SpecGroup> groups = this.specService.findSpecGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("groups/params/{cid}")
    public ResponseEntity<List<SpecGroup>> findSpecWithParamByCid(@PathVariable("cid") Long cid) {
        if (cid == null || cid < 0) return ResponseEntity.badRequest().build();
        List<SpecGroup> groups = this.specService.findSpecGroupsWithParamByCid(cid);
        if (CollectionUtils.isEmpty(groups)) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(groups);
    }

    @PostMapping("group")
    public ResponseEntity<Void> addSpecGroup(@RequestBody SpecGroup group) {
        if (group == null) return ResponseEntity.badRequest().build();
        this.specService.addSpecGroup(group);
        return ResponseEntity.ok().build();
    }

    @PutMapping("group")
    public ResponseEntity<Void> updateSpecGroup(@RequestBody SpecGroup group) {
        if (group == null) return ResponseEntity.badRequest().build();
        this.specService.updateSpecGroup(group);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("group/{id}")
    public ResponseEntity<Void> deleteSpecGroupById(@PathVariable Long id) {
        if (id == null || id < 0) return ResponseEntity.badRequest().build();
        this.specService.deleteSpecGroup(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("params/{gid}")
    public ResponseEntity<List<SpecParam>> findSpecParamByGid(@PathVariable("gid") Long gid) {
        if (gid == null || gid < 0) return ResponseEntity.badRequest().build();
        List<SpecParam> params = this.specService.findSpecParamsByGid(gid);
        if (CollectionUtils.isEmpty(params)) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(params);
    }

    @GetMapping("params/cid/{cid}")
    public ResponseEntity<List<SpecParam>> findSpecParamsByCid(@PathVariable("cid") Long cid) {
        if (cid == null || cid < 0) return ResponseEntity.badRequest().build();
        List<SpecParam> params = this.specService.findSpecParamsByCid(cid);
        return ResponseEntity.ok(params);
    }

    @PostMapping("param")
    public ResponseEntity<Void> addSpecParam(@RequestBody SpecParam param) {
        if (param == null) return ResponseEntity.badRequest().build();
        this.specService.addSpecParam(param);
        return ResponseEntity.ok().build();
    }

    @PutMapping("param")
    public ResponseEntity<Void> updateSpecParam(@RequestBody SpecParam param) {
        if (param == null || StringUtils.isEmpty(param.getId())) return ResponseEntity.badRequest().build();
        this.specService.updateSpecParam(param);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteSpecParam(@PathVariable Long id) {
        if (id == null || id < 0) return ResponseEntity.badRequest().build();
        this.specService.deleteSpecParam(id);
        return ResponseEntity.ok().build();
    }
}
