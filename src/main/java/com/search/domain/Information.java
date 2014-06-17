package com.search.domain;

import java.util.List;

public class Information {
	
	private String updatedAt;
	private String id;
	private String createdAt;
	private List<Link> _links;
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public List<Link> get_links() {
		return _links;
	}
	public void set_links(List<Link> _links) {
		this._links = _links;
	}

	
	

}

