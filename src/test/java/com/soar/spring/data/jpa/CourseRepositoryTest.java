package com.soar.spring.data.jpa;

import com.soar.spring.data.jpa.entity.Course;
import com.soar.spring.data.jpa.entity.Student;
import com.soar.spring.data.jpa.entity.Teacher;
import com.soar.spring.data.jpa.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printAllCources(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses=" + courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Simon")
                .lastName("Walker")
                .build();

        Course course = Course.builder()
                .title("COS-5")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithTreeRecords =
                PageRequest.of(0,3);
        Pageable secondPageWithTreeRecords =
                PageRequest.of(1,2);
        List<Course> courses = courseRepository.findAll(secondPageWithTreeRecords)
                .getContent();
        Long  totalElements =
                courseRepository.findAll(secondPageWithTreeRecords)
                .getTotalElements();
        int totalPages =
                courseRepository.findAll(secondPageWithTreeRecords)
                .getTotalPages();

        System.out.println("courses = " + courses);

        System.out.println("totalElements = " +totalElements);

        System.out.println("totalPages = " + totalPages);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(0,2, Sort.by("title"));
        Pageable sortByCreditDesc =
                PageRequest.of(0,2,Sort.by("credit").descending());

        Pageable sortByTytleAndCreditDesc =
                PageRequest.of(0,2,
                        Sort.by("title").descending()
                                .and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses sortByTitle = " +courses);

        List<Course> courses2 = courseRepository.findAll(sortByCreditDesc).getContent();
        System.out.println("courses sortedByCreditsDesc = " + courses2);

        List<Course> courses3 = courseRepository.findAll(sortByTytleAndCreditDesc).getContent();
        System.out.println("courses sortByTytleAndCreditDesc = " + courses3);

    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0,10);
        List<Course> courses =
        courseRepository.findByTitleContaining("C", firstPageTenRecords).getContent();

        System.out.println("courses = " +courses);
    }

    @Test
    public void  saveCourseWithStudentAndTeacher(){
        Teacher  teacher = Teacher.builder()
                .firstName("Ivan")
                .lastName("White")
                .build();
        Student student = Student.builder()
                .firstName("Katrin")
                .lastName("Cain")
                .emailId("kacain@yahoo.com")
                .build();
        Course course = Course.builder()
                .title("IT-01")
                .credit(6)
                .teacher(teacher)
                .build();
        course.addStudent(student);
        courseRepository.save(course);
    }
}
