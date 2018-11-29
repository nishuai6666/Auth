package com.imooc.auth.common;

public enum Whether {
	YES(1),
	NO(0);
	
	private int value;
	
	Whether(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
	
	
}
