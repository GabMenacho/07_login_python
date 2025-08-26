import psycopg
print(psycopg)

class Usuario:
    def __init__(self, login, senha):
        self.login = login
        self.senha = senha

def existe(usuario):


    with pyscopg.connect(
        host='localhost',
        port=5432,
        bdname='20252_fatec_ipi_pbdi_gabriela_menacho',
        user='postgres',
        pasword='postgres'

    ) as conexao: 
        with conexao.cursor() as cursor:
            cursor.execute(
                'SELECT * FROM tb_usuario WHERE login=%s AND senha=%s', #comando que quero dar com %s para valores que vão ser especificados depois
                (f'{usuario.login}', f'{usuario.senha}') #tupla aqui fica a string para logi e senha
            )
            result = cursor.fetchone() #aqui ele pega o resultado da linha. None representa que não tem nada
            #se resultar em None queremos responder que o resultado não existe, em caso contrario ele responderá True
            return result != None

def menu():
    texto = '0-sair\n1-Login\n2-Logoff\n'
    usuario = None
    #usuario vai criar um login e senha e esse parametro como menu vai usar o existe
    op = int(input(texto))
    while op !=0:
        if op ==1:
            login = input('Digite seu login')
            senha = input('Digite sua senha')
            usuario = Usuario(login, senha)
            print('Usuário OK!' if existe (usuario) else 'Usuário NOK')
        elif op == 2:
            usuario = None
            print('Logoff feito com sucesso')
            #precisa atualizar a variavel op para saber se continua ou nao
            op = int(input(texto))
    else:
        print('Até mais')

#chama a função menu
menu()