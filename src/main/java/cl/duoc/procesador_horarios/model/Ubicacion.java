package cl.duoc.procesador_horarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ubicacion {
    private String idVehiculo;
    private double latitud;
    private double longitud;
    private long timestamp;
}