import re
from bs4 import BeautifulSoup
import urllib3

areas = [
			'http://www.meuhorarioufba.com.br/area/1', 
			'http://www.meuhorarioufba.com.br/area/2',
			'http://www.meuhorarioufba.com.br/area/3',
			'http://www.meuhorarioufba.com.br/area/4',
			'http://www.meuhorarioufba.com.br/area/5',
			'http://www.meuhorarioufba.com.br/area/6',
			'http://www.meuhorarioufba.com.br/area/7'
		]

http = urllib3.PoolManager()

arquivo = open('pre-requisitos.csv', 'w+')

for area in areas:
	response = http.request('GET', area)
	soup = BeautifulSoup(response.data, 'html.parser')

	cursos = soup.find_all('ul')
	linksCursos = cursos[0].find_all('a')

	padrao = r'/curso/[0-9]+'

	for link in linksCursos:
		cursos = re.findall(padrao, str(link))
		response = http.request('GET', 'https://www.meuhorarioufba.com.br' + cursos[0])
		soup = BeautifulSoup(response.data, 'html.parser')

		linha = cursos[0][7:] + ';'

		scripts = soup.find_all('script')

		padrao = r'preRequisites.{(.)}'
		preRequisites = re.findall(padrao, scripts[5])

		preRequisites = preRequisites.split(':')

		contador = 0
		while contador < len(preRequisites):
			linha += preRequisites[contador] + ';'
			aux = preRequisites[contador + 1].split(',')
			aux[0].lsplit('[')
			aux[len(aux)-1].rsplit(']')

			for trancador in aux:
				linha += trancador
				arquivo.write(linha + '\n')

arquivo.close()