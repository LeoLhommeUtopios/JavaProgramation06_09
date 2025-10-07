package org.example.correctionexercice1.Dto;

import ch.qos.logback.core.pattern.util.AlmostAsIsEscapeUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookDtoReceive {
    private String name;
    private String author;
}
