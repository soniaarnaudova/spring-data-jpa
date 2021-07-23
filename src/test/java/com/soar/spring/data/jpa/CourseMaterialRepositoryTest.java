package com.soar.spring.data.jpa;


import com.soar.spring.data.jpa.entity.Course;
import com.soar.spring.data.jpa.entity.CourseMaterial;
import com.soar.spring.data.jpa.repository.CourseMaterialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial(){
        Course course =Course.builder()
                .title("COS-2")
                .credit(6)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.yahoo.com")
                .course(course)
                .build();
        courseMaterialRepository.save(courseMaterial);

    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials =
                courseMaterialRepository.findAll();
        System.out.println("courseMaterials=" + courseMaterials);
    }
}
