package com.neil.module_lib.net.core;

/**
 *  if（success）{
 *      T
 *  }else{
 *      msg
 *  }
 */
public interface Observer<T> {
	void update(boolean success,T t ,String msg);
}
