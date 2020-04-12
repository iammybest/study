package com.iammybest.common.http.result;

public interface WebConstants {

    /**
     * 状态 标志
     */
    String CODE = "code";
    /**
     * 状态 标志
     */
    String DATA = "data";
    /**
     * 消息描述 标志
     */
    String DESC = "desc";
    /**
     * 成功
     */
    String MSG_OK = "OK";

    /**
     * 缺少参数
     */
    String MSG_PARAM_MISS = "missing parameter";

    /**
     * Token失效
     */
    String MSG_TOKEN_ERROR = "Token failure";

    /**
     * 参数错误
     */
    String MSG_PARAM_ERROR = "error parameter";

    String TOKEN_VERIFY_TYPE_ERROR = "token verify type error";
    /**
     * 服务器错误
     */
    String MSG_SERVER_ERROR = "server error";

    String MSG_FILE_MAX_LIMIT = "The file exceeds the size limit";
    ////////////// 系统标描述 结束//////////////////


    ////////////// 文件上传大小限制 开始//////////////////
    //头像限制(10M)
    int FACE_MAX_BYTE = 10 * 1024 * 1024;

    int GROUP_MEMBERS_PAGE_SIZE = 2;

    ////////////// 文件上传大小限制 结束//////////////////

    /**
     * 请求成功
     */
    int SUCCESS = 200;
}
