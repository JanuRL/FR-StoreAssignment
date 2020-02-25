package com.portal.request;

import java.util.List;

public class OrderRequest
{
	Integer custId;
	List<String> productList;
	
	public Integer getCustId() {
		return custId;
	}
	public void setCust_id(Integer custId) {
		this.custId = custId;
	}
	public List<String> getProductList() {
		return productList;
	}
	public void setProductList(List<String> productList) {
		this.productList = productList;
	} 

}
