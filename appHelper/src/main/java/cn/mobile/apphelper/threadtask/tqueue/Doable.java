package cn.mobile.apphelper.threadtask.tqueue;

import android.os.Bundle;

/**
 * Doable
 * TODO:可做的事件
 * @author:zhangqi
 * @date:2017年4月26日
 */
public interface Doable {

     <T> T doing(TQueue queue, Bundle args);
}
