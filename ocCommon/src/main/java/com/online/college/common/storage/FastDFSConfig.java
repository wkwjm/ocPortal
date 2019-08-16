package com.online.college.common.storage;

/**
 * 类名: FastDFSConfig
 * 描述: TODO
 * 创建人:胡玉国
 * 时间:2018/12/2
 */
public class FastDFSConfig {
  public static int soTimeOut=30000;
  public static int connectTimeOut=20000;
  public static int  MaxTotal=200;
  public static int MaxTotalPerKey=200;
  public static int MaxIdlePerKey=200;
  public static String type="fastdfs";
  public static String group="group1";
  public static String trackerPath="192.168.202.129:22122";
  public static String baseUrl="http://192.168.202.129:8888/";

}
