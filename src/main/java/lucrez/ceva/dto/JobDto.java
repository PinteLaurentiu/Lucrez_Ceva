package lucrez.ceva.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lucrez.ceva.model.enums.TipJob;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JobDto {
    private String descriere;
    private String titlu;
    private String locatie;
    private Long nrZile;
    private TipJob tipJob;
    private List<String> tags;
}
