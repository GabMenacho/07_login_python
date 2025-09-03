package br.sp.gov.fatec.ubs.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.sp.gov.fatec.ubs.backend.model.MedicoEntity;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

}