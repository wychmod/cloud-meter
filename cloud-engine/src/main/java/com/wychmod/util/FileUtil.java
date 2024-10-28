package com.wychmod.util;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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

    /**
     * 从远程url获取文件内容
     * @param urlStr 远程url
     * @return 返回文件内容
     */
    public static String readRemoteUrl(String urlStr) {
        return HttpUtil.get(urlStr);
    }
}
