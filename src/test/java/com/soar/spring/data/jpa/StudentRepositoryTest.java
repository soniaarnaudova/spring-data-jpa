package com.soar.spring.data.jpa;

import com.soar.spring.data.jpa.entity.Guardian;
import com.soar.spring.data.jpa.entity.Student;
import com.soar.spring.data.jpa.repository.StudentRepository;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("soar@yahoo.com")
                .firstName("Sonia")
                .lastName("Arnaudova")
               // .guardianName("ADN")
               // .guardianEmail("adn@gmail.com")
               // .guardianMobile("999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Peter Stone")
                .email("ggg@yahoo.com")
                .mobile("0998877665")
                .build();

        Student student = Student.builder()
                .firstName("Ivan")
                .lastName("Adam")
                .emailId("adam@gmail.com")
                .guardian(guardian)
                .build();
        studentRepository.save(student);

    }

    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student list = " + studentList);

    }

    @Test
    public void printStudentsByFirstName(){

        List<Student> studentList = studentRepository.findStudentByFirstName("Sonia");
        System.out.println("students=" + studentList);

    }

    @Test
    public void printStudentsByFirstNameContaining(){

        List<Student> studentList = studentRepository.findStudentByFirstNameContaining("i");
        System.out.println("students=" + studentList);

    }

    @Test
    public void printStudentsByLastNameNotNull(){

        List<Student> studentList = studentRepository.findStudentByLastNameNotNull();
        System.out.println("students=" + studentList);

    }

    @Test
    public void printStudentsByGuardianName(){

        List<Student> studentList = studentRepository.findStudentByGuardianName("Peter Stone");
        System.out.println("students=" + studentList);

    }

    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("soar@yahoo.com");
        System.out.println("student=" + student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String studentFirstName = studentRepository.getStudentFirstNameByEmailAddress("soar@yahoo.com");
        System.out.println("studentFirstName=" + studentFirstName);
    }

    @Test
    public void printGetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("soar@yahoo.com");
        System.out.println("student=" + student);
    }

    @Test
    public void printGetStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("soar@yahoo.com");
        System.out.println("student=" + student);
    }

    @Test
    public void updateStudentByEmailId(){
        int i = studentRepository.updateStudentNameByEmaild("Deah", "soar@yahoo.com");
        System.out.println("i=" + i);
    }

}
