package com.wychmod.util;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: wychmod
 * @date: 2024-10-26
 */

@Slf4j
public class FileUtil {

    /**
     * 生成文件名
     * @param fileName 原始文件名，用于生成新文件名的一部分
     * @return 返回生成的唯一文件名
     */
    public static String getFileName(String fileName) {
        return System.currentTimeMillis() + "-" + UUID.fastUUID().toString() + "-" + fileName;
    }
}
