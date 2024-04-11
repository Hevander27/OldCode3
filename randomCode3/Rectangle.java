/* DO NOT MODIFY */
public class Rectangle {

	private int length, width;
	public Rectangle(int l, int w) {
		length = l;
		width = w;
	}
	public Rectangle(int s) {
		length = s;
		width = s;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getPerimeter() {
		return width + width + length + length;
	}
	public int getArea() {
		return width * length;
	}
	public String toString()
	{
		return "Rectangle [L:"+length+"; W:"+width+"]";
	}
}
