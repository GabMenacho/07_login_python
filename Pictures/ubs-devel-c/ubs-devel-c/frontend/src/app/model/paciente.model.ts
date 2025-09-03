export class Paciente {
 
  // Informações pessoais
  pacienteSelecionado?: Paciente;

  public codigo?: number; // Código do paciente
  public nomeCompleto: string = ''; // Nome completo
  public nomeSocial?: string; // Nome social (opcional)
  public nomeMae?: string; // Nome da mãe (opcional)
  public nomePai?: string; // Nome do pai (opcional)

  // Dados de nascimento
  public dataNascimento: Date = new Date(); // Data de nascimento
  public sexo: 'Masculino' | 'Feminino' | 'Outro' = 'Masculino'; // Sexo

  // Localidade
  public nacionalidade: string = ''; // Nacionalidade
  public municipioNascimento: string = ''; // Município de nascimento

  // Outros dados
  public racaCor: 'Branca' | 'Preta' | 'Parda' | 'Amarela' | 'Indígena' = 'Branca'; // Raça/Cor
  public frequentaEscola?: 'Sim' | 'Não';
  public estabelecimentoVeiculo?: string; 
  public estabelecimentoCadastro?: string; 
  

  // Dados de deficiência
  public deficiente?: 'Sim' | 'Não'; // Se o paciente possui deficiência
  public visual?: 'Sim' | 'Não';
  public auditiva?: 'Sim' | 'Não'; 
  public motora?: 'Sim' | 'Não'; 
  public intelectual?: 'Sim' | 'Não';

  // Dados de contato
  
    public telefoneCelular: string = '';  
    public telefoneResidencial?: string; 
    public telefoneComercial?: string;   
    public email: string = '';       
    public cpf: string = '';     
    
  //Prontuario

  public prontuarioPaciente: string = "";
  
  //CNS

  public cnsPaciente: string = "";

  
  constructor(init?: Partial<Paciente>) {
    Object.assign(this, init); 
  }
  // Atributos extras para testes (adicionados conforme solicitado)
  public bairro?: string;
  public cep?: string;
  public cnh?: string;
  public cns?: string;
  public complemento?: string;
  public ctps?: string;
  public data_nascimento?: string;
  public distrito_administrativo?: string;
  public escolaridade?: string;
  public estabelecimentoVinculo?: string;
  public etnia?: string;
  public logradouro?: string;
  public municipioResidencia?: string;
  public numero?: string;
  public ocupacao?: string;
  public opm?: string;
  public orgaoEmissor?: string;
  public origemEndereco?: string;
  public passaporte?: string;
  public pisPasepNis?: string;
  public prontuario?: string;
  public referencia?: string;
  public rg?: string;
  public situacaoFamiliar?: string;
  public tipoLogradouro?: string;
  public tituloEleitor?: string;
  public uf?: string;
}
