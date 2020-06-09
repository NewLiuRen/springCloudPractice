package com.demo.service.impl;

import com.demo.bo.SpuBO;
import com.demo.common.pojo.PageResult;
import com.demo.mapper.*;
import com.demo.pojo.*;
import com.demo.service.IGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements IGoodsService {
    private final ISpuMapper spuMapper;
    private final ISpuDetailMapper spuDetailMapper;
    private final ISkuMapper skuMapper;
    private final IStockMapper stockMapper;
    private final IBrandMapper brandMapper;
    private final ICategoryMapper categoryMapper;

    @Autowired
    public GoodsServiceImpl(ISpuMapper spuMapper, ISpuDetailMapper spuDetailMapper, ISkuMapper skuMapper, IStockMapper stockMapper, IBrandMapper brandMapper, ICategoryMapper categoryMapper) {
        this.spuMapper = spuMapper;
        this.spuDetailMapper = spuDetailMapper;
        this.skuMapper = skuMapper;
        this.stockMapper = stockMapper;
        this.brandMapper = brandMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public PageResult<SpuBO> findByPage(String key, Boolean saleable, Integer page, Integer rows) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(key))
            criteria.andLike("title", "%" + key + "%");
        if (saleable != null) criteria.andEqualTo("saleable", saleable);

        PageHelper.startPage(page, rows);

        List<Spu> spuList = this.spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);

        List<SpuBO> spuBOList = new ArrayList<>();
        spuList.forEach(spu -> {
            SpuBO spuBO = new SpuBO();
            BeanUtils.copyProperties(spu, spuBO);

            Brand brand = this.brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBO.setBname(brand.getName());

            List<Category> categories = this.categoryMapper.findNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBO.setCname(StringUtils.join(categories.stream().map(Category::getName).collect(Collectors.toList()), '/'));

            spuBOList.add(spuBO);
        });

        return new PageResult<>(pageInfo.getTotal(), spuBOList);
    }

    @Override
    public void addGoods(SpuBO spuBO) {
        Date date = new Date();
        // 新增spu
        // 设置默认字段
        spuBO.setId(null);
        spuBO.setSaleable(true);
        spuBO.setValid(true);
        spuBO.setCreateTime(date);
        spuBO.setLastUpdateTime(date);
        this.spuMapper.insertSelective(spuBO);

        SpuDetail spuDetail = spuBO.getSpuDetail();
        spuDetail.setSpuId(spuBO.getId());
        this.spuDetailMapper.insertSelective(spuDetail);

        this.saveSkuAndStock(spuBO);
    }

    @Override
    public SpuDetail findSpuBySpuId(Long spuId) {
        return this.spuDetailMapper.selectByPrimaryKey(spuId);
    }

    @Override
    public List<Sku> findSkusBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skus = this.skuMapper.select(sku);

        skus.forEach(s -> {
            Stock stock = this.stockMapper.selectByPrimaryKey(s.getId());
            s.setStock(stock.getStock());
        });
        return skus;
    }

    @Override
    public void updateGoods(SpuBO spuBO) {
        Date date = new Date();

        // 查询之前的sku列表
        List<Sku> skus = this.findSkusBySpuId(spuBO.getId());
        // 由于sku可能会存在变动，所以更新时需要先删除之前的sku列表
        if (!CollectionUtils.isEmpty(skus)) {
            List<Long> ids = skus.stream().map(Sku::getId).collect(Collectors.toList());

            this.stockMapper.deleteSotckByids(ids);

            Sku sku = new Sku();
            sku.setSpuId(spuBO.getId());
            this.skuMapper.delete(sku);
        }

        // 更新spu
        spuBO.setLastUpdateTime(date);
        // 创建时间不允许修改
        spuBO.setCreateTime(null);
        // 更新方法不允许删除和下架，删除和下架有单独的接口提供
        spuBO.setValid(null);
        spuBO.setSaleable(null);
        this.spuMapper.updateByPrimaryKeySelective(spuBO);

        // 更新spuDetail
        this.spuDetailMapper.updateByPrimaryKeySelective(spuBO.getSpuDetail());

        // 新建sku及库存
        this.saveSkuAndStock(spuBO);
    }

    private void saveSkuAndStock(SpuBO spuBO) {
        Date date = new Date();
        spuBO.getSkus().forEach(sku -> {
            sku.setCreateTime(date);
            sku.setSpuId(spuBO.getId());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insertSelective(sku);

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insertSelective(stock);
        });
    }

    @Override
    public void updateGoodsValid(Long spuId) {
        Spu spu = new Spu();
        spu.setId(spuId);
        spu.setValid(false);
        spu.setLastUpdateTime(new Date());
        this.spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    public void updateGoodsSaleable(Long spuId) {
        Spu spu = new Spu();
        spu.setId(spuId);
        spu.setSaleable(false);
        spu.setLastUpdateTime(new Date());
        this.spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    public void updateGoodsUnsaleable(Long spuId) {
        Spu spu = new Spu();
        spu.setId(spuId);
        spu.setSaleable(true);
        spu.setLastUpdateTime(new Date());
        this.spuMapper.updateByPrimaryKeySelective(spu);
    }
}
