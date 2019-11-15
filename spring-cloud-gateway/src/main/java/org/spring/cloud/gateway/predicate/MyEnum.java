package org.spring.cloud.gateway.predicate;

public enum MyEnum {

	A("a") {
		@Override
		public String getName() {
			int a = 0;
			System.out.println(a++);
			return this.getName();
		}
	};
	
	private String name;
	
	MyEnum(String name) {
		this.name = name;
	}
	
	public abstract String getName();
	
	public static void main(String[] args) {
		MyEnum a = MyEnum.A;
		String name2 = a.getName();
	}
}
