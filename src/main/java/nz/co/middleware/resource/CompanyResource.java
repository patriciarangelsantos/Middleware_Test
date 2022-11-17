package nz.co.middleware.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import nz.co.middleware.domain.Company;
import nz.co.middleware.repository.CompanyRepository;

@RestController
@RequestMapping("/xml-api")
public class CompanyResource {
	
	@Autowired
	private CompanyRepository comRepository;
	
	@GetMapping(value="/all")
	public List<Company> all(){
		return comRepository.findAll();
	}
	
	@GetMapping(value="/{companyId}", produces = { "application/json", "application/xml" })
	public List<Company> findByCompany(@PathVariable Long companyId){
		
		return comRepository.findByIdCompany(companyId);
	}

	@PostMapping(value="/company")
	@ResponseStatus(code = HttpStatus.CREATED)
    public Company createCompany(@RequestBody Company company) {
        return comRepository.save(company);

    }
	
}
