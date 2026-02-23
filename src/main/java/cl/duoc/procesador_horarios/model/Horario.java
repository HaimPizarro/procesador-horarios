package cl.duoc.procesador_horarios.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Horario {
    private String idVehiculo;
    private String estadoHorario;
    private String proximaParada;
    private long estimadoLlegadaMs;
}