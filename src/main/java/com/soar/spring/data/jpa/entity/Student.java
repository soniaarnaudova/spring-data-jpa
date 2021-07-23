package com.soar.spring.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {

    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="student_sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;

    @Column(
            name="email_address",
            nullable = false
    )
    private String emailId;

    @ManyToMany(mappedBy = "students",
    fetch = FetchType.EAGER)
    private List<Course> courses;

    @Embedded
    private Guardian guardian;

}
