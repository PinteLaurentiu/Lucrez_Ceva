package lucrez.ceva.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
public class ResponseStatus {
    protected boolean success = true;

    public static ResponseEntity<ResponseStatus> create() {
        return new ResponseEntity<>(new ResponseStatus(), HttpStatus.OK);
    }

    public static ResponseEntity<Resource> create(Resource resource) {
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    public static ResponseEntity<ResponseStatus> create(String error) {
        return ResponseError.create(error);
    }

    public static ResponseEntity<ResponseStatus> create(Exception ex) {
        return ResponseError.create(ex.getMessage());
    }
}
