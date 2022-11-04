package com.github.guangyuu.common.utils;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Random;

/**
 * @Author Guangyu
 * @Date 2022/11/3
 * @Description 存储空间工具类
 **/
public class SpaceUtils {
    /**
     * 用户家目录
     */
    private static final String PROPERTIES_USER_HOME = "user.home";
    /**
     * 系统文件分割符号
     */
    private static final String FILE_SEPARATOR = File.separator;
    /**
     * 根路径
     */
    public static final String ROOT_PATH = "typora-upload";
    /**
     * 一级目录
     */
    private static final String[] FIRST_LEVEL_DIRECTORY = new String[]{"00", "11", "22", "33", "44", "55", "66", "77", "88", "99"};
    /**
     * 二级目录
     */
    private static final String[] SECOND_LEVEL_DIRECTORY = new String[]{"aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj", "kk", "ll", "mm", "nn", "oo", "pp", "qq", "rr", "ss", "tt", "uu", "vv", "ww", "xx", "yy", "zz"};

    /**
     * 生成目录
     */
    public static void generator() {
        final String userHome = System.getProperty(PROPERTIES_USER_HOME);
        final LocalDate now = LocalDate.now(ZoneId.systemDefault());
        final String year = String.valueOf(now.getYear());
        final String month = String.valueOf(now.getMonthValue());
        final String day = String.valueOf(now.getDayOfMonth());
        initDirectory(year, month, day, userHome);
    }

    /**
     * 返回随机存储目录
     *
     * @return 存储目录
     */
    public static String randomPath() {
        String userHome = System.getProperty(PROPERTIES_USER_HOME);

        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        String year = String.valueOf(now.getYear());
        String month = String.valueOf(now.getMonthValue());
        if (now.getMonthValue() <= 9) {
            month = 0 + month;
        }
        String day = String.valueOf(now.getDayOfMonth());
        if (now.getDayOfMonth() <= 9) {
            day = 0 + day;
        }
        Random random = new Random();
        int var1 = random.nextInt(FIRST_LEVEL_DIRECTORY.length);
        int var2 = random.nextInt(SECOND_LEVEL_DIRECTORY.length);

        return userHome + FILE_SEPARATOR +
                ROOT_PATH + FILE_SEPARATOR +
                year + FILE_SEPARATOR +
                month + FILE_SEPARATOR +
                day + FILE_SEPARATOR +
                FIRST_LEVEL_DIRECTORY[var1] + FILE_SEPARATOR +
                SECOND_LEVEL_DIRECTORY[var2];

    }

    /**
     * 存储空间初始化
     *
     * @param year     年
     * @param month    月
     * @param day      日
     * @param userHome 用户家目录
     */
    private static void initDirectory(String year, String month, String day, String userHome) {
        for (String level1 : FIRST_LEVEL_DIRECTORY) {
            String firstLevelPath = userHome + FILE_SEPARATOR + ROOT_PATH + FILE_SEPARATOR + year + FILE_SEPARATOR + month + FILE_SEPARATOR + day + FILE_SEPARATOR + level1;
            for (String level2 : SECOND_LEVEL_DIRECTORY) {
                String fullPath = firstLevelPath + FILE_SEPARATOR + level2;
                File file = new File(fullPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
        }
    }
}
