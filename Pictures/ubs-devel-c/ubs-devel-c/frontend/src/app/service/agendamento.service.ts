import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Agendamento } from '../model/agendamento.model';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {
  private apiUrl = 'http://localhost:8092/api/agendamento'; // Ajuste a porta se necessário

  constructor(private http: HttpClient) { }

  salvar(agendamento: any): Observable<Agendamento> {
    return this.http.post<Agendamento>(this.apiUrl, agendamento);
  }

  buscarPorData(data: string): Observable<Agendamento[]> {
    return this.http.get<Agendamento[]>(`${this.apiUrl}/por-data?data=${data}`);
  }

  // Adicione outros métodos de busca aqui conforme necessário
}