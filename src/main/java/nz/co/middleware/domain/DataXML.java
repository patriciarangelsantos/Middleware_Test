package nz.co.middleware.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@XmlRootElement(name = "Data")
public class DataXML implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Company> companies = new ArrayList<>();
	
	@JacksonXmlProperty
    @JacksonXmlElementWrapper(useWrapping = false)
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	
	

}
