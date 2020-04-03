package com.task.test;

import java.util.Objects;

public class ResultTableObject {
	
	private Integer price;
	private Integer size;
	private Type type;
	
	public Integer getPrice() {
		return this.price;
	}
	
	public void setPrice(final Integer price) {
		this.price = price;
	}
	
	public Integer getSize() {
		return this.size;
	}
	
	public void setSize(final Integer size) {
		this.size = size;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public void setType(final Type type) {
		this.type = type;
	}
	
	public boolean isValidForUpdate() {
		return this.getType() != null && this.getPrice() != null && this.getSize() != null;
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		final ResultTableObject that = (ResultTableObject) o;
		return Objects.equals(this.getPrice(), that.getPrice()) &&
				this.getType() == that.getType();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getPrice(), this.getType());
	}
	
	@Override
	public String toString() {
		return this.price + "," + this.size;
	}
	
	public enum Type {
		A,
		B,
		S;
		
		public static ResultTableObject.Type convertType(final String type) {
			if ("ask".equals(type)) {
				return A;
			}
			else if ("bid".equals(type)) {
				return B;
			}
			else if ("spread".equals(type)) {
				return S;
			}
			else {
				return null;
			}
		}
	}
}
