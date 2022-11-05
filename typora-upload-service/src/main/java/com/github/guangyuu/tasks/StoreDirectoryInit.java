package com.github.guangyuu.tasks;

import com.github.guangyuu.common.utils.SpaceUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Guangyu
 * @Date 2022/11/5
 * @Description 启动时执行的任务: 初始化存储目录
 **/
@Component
public class StoreDirectoryInit implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        SpaceUtils.generator();
    }
}
