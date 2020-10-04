package com.mola.OnlineCatalogProject.service;

import com.mola.OnlineCatalogProject.model.SchoolUnit;
import com.mola.OnlineCatalogProject.repository.SchoolUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SchoolUnitService")
public class SchoolUnitService {

    @Autowired
    private SchoolUnitRepository schoolUnitRepository;


    public List<SchoolUnit> findAll() {
        return schoolUnitRepository.findAll();
    }

    public void save(SchoolUnit schoolUnit) {
        schoolUnitRepository.save(schoolUnit);
    }

    public String findSchoolsByUnit(Integer id) {

        return schoolUnitRepository.findById(id).get().getUnitName();
    }

    public SchoolUnit findById(Integer id) {
        Optional<SchoolUnit> schoolUnit = schoolUnitRepository.findById(id);
        if (schoolUnit.isPresent()) {
            return schoolUnit.get();
        }
        return null;
    }

    public void deleteById(Integer id) {
        schoolUnitRepository.deleteById(id);
    }

}
