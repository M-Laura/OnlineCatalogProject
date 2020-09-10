package com.mola.OnlineCatalogProject.service;

import com.mola.OnlineCatalogProject.model.SchoolGroup;
import com.mola.OnlineCatalogProject.model.Student;
import com.mola.OnlineCatalogProject.repository.SchoolGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ISchoolGroupService")
public class SchoolGroupService {

    @Autowired
    private SchoolGroupRepository schoolGroupRepository;


    public List<SchoolGroup> findAll() {
        return schoolGroupRepository.findAll();
    }

    public void save(SchoolGroup schoolGroup) {
        schoolGroupRepository.save(schoolGroup);
    }

    public List<Student> findStudentsByGroup(Integer id) {
        //TODO: try catch / check if present
        return schoolGroupRepository.findById(id).get().getStudents();
    }

    public SchoolGroup findById(Integer id) {
        Optional<SchoolGroup> schoolGroup = schoolGroupRepository.findById(id);
        if (schoolGroup.isPresent()) {
            return schoolGroup.get();
        }
        return null;
    }

    public void deleteById(Integer id) {
        schoolGroupRepository.deleteById(id);
    }

}