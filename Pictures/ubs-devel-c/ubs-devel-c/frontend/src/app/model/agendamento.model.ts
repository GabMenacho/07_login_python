import { Medico } from "./medico.model";
import { Paciente } from "./paciente.model"; // Usando o modelo já existente!

export interface Agendamento {
  codigo?: number;
  paciente: Paciente;
  medico: Medico;
  dataAgendamento: string; // Formato "YYYY-MM-DD"
  horaAgendamento: string; // Formato "HH:mm"
  status: string;
  tipoConsulta: string; // "PRIMEIRA_VEZ" ou "RETORNO"
}