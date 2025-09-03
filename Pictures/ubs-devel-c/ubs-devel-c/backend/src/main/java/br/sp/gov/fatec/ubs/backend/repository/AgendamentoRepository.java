package br.sp.gov.fatec.ubs.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.sp.gov.fatec.ubs.backend.model.AgendamentoEntity;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    // Requisito 3: Filtro de confirmação por data
    List<AgendamentoEntity> findByDataAgendamento(LocalDate data);

    // Requisito 5: Filtro por paciente
    List<AgendamentoEntity> findByPacienteCodigo(Long codigoPaciente);

    // Requisito 6: Filtro por médico
    List<AgendamentoEntity> findByMedicoCodigo(Long codigoMedico);

    // Requisito 6: Filtro por especialidade do médico (o Spring JPA cria o JOIN automaticamente)
    List<AgendamentoEntity> findByMedicoEspecialidade(String especialidade);
}