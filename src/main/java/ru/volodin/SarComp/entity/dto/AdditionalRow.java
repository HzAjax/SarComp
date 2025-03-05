package ru.volodin.SarComp.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdditionalRow {
    private String index;
    private String name;
    private String cost;
}
