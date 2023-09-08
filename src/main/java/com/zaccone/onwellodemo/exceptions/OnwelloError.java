package com.zaccone.onwellodemo.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OnwelloError {

    private String message;

    private LocalDateTime timestamp;

    private int httpsCode;

}
