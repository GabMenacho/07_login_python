package br.sp.gov.fatec.ubs.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.sp.gov.fatec.ubs.backend.model.AgendamentoEntity;
import br.sp.gov.fatec.ubs.backend.service.AgendamentoService;

@RestController
@RequestMapping("/api/agendamento")
@CrossOrigin
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    // --- Endpoints de Filtro ---

    @GetMapping("/por-data")
    public ResponseEntity<List<AgendamentoEntity>> buscarPorData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(agendamentoService.buscarPorData(data));
    }

    @GetMapping("/por-paciente/{codigo}")
    public ResponseEntity<List<AgendamentoEntity>> buscarPorPaciente(@PathVariable Long codigo) {
        return ResponseEntity.ok(agendamentoService.buscarPorPaciente(codigo));
    }

    @GetMapping("/por-medico/{codigo}")
    public ResponseEntity<List<AgendamentoEntity>> buscarPorMedico(@PathVariable Long codigo) {
        return ResponseEntity.ok(agendamentoService.buscarPorMedico(codigo));
    }

    @GetMapping("/por-especialidade")
    public ResponseEntity<List<AgendamentoEntity>> buscarPorEspecialidade(@RequestParam("nome") String especialidade) {
        return ResponseEntity.ok(agendamentoService.buscarPorEspecialidade(especialidade));
    }

    // --- Endpoints CRUD ---

    @PostMapping
    public ResponseEntity<AgendamentoEntity> gravar(@RequestBody AgendamentoEntity agendamento) {
        return ResponseEntity.ok(agendamentoService.salvar(agendamento));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoEntity>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<AgendamentoEntity> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<AgendamentoEntity> agendamento = agendamentoService.buscarPorCodigo(codigo);
        return agendamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

 @PutMapping("/{codigo}/status")
    public ResponseEntity<AgendamentoEntity> atualizarStatus(@PathVariable Long codigo, @RequestParam("status") String status) {
        try {
            AgendamentoEntity agendamentoAtualizado = agendamentoService.atualizarStatus(codigo, status);
            return ResponseEntity.ok(agendamentoAtualizado);
        } catch (RuntimeException e) {
            // Se o agendamento não for encontrado, o service vai lançar uma RuntimeException.
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> remover(@PathVariable Long codigo) {
        if (agendamentoService.buscarPorCodigo(codigo).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        agendamentoService.deletar(codigo);
        return ResponseEntity.ok().build();
    }
}