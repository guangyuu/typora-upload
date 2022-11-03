package com.github.guangyuu.job;

import com.github.guangyuu.common.utils.SpaceUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 生成存储目录定时任务
 **/
@Component
public class StoreDirectoryGeneratorJob {

    /**
     * 每天00:00执行一次,生成目录
     * 目录格式:年/月/日/00-99/aa-zz
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    private void generator() {
        SpaceUtils.generator();
    }


}
