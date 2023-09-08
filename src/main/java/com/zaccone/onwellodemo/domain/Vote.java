package com.zaccone.onwellodemo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "votes")
@EqualsAndHashCode(of = {"id"})
@Schema(name = "vote", description = "Represents a vote/bulletin  entity")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    @JsonBackReference
    @NotNull(message = "Candidate must not be null")
    @Schema(name = "candidate", description = "Candidate: The candidate associated with the vote bulletin")
    private Candidate candidate;

    @OneToOne(fetch = FetchType.EAGER,  orphanRemoval = true)
    @JoinColumn(name = "vote_id")
    @JsonManagedReference
    @NotNull(message = "Voter must not be null")
    @Schema(name = "voter", implementation = Voter.class,description = "Voter: The voter associated with the vote bulletin")
    private Voter voter;

    @NotNull(message = "Created At must not be null")
    @Schema(name = "createdAt", description = "Created At: The date and time when the vote bulletin was created",
    example = "2023-09-08T00:51:59.893Z")
    private LocalDateTime createdAt;

}
