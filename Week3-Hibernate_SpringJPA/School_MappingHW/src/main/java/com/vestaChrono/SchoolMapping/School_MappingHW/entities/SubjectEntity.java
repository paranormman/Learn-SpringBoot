package com.vestaChrono.SchoolMapping.School_MappingHW.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subjects")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subjectTitle;

    @ManyToOne
    @JoinColumn(name = "prof_subject_id")
    @JsonIgnore
    private ProfessorEntity professor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSubjectTitle(), that.getSubjectTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubjectTitle());
    }

}
