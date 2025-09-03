package br.sp.gov.fatec.ubs.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sp.gov.fatec.ubs.backend.model.MedicoEntity;
import br.sp.gov.fatec.ubs.backend.service.MedicoService;

@RestController
@RequestMapping("/api/medico") // Seguindo o padrão do PacienteController, usando nome singular
@CrossOrigin // Boa prática para permitir acesso do frontend
public class MedicoController {

    @Autowired
    private MedicoService medicoService; // O Controller agora conversa com o Service

    @PostMapping
    public ResponseEntity<MedicoEntity> gravar(@RequestBody MedicoEntity medico) {
        MedicoEntity medicoSalvo = medicoService.salvar(medico);
        return ResponseEntity.ok(medicoSalvo);
    }
    
    @GetMapping
    public ResponseEntity<List<MedicoEntity>> listar() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }
    
    @GetMapping("/{codigo}")
    public ResponseEntity<MedicoEntity> ler(@PathVariable Long codigo) {
        Optional<MedicoEntity> medicoOpt = medicoService.buscarPorCodigo(codigo);
        return medicoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity<MedicoEntity> alterar(@PathVariable Long codigo, @RequestBody MedicoEntity medico) {
        // Verifica se o médico existe antes de tentar atualizar
        if (medicoService.buscarPorCodigo(codigo).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        medico.setCodigo(codigo); // Garante que estamos atualizando o médico correto
        MedicoEntity medicoAtualizado = medicoService.salvar(medico);
        return ResponseEntity.ok(medicoAtualizado);
    }
    
    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> remover(@PathVariable long codigo) {
        if (medicoService.buscarPorCodigo(codigo).isEmpty()) {
            return ResponseEntity.notFound().build(); 
        }
        medicoService.deletar(codigo);
        return ResponseEntity.ok().build();
    }
}