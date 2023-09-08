package com.zaccone.onwellodemo.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "candidates", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"firstName", "lastName"})
})
@EqualsAndHashCode(of = {"id", "firstName", "lastname"})
@Schema(name = "Candidate", description = "Represents a candidate entity")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    @Schema(name = "firstName", description = "Firstname of the candidate", example = "Issac")
    @Pattern(regexp = "^[a-z]+$", message = "Firstname must contain only lowercase letters, not null")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @NotNull(message = "Firstname status must not be null")
    private String firstName;

    @Column(name = "lastname")
    @Schema(name = "lastName", description = "Lastname of the candidate", example = "Asimov")
    @Pattern(regexp = "^[a-z]+$", message = "Lastname must contain only lowercase letters, nut null")
    @Size(max = 50, message = "Lastname must not exceed 50 characters")
    @NotNull(message = "Lastname status must not be null")
    private String lastName;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Schema(name = "votes",description = "List of Vote Bulletins: Represents the vote bulletins associated with the candidate")
    @ToString.Exclude
    private Set<Vote> votes;


}
