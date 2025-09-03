import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Paciente } from '../model/paciente.model';
import { Medico } from '../model/medico.model';
import { PacienteService } from '../service/paciente.service';
import { MedicoService } from '../service/medico.service';
import { AgendamentoService } from '../service/agendamento.service';

@Component({
  selector: 'app-agendamento',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent implements OnInit {
  // --- Estado da Seção de Busca ---
  searchTerm: string = '';
  searchResults: Paciente[] = [];
  loading: boolean = false;
  
  // --- Estado do Modal ---
  modalAberto: boolean = false;
  pacienteSelecionado: Paciente | null = null;
  
  // --- Estado da Seção de Agendamento ---
  pacienteParaAgendamento: Paciente | null = null;
  medicos: Medico[] = [];
  especialidades: string[] = [];
  medicosFiltrados: Medico[] = [];
  horariosDisponiveis: string[] = [];

  // --- Dados do Formulário de Agendamento ---
  agendamento = {
    especialidade: '',
    medicoCodigo: '',
    data: '',
    hora: '',
    tipoConsulta: 'PRIMEIRA_VEZ'
  };

  // --- Controle de UI ---
  secao: 'busca' | 'agendamento' = 'busca';

  constructor(
    private pacienteService: PacienteService,
    private medicoService: MedicoService,
    private agendamentoService: AgendamentoService
  ) {}

  ngOnInit(): void {
    this.carregarMedicos();
  }

  // --- Métodos de Busca de Paciente ---
  buscarPacientes() {
    this.loading = true;
    const termo = this.searchTerm.trim().toLowerCase();
    this.pacienteService.listar().subscribe(pacientes => {
      this.searchResults = pacientes.filter(p => p.nomeCompleto.toLowerCase().includes(termo));
      this.loading = false;
    });
  }

  abrirModalPaciente(paciente: Paciente) {
    this.pacienteSelecionado = paciente;
    this.modalAberto = true;
  }

  fecharModalPaciente() {
    this.modalAberto = false;
    this.pacienteSelecionado = null;
  }

  selecionarPacienteParaAgendamento(paciente: Paciente) {
    this.pacienteParaAgendamento = paciente;
    this.fecharModalPaciente();
    this.secao = 'agendamento';
  }

  voltarParaBusca() {
    this.pacienteParaAgendamento = null;
    this.secao = 'busca';
  }

  // --- Métodos de Agendamento ---

  carregarMedicos() {
    this.medicoService.listarTodos().subscribe(medicos => {
      this.medicos = medicos;
      this.medicosFiltrados = medicos;
      // Extrai especialidades únicas da lista de médicos
      this.especialidades = [...new Set(medicos.map(m => m.especialidade))];
    });
  }

  filtrarMedicos() {
    if (this.agendamento.especialidade) {
      this.medicosFiltrados = this.medicos.filter(m => m.especialidade === this.agendamento.especialidade);
    } else {
      this.medicosFiltrados = this.medicos;
    }
    this.agendamento.medicoCodigo = ''; // Reseta a seleção do médico
    this.atualizarHorariosDisponiveis();
  }

  atualizarHorariosDisponiveis() {
    if (!this.agendamento.medicoCodigo || !this.agendamento.data) {
      this.horariosDisponiveis = [];
      return;
    }

    // Lógica para gerar horários e verificar disponibilidade
    const horariosBase = this.gerarHorariosBase();
    this.agendamentoService.buscarPorData(this.agendamento.data).subscribe(agendamentosDoDia => {
      const horariosOcupados = agendamentosDoDia
        .filter(a => a.medico.codigo === Number(this.agendamento.medicoCodigo))
        .map(a => a.horaAgendamento.substring(0, 5));
        
      this.horariosDisponiveis = horariosBase.filter(h => !horariosOcupados.includes(h));
    });
  }

  gerarHorariosBase(): string[] {
    const slots = [];
    for (let hour = 8; hour < 18; hour++) {
        for (let minute of [0, 30]) {
            const timeString = `${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}`;
            slots.push(timeString);
        }
    }
    return slots;
  }
  
  agendarConsulta() {
    if (!this.isFormValid()) {
      alert('Por favor, preencha todos os campos.');
      return;
    }

    const medicoSelecionado = this.medicos.find(m => m.codigo === Number(this.agendamento.medicoCodigo));

    if (!this.pacienteParaAgendamento || !medicoSelecionado) {
        alert('Erro: Paciente ou Médico não encontrado.');
        return;
    }
    
    const novoAgendamento = {
      paciente: this.pacienteParaAgendamento,
      medico: medicoSelecionado,
      dataAgendamento: this.agendamento.data,
      horaAgendamento: this.agendamento.hora,
      status: 'scheduled',
      tipoConsulta: this.agendamento.tipoConsulta
    };

    this.agendamentoService.salvar(novoAgendamento).subscribe({
      next: () => {
        alert('Consulta agendada com sucesso!');
        this.resetarFormulario();
      },
      error: (err) => {
        console.error('Erro ao agendar consulta', err);
        alert('Não foi possível agendar a consulta. Tente novamente.');
      }
    });
  }

  isFormValid(): boolean {
    return !!this.pacienteParaAgendamento &&
           !!this.agendamento.medicoCodigo &&
           !!this.agendamento.data &&
           !!this.agendamento.hora;
  }

  resetarFormulario() {
    this.agendamento = {
      especialidade: '',
      medicoCodigo: '',
      data: '',
      hora: '',
      tipoConsulta: 'PRIMEIRA_VEZ'
    };
    this.pacienteParaAgendamento = null;
    this.secao = 'busca';
  }
}