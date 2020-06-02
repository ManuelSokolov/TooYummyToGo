Too Yummy To Go - Meta 1
========================

**Deadline:** 25 de Março de 2020, 23:55, Hora de Lisboa

**Equipa:** O trabalho deve ser feito em grupos de 3. Excepcionalmente serão aceites grupos de 2.

**Dúvidas:** Dúvidas sobre o trabalho, bem como outras discussões como procura de elementos de grupo deverão ser feitas [nos issues do repositório da cadeira de DCO](https://git.alunos.di.fc.ul.pt/dco0001/dco_1920/issues).

**Instruções:**

* Fazer fork deste repositório.
* Escolher visibilidade privado (outros grupos não poderão ver o conteúdo).
* Em settings -> members, dar acesso de _Developer_ ao utilizador **dco000**.
* Poderá agora trabalhar neste novo repositório.

**Divisão de trabalho:** Existem 3 casos de uso a trabalhar. Cada um dos membros do grupo deverá fazer um, sendo que devem colaborar de forma a estarem todos em harmonia. O modelo de domínio deverá portanto ser feito em conjunto.

**Para entregar:**

* Deverá preencher o ficheiro autores.txt.
* Deverá entregar tudo num PDF chamado ```too_yummy_to_go.pdf```.
* Não esquecer fazer um commit no git:

```
git add autores.txt
git add too_yummy_to_go.pdf
git commit -m "Adicionados autores e documentação"
```

* Depois, basta criar uma nova tag git e colocá-la no servidor:

```
git tag meta1
git push origin meta1
```

* Não cumprir estas instruções levará a que o projecto nem seja corrigido, resultando numa nota de 0.

Fraude
------

Como futuro profissional, espera-se de si uma atitude irrepreensível,
em termos éticos e deontológicos. Tenha pois o maior cuidado em
respeitar e fazer respeitar a lei da criminalidade informática.

A nível académico, alunos detetados em situação de fraude ou plágio
(plagiadores e plagiados) em alguma prova ficam reprovados à
disciplina e serão alvo de processo disciplinar, o que levará a um
registo dessa incidência no processo de aluno, podendo conduzir à
suspensão letiva ou abandono da Universidade.

Objectivo
---------

Nesta primeira meta estaremos focados na fase de análise, em que vamos
organizar o que o cliente nos transmite. Felizmente, o levantamento de casos
de uso já foi feito, e iremos trabalhar sobre eles.

Nesta primeira meta pretende-se que os alunos entreguem:

* Diagrama de Casos de Uso
* Modelo de Domínio
* SSD e contratos para os seguintes casos de uso: 5, 6 e 7.

De seguida apresenta-se uma vista geral da aplicação e a descrição dos casos de uso:


Descrição geral do Too Yummy To Go:
-----------------------------

Todos os dias são enviados para o lixo ([1]) imensos produtos perecíveis de lojas como mercearias, restaurantes ou padarias. Pretende-se desenvolver uma aplicação que permita aos comerciantes colocar conjuntos de produtos para venda ao final do dia a um preço mais interessante para os consumidores [2].

Esta aplicação deverá permitir que os utilizadores criem conta e façam login, bem como pesquisem que comerciantes existem, e encomendar vários produtos desse comerciante para recolher mais tarde. Cabe aos comerciantes confirmarem que entregaram a encomenda de cada utilizador no momento da recolha.

[1] Felizmente existem programa como o [Re:food](https://www.re-food.org/pt) ou o [Fruta Feia](https://frutafeia.pt).

[2] Qualquer semelhança desta ideia com a aplicação [Too Good to Go](https://toogoodtogo.pt/pt) não é pura coincidência.


UC1 - Registo de Utilizador
===========================

_(Foi deixado para uma segunda iteração)_

UC2 - Login do Utilizador ou Comerciante
========================================

_(Foi deixado para uma segunda iteração)_

UC3 - Criar conta de Comerciante
================================

Este caso de uso é feito pelo administrador do sistema.

_(Foi deixado para uma segunda iteração)_

UC4 - Adicionar Tipo de Produto
===============================

Descrição breve: Um comerciante deverá poder adicionar um novo tipo de produto, indicando o nome e os ingredientes. O sistema regista o tipo de produto e gera um código.

UC5 - Colocar produto à venda
==================================

**Actor Principal:** Comerciante

**Pré-condição:** Existe um comerciante autenticado

**Pós-condição:** Se os dados tiverem sido introduzidos correctamente, existem mais produtos disponíveis associados ao comerciante em questão.

**Cenário Principal (ou _Happy Path_):**

1. O comerciante indica ao sistema que pretende disponibilizar produtos para venda no dia corrente.
2. O sistema responde com a lista de tipos de produtos associadas a esse comerciante.
3. O comerciante indica que quer disponibilizar um tipo de produto, indicando o código e a quantidade desse tipo de produto.
	* Este passo pode ser repetido tantas vezes quantas o comerciante pretender.
4. O comerciante confirma a disponibilidade dos produtos para venda, indicando o horário de inicio e fim da recolha desses produtos.

**Extensões:**

- 3a. Se o código do tipo de produto não existir:
	- 3.1 O sistema prossegue com a realização do caso de uso UC4.
	- 3.2 O caso de uso continua a partir do ponto 3.

UC6 - Listar Comerciantes com Produtos Disponíveis
==================================================

**Actor Principal:** Utilizador

**Pré-condição:** Existe um utilizador autenticado

**Pós-condição:** n/a

**Cenário Principal (ou _Happy Path_):**

1. O utilizador indica ao sistema a sua localização actual.
2. O sistema responde ao utilizador com a lista de comerciantes num raio de 5km que tenham produtos para recolha durante a próxima hora.
3. Opcionalmente, o utilizador pode definir um outro raio no qual está disposto a recolher produtos.
4. Nesse caso, o sistema responde ao utilizador com uma lista mais (ou menos) refinada.
5. Opcionalmente, o utilizador poderá definir um período em que está disponível para recolha.
6. Nesse caso, o sistema indica uma lista de comerciantes que tenham produtos disponíveis em janelas temporais que se sobreponham com aquela indicada pelo utilizador.

**Extensões:**

3a. Se não existirem comerciantes no raio definido pelo utilizador:
	1. O sistema informa o utilizador.
	2. O caso de uso continua a partir do ponto 3.

UC7 - Encomendar Produtos
=========================

**Actor Principal:** Utilizador

**Pré-condição:** Existe um utilizador autenticado

**Pós-condição:** Se o caso de uso tiver sucesso, existe mais uma reserva no sistema.

**Cenário Principal (ou _Happy Path_):**

1. Este caso de uso começa pela realização do caso de uso 6.
2. O utilizador indica ao sistema que comerciante pretende visitar.
3. O sistema responde com a lista de produtos que esse comerciante tem disponível na janela temporal definida.
4. O utilizador indica que pretende incluir na sua compra um produto, indicando também a quantidade em questão.
	* Este passo pode ser repetido várias vezes.
5. O utilizador indica que pretende pagar todos os produtos entretanto adicionados, indicando os detalhes do seu cartão de crédito.
6. O sistema indica que a compra foi feita com sucesso, indicando o código da reserva ao utilizador.

**Extensões:**

- 4a. Se a quantidade pretendida pelo utilizador for superior à disponibilizada pelo comerciante:
	- 4.1. O sistema indica ao utilizador a quantidade de produtos disponível para encomenda.
	- 4.2. O caso de uso prossegue a partir do ponto 4.

UC8 - Confirmar recolha de reserva
==================================

O comerciante indica que a reserva foi recolhida, indicando ao sistema o código de reserva.
