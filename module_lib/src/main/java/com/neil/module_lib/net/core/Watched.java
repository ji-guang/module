package com.neil.module_lib.net.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的观察者：接口嵌套
 */
public class Watched<T> {
	private List<Observer<T>> list = new ArrayList<Observer<T>>();

	public void resigtObserver(Observer<T> observer) {
		if (observer != null)
			list.add(observer);
	}

	public void unresigtObserver(Observer<T> observer) {
		list.remove(observer);
	}

	public void notifyObservers(boolean success,T t ,String msg) {
		for (Observer<T> o : list)
			o.update(success,t, msg);
	}
}
