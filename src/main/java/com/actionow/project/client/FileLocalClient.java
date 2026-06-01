package com.actionow.project.client;

import com.actionow.common.core.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件服务 本地客户端
 * 调用 actionow-asset 的文件服务内部接口
 *
 * @author Actionow
 */
public interface FileLocalClient {

    /**
     * 从 URL 转存文件到本地 OSS
     *
     * @param request 转存请求
     * @return 上传结果
     */
    Result<FileUploadResult> transferFromUrl(FileTransferRequest request);

    /**
     * 删除文件
     *
     * @param fileKey 文件 Key
     * @return 操作结果
     */
    Result<Void> deleteFile(String fileKey);

    /**
     * 批量删除文件
     *
     * @param fileKeys 文件 Key 列表
     * @return 操作结果
     */
    Result<Void> deleteFiles(List<String> fileKeys);

    /**
     * 获取文件访问 URL
     *
     * @param fileKey 文件 Key
     * @return 文件 URL
     */
    Result<String> getFileUrl(String fileKey);

    /**
     * 检查文件是否存在
     *
     * @param fileKey 文件 Key
     * @return 是否存在
     */
    Result<Boolean> exists(String fileKey);

    /**
     * 生成缩略图
     *
     * @param fileKey  文件 Key
     * @param mimeType MIME 类型
     * @return 缩略图 URL
     */
    Result<String> generateThumbnail(String fileKey,
                                     String mimeType);

    /**
     * 获取预签名下载 URL
     *
     * @param fileKey       文件 Key
     * @param expireSeconds 有效期（秒）
     * @return 下载 URL
     */
    Result<String> getDownloadUrl(String fileKey,
                                  int expireSeconds);
}
