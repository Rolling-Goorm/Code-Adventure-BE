package com.goorm.codeAdventure.problem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "programming_languages")
public class ProgrammingLanguage {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String version;

    private String imageUrl;

    @OneToMany(mappedBy = "programmingLanguage")
    private Set<SupportedLanguages> supportedLanguages = new HashSet<>();
}
