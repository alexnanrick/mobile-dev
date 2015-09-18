package ie.dit;

public class Person {

	private String name;
	private char gender;
	
	public Person(String name, char gender) {
		setName(name);
		setGender(gender);
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	char getGender() {
		return gender;
	}

	void setGender(char gender) {
		this.gender = gender;
	}
	
	public String toString() {
		return "Person [" + (name != null ? "name=" + name + ", " : "") + "gender=" + gender + "]";
	}
	
	public static void main(String []args) {
		Person p1 = new Person("Jane", 'F');
		Person p2 = new Person("Joe", 'M');
		
		String test = p1.toString();
		p2.toString();
		
		System.out.println(test);
		
		Student s1 = new Student("Alex", 'M', "C13451458", "DT228");
		
		test = s1.toString();
		
		System.out.println(test);
		
		s1.confirmDetails("Alex");
	}
}


