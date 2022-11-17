package nz.co.middleware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nz.co.middleware.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	@Query("select c from Company c where c.id = :companyId" )
	List<Company> findByIdCompany(@Param("companyId") Long companyId);

}
