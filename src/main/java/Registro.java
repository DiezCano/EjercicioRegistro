import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Registro {
    private LocalDateTime fechahora;
    private double temperatura;
    private double humedad;

}
