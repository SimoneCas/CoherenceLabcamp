package it.reply.labcamp.coherence.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import it.reply.labcamp.coherence.model.key.CustomerKey;
import it.reply.labcamp.coherence.model.key.OrderKey;
import it.reply.labcamp.coherence.model.key.ProductKey;
import it.reply.labcamp.coherence.model.value.Category;
import it.reply.labcamp.coherence.model.value.CustomerValue;
import it.reply.labcamp.coherence.model.value.GeoZone;
import it.reply.labcamp.coherence.model.value.OrderValue;
import it.reply.labcamp.coherence.model.value.PriceRange;
import it.reply.labcamp.coherence.model.value.ProductValue;

public class ClientApplication {

	private static final String PUT_CUSTOMER = "putCustomer";
	private static final String GET_CUSTOMER_VALUE = "getCustomerValue";
	private static final String GET_ALL_CUSTOMERS = "getAllCustomers";
	private static final String GET_CUSTOMERS_BY_IDS = "getCustomersByIds";
	private static final String GET_CUSTOMERS_BY_AREA = "getCustomersByArea";
	private static final String ADD_CUSTOMER_LISTENER = "addCustomerListener";
	
	private static final String PUT_ORDER = "putOrder";
	private static final String GET_ORDER = "getOrder";
	private static final String GET_ALL_ORDERS = "getAllOrders";
	private static final String GET_ORDERS_BY_TAX = "getOrdersByTax";
	private static final String CALCULATE_TOTAL_AVERAGE = "calculateTotalAverage";
	private static final String INSERT_ORDER = "insertOrder";
	private static final String UPDATE_ORDER = "updateOrder";
	
	private static final String PUT_PROCUCT = "putProduct";
	private static final String GET_PRODUCT_VALUE = "getProductValue";
	private static final String GET_ALL_PRODUCTS = "getAllProducts";
	private static final String GET_PRODUCTS_BY_IDS = "getProductsByIds";
	private static final String GET_PROD_WITH_PRICE_GREATER_THAN = "getProductsWithPriceGreaterThan";
	private static final String GET_PROD_BY_CATEGORY_AND_PRICE_RANGE = "getProductsByCategoryAndPriceRange";
	
	public static void main(String[] args){
		
		printIntroduction();
		
		
		while(true) {
			printCommands();
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n\n *****************\n *****************");
			System.out.println("Inserire comando:");
			String nextCommand = scanner.next();
			
			switch(nextCommand){
			//Customer methods
			case PUT_CUSTOMER: 
				invokePutCustomer();
				break;
			case GET_CUSTOMER_VALUE:
				invokeGetCustomerValue();
				break;
			case GET_ALL_CUSTOMERS:
				invokeGetAllCustomers();
				break;
			case GET_CUSTOMERS_BY_IDS:
				invokeGetCustomersByIds();
				break;
			case GET_CUSTOMERS_BY_AREA:
				invokeGetCustomersByArea();
				break;
			case ADD_CUSTOMER_LISTENER:
				invokeAddCustomerListener();
				break;
			//Order methods
			case PUT_ORDER:
				invokePutOrder();
				break;
			case GET_ORDER:
				invokeGetOrder();
				break;
			case GET_ALL_ORDERS:
				invokeGetAllOrders();
				break;
			case GET_ORDERS_BY_TAX:
				invokeGetOrdersByTax();
				break;
			case CALCULATE_TOTAL_AVERAGE:
				invokeCalculateTotalAverage();
				break;
			case INSERT_ORDER:
				invokeInsertOrder();
				break;
			case UPDATE_ORDER:
				invokeUpdateOrder();
				break;
			//Product methods
			case PUT_PROCUCT:
				invokePutProduct();
				break;
			case GET_PRODUCT_VALUE:
				invokeGetProductValue();
				break;
			case GET_ALL_PRODUCTS:
				invokeGetAllProducts();
				break;
			case GET_PRODUCTS_BY_IDS:
				invokeGetProductsByIds();
				break;
			case GET_PROD_WITH_PRICE_GREATER_THAN:
				invokeGetProdWithPriceGreaterThan();
				break;
			case GET_PROD_BY_CATEGORY_AND_PRICE_RANGE:
				invokeGetProdByCategoryAndPriceRange();
				break;
			default: System.out.println("ERRORE - Comando sconosciuto");
			}
			
		}
		
	}

