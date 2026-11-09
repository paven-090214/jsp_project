package review.servlet.view;

public class ViewResolver {
	private final String prefix;
	private final String suffix;
	
	public ViewResolver(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	public String resolve(String viewName) {
		return prefix + viewName + suffix;
	}
}