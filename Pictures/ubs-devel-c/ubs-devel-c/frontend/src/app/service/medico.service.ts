import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Medico } from '../model/medico.model';

@Injectable({
  providedIn: 'root'
})
export class MedicoService {
  private apiUrl = 'http://localhost:8092/api/medico'; // Ajuste a porta se necessário

  constructor(private http: HttpClient) { }

  listarTodos(): Observable<Medico[]> {
    return this.http.get<Medico[]>(this.apiUrl);
  }
}