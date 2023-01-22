package com.aboshady.clinic.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelRequest {
    private String reason;
}
