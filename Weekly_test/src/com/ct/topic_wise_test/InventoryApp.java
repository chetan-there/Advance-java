package com.ct.topic_wise_test;

public class InventoryApp {
	public static void main(String[] args) {
/*String productId;
	String productName;
	String category; // category can be Electronics,books,furniture
	int quantity;
	double price;
	String supplier;*/
		Product p1=new Product("101", "laptop", "Etc", 50, 70000,"NIT");
		Product p2=new Product("102", "mouse", "Etc", 220, 200,"JJ");
		Product p3=new Product("103", "cooling pad", "Etc", 400, 700,"Ltd");
		
		p1.insertProduct();
		p2.insertProduct();
		p3.insertProduct();
		p1.displayProductsByCategory("Etc");
		
	}
}
