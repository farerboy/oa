package com.farerboy.oa.vo.easyui;

import lombok.Data;

import java.util.List;

/**
 * easyui的datagrid模型
 *
 * @author farerboy
 */
@Data
public class DataGrid<T>{

	private Long total;
	
	private List<T> rows;
	
	private List footer;

}
