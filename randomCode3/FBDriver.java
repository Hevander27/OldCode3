
public class FBDriver {

	public static void main(String[] args) {
		String text = "Hello, World";
		System.out.println(FBWork.encode(text));
		System.out.println(text);
		System.out.println();
		FBMap<Integer, Integer> map = new FBMap<>();
		map.push(1, 1);
		map.push(2, 2);
		map.push(3, 1);
		System.out.println(map);
		System.out.println("1 == " + map.getValue(1));
		map.push(1, 3);//this item should cause an update, there should still only be 3 items in the map
		System.out.println(map);
		
		System.out.println("true == " + map.containsKey(1));
		System.out.println("true == " + map.containsKey(2));
		System.out.println("true == " + map.containsKey(3));
		System.out.println("false == " + map.containsKey(4));
		System.out.println("3 == " + map.getValue(1));
		System.out.println("null == " + map.getValue(4));
	}

}
