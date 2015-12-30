package com.hungnv132.core.support;

import java.util.List;
import java.util.Map;

import com.hungnv132.core.domain.User.ROLE;

public class DatatableForm {
	private int draw;
	private int start;
	private int length;
	
	private Map<SearchKeys, String> search;

	private List<Map<OrderKeys, String>> order;

	private List<Map<ColumnKeys, String>> columns;

	public enum SearchKeys {
		value, regex
	}

	public enum OrderKeys {
		column, dir
	}

	public enum ColumnKeys {
		data, name, searchable, orderable, searchRegex, searchValue
	}
	
	

	public List<Map<ColumnKeys, String>> getColumns() {
		return columns;
	}

	public void setColumns(List<Map<ColumnKeys, String>> columns) {
		this.columns = columns;
	}

	public Map<SearchKeys, String> getSearch() {
		return search;
	}

	public void setSearch(Map<SearchKeys, String> search) {
		this.search = search;
	}

	public List<Map<OrderKeys, String>> getOrder() {
		return order;
	}

	public void setOrder(List<Map<OrderKeys, String>> order) {
		this.order = order;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
}
