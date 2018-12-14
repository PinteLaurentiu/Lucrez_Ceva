package lucrez.ceva.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
public class ResponseError extends ResponseStatus {
    protected boolean success = false;
    private String reason;

    public static ResponseEntity<ResponseStatus> create(String reason) {
        return new ResponseEntity<>(new ResponseError(false, reason), HttpStatus.BAD_REQUEST);
    }
}
