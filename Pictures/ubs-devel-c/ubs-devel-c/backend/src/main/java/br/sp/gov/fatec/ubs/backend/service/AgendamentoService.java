package br.sp.gov.fatec.ubs.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sp.gov.fatec.ubs.backend.model.AgendamentoEntity;
import br.sp.gov.fatec.ubs.backend.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // --- Métodos de Busca (Filtros) ---

    public List<AgendamentoEntity> buscarPorData(LocalDate data) {
        return agendamentoRepository.findByDataAgendamento(data);
    }

    public List<AgendamentoEntity> buscarPorPaciente(Long codigoPaciente) {
        return agendamentoRepository.findByPacienteCodigo(codigoPaciente);
    }

    public List<AgendamentoEntity> buscarPorMedico(Long codigoMedico) {
        return agendamentoRepository.findByMedicoCodigo(codigoMedico);
    }

    public List<AgendamentoEntity> buscarPorEspecialidade(String especialidade) {
        return agendamentoRepository.findByMedicoEspecialidade(especialidade);
    }

    // --- Métodos CRUD (Criar, Ler, Atualizar, Deletar) ---
    
    public List<AgendamentoEntity> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Optional<AgendamentoEntity> buscarPorCodigo(Long codigo) {
        return agendamentoRepository.findById(codigo);
    }

    public AgendamentoEntity salvar(AgendamentoEntity agendamento) {
        return agendamentoRepository.save(agendamento);
    }
    
    public AgendamentoEntity atualizarStatus(Long codigo, String novoStatus) {
        AgendamentoEntity agendamento = agendamentoRepository.findById(codigo)
            .orElseThrow(() -> new RuntimeException("Agendamento não encontrado para o código: " + codigo));
        
        // TODO: Implementar lógica de segurança do Requisito 4 aqui.
        // A verificação de permissão por especialidade do usuário logado
        // será adicionada quando a camada de segurança for finalizada.
        
        agendamento.setStatus(novoStatus);
        return agendamentoRepository.save(agendamento);
    }

    public void deletar(Long codigo) {
        agendamentoRepository.deleteById(codigo);
    }
}