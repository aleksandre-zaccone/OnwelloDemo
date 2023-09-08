package com.zaccone.onwellodemo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "voters", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"firstName", "lastName"})
})
@EqualsAndHashCode(of = {"id", "firstName", "lastName"})
@Schema(name = "Voter", description = "Represents a voter entity")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "firstName", description = "Firstname of the candidate", example = "Aleksandre")
    @Pattern(regexp = "^[a-z]+$", message = "Firstname must contain only lowercase letters, not null")
    @Size(max = 50, message = "Firstname must not exceed 50 characters")
    @NotNull(message = "Firstname status must not be null")
    private String firstName;

    @Schema(name = "lastName", description = "Lastname of the candidate", example = "Ablotia")
    @Pattern(regexp = "^[a-z]+$", message = "Lastname must contain only lowercase letters, nut null")
    @Size(max = 50, message = "Lastname must not exceed 50 characters")
    @NotNull(message = "Lastname status must not be null")
    private String lastName;

    @Schema(name ="voted",description = "Voted status", example = "true")
    @NotNull(message = "Voted status must not be null")
    private boolean voted;

    @OneToOne(mappedBy = "voter")
    @JsonBackReference
    @ToString.Exclude
    @Schema(name="vote", description = "Vote Bulletin: Indicates whether the voter has cast their vote (true) or not (false)")
    private Vote vote;

}
