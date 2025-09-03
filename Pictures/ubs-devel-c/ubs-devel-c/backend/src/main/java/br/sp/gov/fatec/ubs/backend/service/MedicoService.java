package br.sp.gov.fatec.ubs.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sp.gov.fatec.ubs.backend.model.MedicoEntity;
import br.sp.gov.fatec.ubs.backend.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoEntity salvar(MedicoEntity medico) {
        // Aqui poderíamos adicionar regras de negócio antes de salvar
        return medicoRepository.save(medico);
    }

    public List<MedicoEntity> listarTodos() {
        return medicoRepository.findAll();
    }
    
    public Optional<MedicoEntity> buscarPorCodigo(Long codigo) {
        return medicoRepository.findById(codigo);
    }
    
    public void deletar(Long codigo) {
        medicoRepository.deleteById(codigo);
    }
}