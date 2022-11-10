# Proble do saldo bancário


### Descrição

Em um cenário bancário, onde um usuário tem duas transações sendo realizadas simultaneamente. Um grande
problema que pode ocorrer é de uma transação sobrescreve valores produzidos por outra.

<p align="center">
<img src="https://github.com/rafaelomodei/banking-problem/blob/main/diagrama-classe.drawio.png"/>
</p>


---


Observando o diagrama de sequência podemos notar que o saldo da conta não é atualizado de forma correta:

<p align="center">
<img src="https://github.com/rafaelomodei/banking-problem/blob/main/transferencia-erro.drawio.png"/>
</p>


---


Se aplicarmos uma correção, vamos chegar nesse resultado:

<p align="center">
<img src="https://github.com/rafaelomodei/banking-problem/blob/main/tranferencia-corrigido.drawio.png"/>
</p>

Como as duas tarefas eram atualizar o saldo, não podemos fazer elas se forma assincronia, se não perdemos a referência do saldo correto

