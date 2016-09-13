package junitTest;

public enum MenuTest {
	RED("红色", "#CD12444"), // <---- 3
	GREEN("绿色", "#AB2222"), // <---- 3
	BLUE("蓝色", "#CD12444"), // <---- 3
	BLACK("黑色"), // <---- 2
	YELLOW; // <---- 1

	private MenuTest() { // <-----1

	}

	private MenuTest(String name) { // ------2
		this.name = name;
	}

	private MenuTest(String name, String style) { // -----3
		this.name = name;
		this.style = style;
	}

	private String name;
	private String style;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
