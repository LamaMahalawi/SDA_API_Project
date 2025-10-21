package Tests.Mentoring_Practices.JsonNode.Task01;

import Tests.Mentoring_Practices.JsonNode.Task01.x.Info;

import java.util.List;

public class Test01{
	private List<ResultsItem> results;
	private Info info;

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setInfo(Info info){
		this.info = info;
	}

	public Info getInfo(){
		return info;
	}

	@Override
 	public String toString(){
		return 
			"Test01{" +
			"results = '" + results + '\'' + 
			",info = '" + info + '\'' + 
			"}";
		}
}