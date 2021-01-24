package top.catoy.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.catoy.service.DictService;

@Service
public class DictServiceImpl implements DictService {

    @Cacheable(cacheNames = "dict")
    @Override
    public String getDict() throws InterruptedException {
        Thread.sleep(5000);
        return "dict";
    }
}
