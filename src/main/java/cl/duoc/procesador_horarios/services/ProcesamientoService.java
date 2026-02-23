package cl.duoc.procesador_horarios.services;

import cl.duoc.procesador_horarios.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProcesamientoService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final String TOPIC_SINK = "horarios";

    @KafkaListener(topics = "ubicaciones_vehiculos", groupId = "grupo-procesamiento")
    public void procesarUbicacion(Ubicacion ubicacion) {
        System.out.println("Recibida ubicación de: " + ubicacion.getIdVehiculo());

        Horario horarioActualizado = new Horario();
        horarioActualizado.setIdVehiculo(ubicacion.getIdVehiculo());
        horarioActualizado.setProximaParada("Estación Central");
        
        if (ubicacion.getLatitud() < -33.445) {
            horarioActualizado.setEstadoHorario("RETRASADO");
            horarioActualizado.setEstimadoLlegadaMs(System.currentTimeMillis() + 600000); // +10 min
        } else {
            horarioActualizado.setEstadoHorario("A TIEMPO");
            horarioActualizado.setEstimadoLlegadaMs(System.currentTimeMillis() + 300000); // +5 min
        }

        kafkaTemplate.send(TOPIC_SINK, horarioActualizado);
        System.out.println("Publicado horario actualizado para: " + horarioActualizado.getIdVehiculo());
    }
}