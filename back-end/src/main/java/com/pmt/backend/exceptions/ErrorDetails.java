package com.pmt.backend.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    private String message;
    private String error;
    private LocalDateTime timestamp;
}
