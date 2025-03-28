package barber_shop_ui.controller;

import barber_shop_ui.entity.AgendamentoEntity;
import barber_shop_ui.service.impl.AgendamentoPdfExportService;
import barber_shop_ui.service.AgendamentoService;
import com.itextpdf.io.exceptions.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        originPatterns = "*", // Assim resolve!
        allowCredentials = "true"
)

@RestController
@RequestMapping("/api/agendamentos")
@AllArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final AgendamentoPdfExportService pdfExportService;

    // Endpoint para criar um agendamento (POST api/agendamentos/criar)
    @PostMapping("/criar")
    public ResponseEntity<AgendamentoEntity> criarAgendamento(@RequestBody AgendamentoEntity novoAgendamento) {
        AgendamentoEntity agendamentoCriado = agendamentoService.salvar(novoAgendamento);
        return new ResponseEntity<>(agendamentoCriado, HttpStatus.CREATED);
    }

    // Endpoint para listar todos os agendamentos (GET api/agendamentos)
    @GetMapping
    public ResponseEntity<List<AgendamentoEntity>> listarAgendamentos() {
        List<AgendamentoEntity> agendamentos = agendamentoService.listarTodos();
        return ResponseEntity.ok(agendamentos);
    }

    // Endpoint para gerar PDF (GET api/agendamentos/export/pdf)
    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportAgendamentosPdf() {
        try {
            List<AgendamentoEntity> agendamentos = agendamentoService.listarTodos();
            byte[] pdf = pdfExportService.exportAgendamentosToPdf(agendamentos);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=agendamentos.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    } @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
        boolean excluido = agendamentoService.deletarAgendamentoPorId(id);
        if (!excluido) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    //Método HTTP PUT para atualizar agendamento por ID
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoEntity> atualizarAgendamento(
            @PathVariable Long id, @RequestBody AgendamentoEntity agendamentoAtualizado) {

        AgendamentoEntity agendamento = agendamentoService.atualizarAgendamento(id, agendamentoAtualizado);
        if (agendamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agendamento);
    }
}

