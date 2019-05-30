import re
from bs4 import BeautifulSoup
import urllib3

arquivo = open('pagina.txt', 'r')
linha = arquivo.readlines()

padrao = r'<tr class="[a-z]+"><td align="center">([0-9]+)</td><td><a href="(/SiacWWW/ListaDisciplinasEmentaPublico\.do\?cdCurso=[0-9]+&nuPerCursoInicial=[0-9]+)">([A-Z\s\-]+)</a></td></tr>'
resultado = re.findall(padrao, linha[0])

materias = []
MATERIAS = []

#arquivo = open('cursos.csv', 'w+')
#tabela = ""
#csv = []  # csv dos cursos
#for curso in resultado:
#    tabela = str(curso[0]) + ';' + str(curso[2]) + ';\n'
#    csv.append(tabela)

#arquivo.writelines(csv)
#arquivo.close()

obrigatorias = open('obrigatorias.csv', 'w+')
optativas = open('optativas.csv', 'w+')

http = urllib3.PoolManager()

for i in range(0, len(resultado)):
    print(resultado[i])
    response = http.request('GET', "https://alunoweb.ufba.br" + resultado[i][1])
    soup = BeautifulSoup(response.data, 'html.parser')
    c = soup.find_all('tr')
    materias = []
    for m in range(9, len(c)):
        materias.append(c[m].find_all('td'))

    for materia in materias:
        if len(materia) == 4:
            link = materia[2].find_all('a')
            if len(link) != 0:
                padrao = r'(/SiacWWW/ExibirEmentaPublico\.do\?cdDisciplina=[A-Za-z0-9]+&)amp;(nuPerInicial=[0-9]+)'
                link = re.findall(padrao, str(link))
                if len(link) != 0:
                    response = http.request('GET', "https://alunoweb.ufba.br" + str(link[0][0]) + str(link[0][1]))
                    soup = BeautifulSoup(response.data, 'html.parser')
                    c = soup.find_all('th')
                    padrao = r'[0-9]+'
                    carga_horaria = re.findall(padrao, str(c[1]))

                    print('obrigatoria')

                    essaDesgraca = str(resultado[i][0]) + ';'
                    essaDesgraca += materia[1].get_text() + ';' + materia[0].get_text() + '\n'
                    obrigatorias.write(essaDesgraca)

                    essaDesgraca =  materia[1].get_text() + ';' + materia[2].get_text() + ';' +  carga_horaria[1] + '\n'
                    if essaDesgraca not in MATERIAS:
                        MATERIAS.append(essaDesgraca)
        elif len(materia) == 3:
            link = materia[1].find_all('a')
            if len(link) != 0:
                padrao = r'(/SiacWWW/ExibirEmentaPublico\.do\?cdDisciplina=[A-Za-z0-9]+&)amp;(nuPerInicial=[0-9]+)'
                link = re.findall(padrao, str(link))
                if len(link) != 0:
                    response = http.request('GET', "https://alunoweb.ufba.br" + str(link[0][0]) + str(link[0][1]))
                    soup = BeautifulSoup(response.data, 'html.parser')
                    c = soup.find_all('th')
                    padrao = r'[0-9]+'
                    carga_horaria = re.findall(padrao, str(c[1]))

                    print('optativa')

                    cocorico = str(resultado[i][0]) + ';' + str(materia[0].get_text()) + '\n'
                    optativas.write(cocorico)

                    essaDesgraca = materia[0].get_text() + ';' + materia[1].get_text() + ';' + carga_horaria[1] + '\n'
                    if essaDesgraca not in MATERIAS:
                        MATERIAS.append(essaDesgraca)

disciplinas = open('disciplinas.csv', 'w+')
disciplinas.writelines(MATERIAS)

