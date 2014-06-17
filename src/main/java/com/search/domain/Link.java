package com.search.domain;

import java.util.List;

public class Link {

	private List<Self> self;
	private List<Up> up;

	public List<Self> getSelf() {
		return self;
	}

	public void setSelf(List<Self> self) {
		this.self = self;
	}

	public List<Up> getUp() {
		return up;
	}

	public void setUp(List<Up> up) {
		this.up = up;
	}

}
