# orientacao-academica-webserver
Sistema Orientação Acadêmica

Especificação do sistema

  Descrição do sistema:
  
  Sistema de plataforma web para envio de solicitações de orientação de grade, de alunos para professores, cujos conteúdos carrega a listagem das matérias cursadas no atual semestre e suas expectativas de resultado, aprovação ou reprovação, e listas de matérias  em que haja intenção de curso no próximo semestre, acompanhadas ou não de comentários do aluno e cujos retornos contêm o veredicto do professor quanto a escolha de grade do aluno, acompanhados ou não por um comentário textual.

  Requisitos:
  
  [RU001] Cada usuário deve possuir uma conta privada e individual
  
  [RU002] Cada usuário deve pertencer a uma categoria dentre Professor e Aluno
    
  [RU002.1] Cada aluno deve pertencer a um curso
    
  [RU002.2] Cada professor deve pertencer a um departamento
  
  [RU003] Cada solicitação de orientação pode ser enviada para mais de professor
  
  [RU004] Cada solicitação de orientação deve possuir a lista de matérias cursadas no atual semestre, com suas expectativas de resultado e lista de matérias em que haja intenção de curso no próximo semestre.
    
  [RU004.1] As expectativas de resultado estão dentre Aprovar ou Reprovar
  
  [RU005] Um professor pode aceitar ou não uma solicitação de orientação
  
  [RU006] Um professor pode enviar o veredicto da grade acompanhada ou não de um comentário textual
  
  [RU006.1] O veredicto da grade é o conjunto formado pelo veredicto das matérias
  
  [RU006.2] O veredicto das matérias está entre Aprovada e Reprovada pelo professor

  Linguagens e Tecnologias:
  
  [L001] Os models e os controllers foram desenvolvidos em Java, utilizando a biblioteca Servlet
  
  [L002] As views foram desenvolvidas em JSP, HTML, CSS e Javascript
  
  [T001] Uso de MySQLServer para o banco de dados com engine InnoDB
  
  [T002] Uso do servidor Tomcat Apache versão 9.1
  
  [T003] Uso da ferramenta de pacotes Maven
