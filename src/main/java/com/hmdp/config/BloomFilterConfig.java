package com.hmdp.config;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class BloomFilterConfig {
    @Resource
    private ShopMapper shopMapper;

    @Bean
    public BloomFilter bloomFilter(){
        List<Shop> list = shopMapper.list();
        Integer count = list.size();
        BloomFilter<Long> bloomFilter = BloomFilter.create(Funnels.longFunnel(), count, 0.05);
        for (Shop shop : list) {
            bloomFilter.put(shop.getId());
        }
        return bloomFilter;

    }
}
