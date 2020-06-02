Too Yummy To Go - Meta 4
========================


**Deadline:** 20 de Maio de 2020, 23:55, Hora de Lisboa

**Equipa:** O trabalho deve ser feito em grupos de 3. Excepcionalmente serão aceites grupos de 2.

**Dúvidas:** Dúvidas sobre o trabalho deverão ser feitas [nos issues do repositório da cadeira de DCO](https://git.alunos.di.fc.ul.pt/dco0001/dco_1920/issues).

**Instruções de como começar:** No repositório local da meta 3 deverá executar os seguintes comandos para obter a última versão:

```
git pull enunciado master
```


**Para entregar:** Deverá fazer commit do seu código no sítio onde se encontra (pasta código). Esta pasta vai ser partilha entre a fase 3 e a fase 4.

Para submeter:

```
git tag meta4
git push origin meta4
```

Não cumprir estas instruções levará a que o projecto nem seja corrigido, resultando numa nota de 0.

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

Nesta fase vamos melhorar o nosso código recorrendo a vários padrões GoF. Nomeadamente:


1. Devem ser suportados ambos os meios de pagamento por cartão de crédito MonsterCard e PortugueseExpress, usando o padrão mais adequado.
2. Os diferentes métodos de pesquisa devem ser abstraídos segundo o padrão Strategy.
3. O comerciante deve ser notificado sempre que uma encomenda é feita com sucesso, de forma decoupled. (Dica: poderá alterar o cliente de forma a este registar um callback que faz print da informação da encomenda, de forma a poder preparar o pedido)
4. Deverá escrever um teste JUnit que cobra o caso de uso login (UC2), bem como a verificação dos handlers a que tem acesso.