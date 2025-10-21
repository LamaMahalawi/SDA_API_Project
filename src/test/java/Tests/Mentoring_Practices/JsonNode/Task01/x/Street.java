package Tests.Mentoring_Practices.JsonNode.Task01.x;

public class Street{
	private Integer number;
	private String name;

	public void setNumber(Integer number){
		this.number = number;
	}

	public Integer getNumber(){
		return number;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"Street{" + 
			"number = '" + number + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}