	private static void invokeGetProdByCategoryAndPriceRange() {
		System.out.println("Start metodo invokeinvokeGetProdByCategoryAndPriceRangeAddCustomerListener");
		ProductLib lib = new ProductLib();
		System.out.print("Inserire valore categoria (SPORT, CAR, CLOTHING, HOUSE, MUSIC, GAME): ");
		Scanner scanner = new Scanner(System.in);
		String category = scanner.next();
		
		System.out.print("Inserire valore Range di Prezzo (CHEAP, EXPENSIVE, LOW_COST): ");
		String priceRange = scanner.next();
		
		Collection<ProductValue> productsByCategoryAndPriceRange = lib.getProductsByCategoryAndPriceRange(Category.valueOf(category), PriceRange.valueOf(priceRange));
		System.out.println(productsByCategoryAndPriceRange);
	}

	private static void invokeGetProdWithPriceGreaterThan() {
		System.out.println("Start metodo invokeGetProdWithPriceGreaterThan");
		ProductLib lib = new ProductLib();
		System.out.print("Inserire valore di confronto (maggiore di): ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		
		Collection<ProductValue> productsWithPriceGreaterThan = lib.getProductsWithPriceGreaterThan(Double.valueOf(input));
		System.out.println(productsWithPriceGreaterThan);
	}

	private static void invokeGetProductsByIds() {
		System.out.println("Start metodo invokeGetProductsByIds");
		ProductLib lib = new ProductLib();
		System.out.println("Inserisci lista di Product Id (separati da #): ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] productIds = input.split("#");
		List<Integer> productIdCollection = Arrays.asList(productIds).stream().map(id -> Integer.valueOf(id)).collect(Collectors.toList());
		
		Collection<ProductValue> ordersByTax = lib.getProductsByIds(productIdCollection);
		System.out.println(ordersByTax);
	}

	private static void invokeGetAllProducts() {
		System.out.println("Start metodo invokeGetAllProducts");
		ProductLib lib = new ProductLib();
		Collection<ProductValue> allProducts = lib.getAllProducts();
		System.out.println(allProducts);
	}

	private static void invokeGetProductValue() {
		System.out.println("Start metodo invokeGetProductValue");
		ProductLib lib = new ProductLib();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Product Id: ");
		String productId = scanner.next();
		
		ProductValue productValue = lib.getProductValue(Integer.valueOf(productId));
		System.out.println(productValue);
	}

	private static void invokePutProduct() {
		System.out.println("Start metodo invokePutProduct");
		ProductLib lib = new ProductLib();
		ProductValue product = new ProductValue();
		product.setCategory(Category.SPORT);
		product.setDescription("Ball Adidas");
		product.setName("Ball");
		product.setPrice(10d);
		product.setPriceRange(PriceRange.LOW_COST);
		product.setProductKey(new ProductKey(100));
		lib.put(product);
		
		ProductValue product2 = new ProductValue();
		product2.setCategory(Category.CLOTHING);
		product2.setDescription("T shirt");
		product2.setName("T shirt");
		product2.setPrice(45d);
		product2.setPriceRange(PriceRange.CHEAP);
		product2.setProductKey(new ProductKey(101));
		lib.put(product2);
		
		ProductValue product3 = new ProductValue();
		product3.setCategory(Category.CAR);
		product3.setDescription("Fiat");
		product3.setName("Fiat bravo");
		product3.setPrice(10000d);
		product3.setPriceRange(PriceRange.EXPENSIVE);
		product3.setProductKey(new ProductKey(102));
		lib.put(product3);
	}

	private static void invokeUpdateOrder() {
		System.out.println("Start metodo invokeUpdateOrder");
		OrderLib lib = new OrderLib();
		OrderValue order = new OrderValue();
		order.setNote("note");
		order.setTax(20);
		order.setOrderKey(new OrderKey(10, new CustomerKey(1)));
		List<ProductKey> products = new ArrayList<>();
		products.add(new ProductKey(107));
		products.add(new ProductKey(108));
		order.setProducts(products);
		order.setTotal(1576d);
		
		lib.updateOrder(order);
	}

	private static void invokeInsertOrder() {
		System.out.println("Start metodo invokeInsertOrder");
		OrderLib lib = new OrderLib();
		OrderValue order = new OrderValue();
		order.setNote("note");
		order.setTax(20);
		order.setOrderKey(new OrderKey(15, new CustomerKey(5)));
		List<ProductKey> products = new ArrayList<>();
		products.add(new ProductKey(105));
		products.add(new ProductKey(106));
		order.setProducts(products);
		order.setTotal(1000d);
		
		lib.updateOrder(order);
	}

	private static void invokeCalculateTotalAverage() {
		System.out.println("Start metodo invokeCalculateTotalAverage");
		OrderLib lib = new OrderLib();
		double calculateTotalAverage = lib.calculateTotalAverage();
		System.out.println("Media totali: " + calculateTotalAverage);
	}

	private static void invokeGetOrdersByTax() {
		System.out.println("Start metodo invokeGetOrdersByTax");
		OrderLib lib = new OrderLib();
		System.out.println("Inserisci lista di Tax (10, 15, 20 separati da #): ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] taxes = input.split("#");
		List<Integer> taxCollection = Arrays.asList(taxes).stream().map(tax -> Integer.valueOf(tax)).collect(Collectors.toList());
		
		Collection<OrderValue> ordersByTax = lib.getOrdersByTax(taxCollection);
		System.out.println(ordersByTax);
	}

	private static void invokeGetAllOrders() {
		System.out.println("Start metodo invokeGetAllOrders");
		OrderLib lib = new OrderLib();
		Collection<OrderValue> allOrders = lib.getAllOrders();
		System.out.println(allOrders);
	}

	private static void invokeGetOrder() {
		System.out.println("Start metodo invokeGetOrder");
		OrderLib lib = new OrderLib();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Order Id: ");
		String orderId = scanner.next();
		System.out.print("Customer Id: ");
		String customerId = scanner.next();
		
		OrderValue orderValue = lib.getOrder(Integer.valueOf(orderId), Integer.valueOf(customerId));
		System.out.println(orderValue);
	}

	private static void invokePutOrder() {
		System.out.println("Start metodo invokePutOrder");
		OrderLib lib = new OrderLib();
		OrderValue order = new OrderValue();
		order.setNote("note");
		order.setTax(20);
		order.setOrderKey(new OrderKey(10, new CustomerKey(1)));
		List<ProductKey> products = new ArrayList<>();
		products.add(new ProductKey(101));
		order.setProducts(products);
		order.setTotal(576d);
		lib.put(order);
		
		OrderValue order2 = new OrderValue();
		order2.setNote("note");
		order2.setTax(10);
		order2.setOrderKey(new OrderKey(11, new CustomerKey(2)));
		List<ProductKey> products2 = new ArrayList<>();
		products2.add(new ProductKey(100));
		order2.setProducts(products2);
		order2.setTotal(30d);
		lib.put(order2);
		
		OrderValue order3 = new OrderValue();
		order3.setNote("note");
		order3.setTax(10);
		order3.setOrderKey(new OrderKey(12, new CustomerKey(3)));
		List<ProductKey> products3 = new ArrayList<>();
		products3.add(new ProductKey(102));
		order3.setProducts(products3);
		order3.setTotal(150d);
		lib.put(order3);
	}

	private static void invokeAddCustomerListener() {
		System.out.println("Start metodo invokeAddCustomerListener");
		CustomerLib lib = new CustomerLib();
		lib.addListener(new CustomerListener());
	}

	private static void invokeGetCustomersByArea() {
		System.out.println("Start metodo invokeGetCustomersByArea");
		CustomerLib lib = new CustomerLib();
		System.out.print("Inserisci Area (EUROPE, ASIA, AMERICA, AFRICA): ");
		Scanner scanner = new Scanner(System.in);
		String area = scanner.next();
		
		Collection<CustomerValue> customersByArea = lib.getCustomersByArea(GeoZone.valueOf(area));
		System.out.println(customersByArea);
	}

	private static void invokeGetCustomersByIds() {
		System.out.println("Start metodo invokeGetCustomersByIds");
		CustomerLib lib = new CustomerLib();
		
		System.out.println("Inserisci lista di Customer Id (separati da #): ");
		Scanner scanner = new Scanner(System.in);
		String customerIds = scanner.nextLine();
		String[] ids = customerIds.split("#");
		List<Integer> idCollection = Arrays.asList(ids).stream().map(id -> Integer.valueOf(id)).collect(Collectors.toList());
		Collection<CustomerValue> customersByIds = lib.getCustomersByIds(idCollection);
		
		System.out.println(customersByIds);
	}

	private static void invokeGetAllCustomers() {
		System.out.println("Start metodo invokeGetAllCustomers");
		CustomerLib lib = new CustomerLib();
		
		Collection<CustomerValue> allCustomers = lib.getAllCustomers();
		System.out.println(allCustomers);
	}

	private static void invokeGetCustomerValue() {
		System.out.println("Start metodo invokeGetCustomerValue");
		CustomerLib lib = new CustomerLib();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Customer Id: ");
		String customerId = scanner.next();
		
		CustomerValue customerValue = lib.getCustomerValue(Integer.valueOf(customerId));
		System.out.println(customerValue);
		
	}

	private static void invokePutCustomer() {
		System.out.println("Start metodo invokePutCustomer");
		CustomerLib lib = new CustomerLib();
		CustomerValue customer = new CustomerValue();
		customer.setAge(30);
		customer.setArea(GeoZone.EUROPE);
		customer.setCustomerKey(new CustomerKey(1));
		customer.setName("mario");
		customer.setSurname("rossi");
		lib.put(customer);
		
		CustomerValue customer2 = new CustomerValue();
		customer2.setAge(35);
		customer2.setArea(GeoZone.EUROPE);
		customer2.setCustomerKey(new CustomerKey(2));
		customer2.setName("luca");
		customer2.setSurname("verdi");
		lib.put(customer2);
		
		CustomerValue customer3 = new CustomerValue();
		customer3.setAge(40);
		customer3.setArea(GeoZone.AFRICA);
		customer3.setCustomerKey(new CustomerKey(3));
		customer3.setName("aka");
		customer3.setSurname("zul");
		lib.put(customer3);
		
		CustomerValue customer4 = new CustomerValue();
		customer4.setAge(20);
		customer4.setArea(GeoZone.AMERICA);
		customer4.setCustomerKey(new CustomerKey(4));
		customer4.setName("jhon");
		customer4.setSurname("smith");
		lib.put(customer4);
		
		CustomerValue customer5 = new CustomerValue();
		customer5.setAge(50);
		customer5.setArea(GeoZone.ASIA);
		customer5.setCustomerKey(new CustomerKey(5));
		customer5.setName("xin");
		customer5.setSurname("xao");
		lib.put(customer5);
	}

	private static void printCommands() {
		System.out.println("[Customer lib] " + PUT_CUSTOMER);
		System.out.println("[Customer lib] " + GET_CUSTOMER_VALUE);
		System.out.println("[Customer lib] " + GET_ALL_CUSTOMERS);
		System.out.println("[Customer lib] " + GET_CUSTOMERS_BY_IDS);
		System.out.println("[Customer lib] " + GET_CUSTOMERS_BY_AREA);
		System.out.println("[Customer lib] " + ADD_CUSTOMER_LISTENER);
		
		System.out.println("[Order lib] " + PUT_ORDER);
		System.out.println("[Order lib] " + GET_ORDER);
		System.out.println("[Order lib] " + GET_ALL_ORDERS);
		System.out.println("[Order lib] " + GET_ORDERS_BY_TAX);
		System.out.println("[Order lib] " + CALCULATE_TOTAL_AVERAGE);
		System.out.println("[Order lib] " + INSERT_ORDER);
		System.out.println("[Order lib] " + UPDATE_ORDER);
		
		System.out.println("[Product lib] " + PUT_PROCUCT);
		System.out.println("[Product lib] " + GET_PRODUCT_VALUE);
		System.out.println("[Product lib] " + GET_ALL_PRODUCTS);
		System.out.println("[Product lib] " + GET_PRODUCTS_BY_IDS);
		System.out.println("[Product lib] " + GET_PROD_BY_CATEGORY_AND_PRICE_RANGE);
		System.out.println("[Product lib] " + GET_PROD_WITH_PRICE_GREATER_THAN);
	}

	private static void printIntroduction() {
		System.out.println("##********************************************************##");
		System.out.println("##********************************************************##");
		System.out.println("##********************************************************##");
		System.out.println("##** ___  ____         ___  ___   ___          ___  ___ **##");
		System.out.println("##**|    |    ! |   | |    |   | |    |\\    | |    |    **##");
		System.out.println("##**|    |    | |   | |    |   | |    | \\   | |    |    **##");
		System.out.println("##**|    |    | |___| |___ |__/  |___ |  \\  | |    |___ **##");
		System.out.println("##**|    |    | |   | |    |  \\  |    |   \\ | |    |    **##");
		System.out.println("##**|___ |____| |   | |___ |   \\ |___ |    \\| |___ |___ **##");
		System.out.println("##********************************************************##");	
		System.out.println("##********************************************************##");
		System.out.println("##********************************************************##");

	}
}
