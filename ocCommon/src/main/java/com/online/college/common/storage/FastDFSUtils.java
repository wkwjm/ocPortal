package com.online.college.common.storage;

import java.io.Serializable;

public class FastDFSUtils implements Serializable {


    /**
     * 根据fastDFS返回的path得到文件的组名
     * @param path fastDFS返回的path
     * @return
     */
     public static String getGroupFormFilePath(String path){
         return path.split("/")[0];
     }

     /**
     * 根据fastDFS返回的path得到文件名
     * @param path fastDFS返回的path
     * @return
     */
     public static String getFileNameFormFilePath(String path) {
         return path.substring(path.indexOf("/")+1);
     }

    public static String getFileUrl(String fileInfo) {
        return FastDFSConfig.baseUrl + fileInfo;
    }

}
