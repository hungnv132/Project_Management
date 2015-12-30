package com.hungnv132.core.domain;

import java.util.List;

public class DataContainer<T> {

	private int total;

	private List<T> data;

	public DataContainer(int total, List<T> data) {
		this.total = total;
		this.data = data;
	}

	public DataContainer() {
	};

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
