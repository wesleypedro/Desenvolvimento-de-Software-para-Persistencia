# Desenvolvimento de Software para Persistência
> Disciplina ofertada pela Universidade Federal do Ceará - Campus Quixadá.

## Trabalho 02
A finalidade do trabalho é demonstrar conhecimento no conteúdo repassado por meio da prática e aplicação em um contexto real. Para isso, tivemos que apresentar uma solução de um __diagrama de classes__ e uma aplicação usando java e __JPA__(usando __Hibernate__ que é uma implementação do modelo JPA). 

### Descrição
Uma companhia é organizada em departamentos. Cada departamento tem um nome e um número. Além disso, um departamento controla vários projetos, cada um dos quais com um nome, um número de identificação e o período de tempo no qual deve ser desenvolvido. Na referida companhia, cada projeto somente pode ser desenvolvido por um departamento específico.

Existem somente três tipos de funcionários que trabalham na companhia: pesquisador, secretário e funcionário de limpeza. Para os pesquisadores, deseja-se armazenar: o nome, o endereço, o sexo, a data de aniversário, o salário e a área de atuação. Para os secretários deseja-se armazenar: o nome, o endereço, o sexo, a data de aniversário, o salário e o grau de escolaridade. Já para os funcionários de limpeza, deseja-se armazenar: o nome, o endereço, o sexo, a data de aniversário, o salário, o cargo e a jornada de trabalho. Os cargos dos funcionários responsáveis pela limpeza são hierárquicos. Assim, deseja-se armazenar também, para cada funcionário de limpeza, informações sobre o funcionário de limpeza que o gerencia. Os funcionários da companhia são identificados por meio de um código de identificação, e podem estar associados a apenas um único departamento.

Funcionários que são pesquisadores podem trabalhar em diversos projetos, independentemente de esses projetos estarem sendo desenvolvidos no mesmo departamento no qual o empregado está associado. Deve-se armazenar o número de horas semanais trabalhadas por cada pesquisador em cada projeto no qual ele trabalha. Devem-se armazenar também informações sobre os dependentes de cada funcionário para propósito de ajuda familiar. Deve-se armazenar o nome, o sexo e a data de aniversário, além do grau de parentesco com o funcionário. Sabendo que um funcionário pode ter vários dependentes.

### Obsevações
O sistema proposto foi implementado usando o padrão DAO, usando JPA para a persitencia, bem como a modelagem em um Diagrama de Classes e utilizando o Modelo Objeto Relacional.