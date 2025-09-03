package br.sp.gov.fatec.ubs.backend.service;

import br.sp.gov.fatec.ubs.backend.model.PacienteEntity;
import br.sp.gov.fatec.ubs.backend.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service para gerenciar a lógica de negócios relacionada aos pacientes.
 */
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    /**
     * Retorna uma lista com todos os pacientes cadastrados.
     * Este método é o que o seu frontend chama em `pacienteService.listar()`.
     * @return Lista de PacienteEntity.
     */
    public List<PacienteEntity> listarTodos() {
        return pacienteRepository.findAll();
    }

    /**
     * Busca um paciente pelo seu código (ID).
     * @param codigo O ID do paciente.
     * @return Um Optional contendo o PacienteEntity se encontrado, ou vazio caso contrário.
     */
    public Optional<PacienteEntity> buscarPorId(Long codigo) {
        return pacienteRepository.findById(codigo);
    }

    /**
     * Salva um novo paciente ou atualiza um existente.
     * @param paciente O PacienteEntity a ser salvo.
     * @return O PacienteEntity salvo.
     */
    public PacienteEntity salvar(PacienteEntity paciente) {
        // Aqui você poderia adicionar lógicas de validação antes de salvar
        return pacienteRepository.save(paciente);
    }

    /**
     * Deleta um paciente pelo seu código (ID).
     * @param codigo O ID do paciente a ser deletado.
     */
    public void deletar(Long codigo) {
        pacienteRepository.deleteById(codigo);
    }

    /**
     * Filtra pacientes cujo nome completo contenha o termo de busca.
     * @param nome O termo a ser buscado no nome completo.
     * @return Uma lista de pacientes que correspondem ao critério.
     */
    public List<PacienteEntity> buscarPorNome(String nome) {
        return pacienteRepository.findByNomeCompletoContainingIgnoreCase(nome);
    }

    /**
     * Filtra pacientes por CPF.
     * @param cpf O CPF exato a ser buscado.
     * @return Uma lista de pacientes com o CPF informado.
     */
    public List<PacienteEntity> buscarPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }
}
