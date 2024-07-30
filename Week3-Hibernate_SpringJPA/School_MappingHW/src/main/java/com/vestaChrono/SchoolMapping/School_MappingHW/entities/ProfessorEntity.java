package com.vestaChrono.SchoolMapping.School_MappingHW.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professors")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String professorName;

    @OneToMany(mappedBy = "professor")
    private Set<SubjectEntity> subjects;

//    private List<String> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessorEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getProfessorName(), that.getProfessorName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProfessorName());
    }

}
