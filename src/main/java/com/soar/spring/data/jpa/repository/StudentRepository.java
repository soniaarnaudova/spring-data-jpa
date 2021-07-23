package com.soar.spring.data.jpa.repository;

import com.soar.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByFirstName(String name);

    List<Student> findStudentByFirstNameContaining(String name);

    List<Student> findStudentByLastNameNotNull();

    List<Student> findStudentByGuardianName(String name);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //Native query
    @Query(
            value="select * from student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    //Native query Named Param - when we have multiple params passing to query
    @Query(
            value="select * from student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);


    /*
    @Modifying - when we want to make changes to rows in table,
    @Transactional to make sure that everything is ok - data is committed or rolled back
    */
    @Modifying
    @Transactional
    @Query(
            value="update student s set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmaild(String firstName, String emailId);
}