package com.soar.spring.data.jpa;

import com.soar.spring.data.jpa.entity.Course;
import com.soar.spring.data.jpa.entity.Teacher;
import com.soar.spring.data.jpa.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){
        Course course = Course.builder()
                .title("COS-3")
                .credit(5)
                .build();
        Course course2 = Course.builder()
                .title("COS-4")
                .credit(3)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Adam")
                .lastName("Smook")
                //.courses(Arrays.asList(course,course2))
                .build();

        teacherRepository.save(teacher);
    }

    @Test
    public void printAllTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();
        System.out.println("teachers=" + teachers);
    }
}
