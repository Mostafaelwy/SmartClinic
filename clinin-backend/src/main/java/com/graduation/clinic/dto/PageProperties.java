package com.graduation.clinic.dto;


import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Sort.Direction;

public class PageProperties {

	private Integer pageNum ;
	private Integer PageSize;
	private String sortAttripute;
	private Direction dir;
	
	public PageProperties( Integer pageNum, Integer pageSize, String sortAttripute,Direction dir) {
		this.pageNum = pageNum!=null? pageNum:0 ;
		
		this.PageSize = pageSize!=null? pageSize:3;
		this.sortAttripute = sortAttripute !=null?sortAttripute:"id";
		this.dir=dir!=null? dir:Direction.ASC;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getPageSize() {
		return PageSize;
	}
	public String getSortAttripute() {
		return sortAttripute;
	}
	public Direction getDir() {
		return dir;
	}
	

	
}
