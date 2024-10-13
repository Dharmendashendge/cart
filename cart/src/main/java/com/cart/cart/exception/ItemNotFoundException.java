package com.cart.cart.exception;

public class ItemNotFoundException extends RuntimeException{
	public ItemNotFoundException(Long id) {
		// TODO Auto-generated constructor stub
		super("Item with id "+id+" not found.");
	}


}
