# WeLoveEat!
Esta aplicação web tem como objetivo satisfazer as necessidades de uma startup do ramo alimentício. O backend do projeto foi feito utilizando SpringBoot e sua principal função é realizar a lógica por trás do cálculo dos valores de cada lanche e considera as exceções (promoções) e os impactos que causa no valor de cada lanche. Existem os dados dos ingredientes(e seus respectivos valores unitários), e opções de lanche do menu(com seu respectivo preço, nome e ingredientes) que ficam persistidos na memória



# Requer
- Java 8
- Maven


## Instruções

Faça o download da pasta ZIP do projeto em sua máquina local

![enter image description here](https://lh3.googleusercontent.com/QcNkrdtB58sWZ14jQw_QBxjB2IPb_zegKnNz7KDMNb2LHgOYaRi-2J53c3ebfAnjOw1JP2wfaL8M "download")

Depois de baixada e descompactada, entre pelo prompt de comando ou terminal linux no diretório da pasta base do projeto

Lembre-se que a pasta base do projeto contém o arquivo **pom.xml**

 ![enter image description here](https://lh3.googleusercontent.com/1E0rvAO3lslW90kJQGXTAoqImv3gipAQHUPBJZKoRLbeUOFOU43gmB_pbobcMZ0MmVmkHUzIqHZF "diretório")

Agora rode o seguinte comando:

> mvn clean install

![enter image description here](https://lh3.googleusercontent.com/U36WnVib1Lq2rlRy4ytrGwctYTQ7mOWdf8wea6gMhSKFVzsuQWjUyV6z3EWIwpGfBG2AzIpqWvZT "build success")

Depois disso, entre na pasta **target**. Dentro dela já deve conter o jar gerado pelo Maven

![enter image description here](https://lh3.googleusercontent.com/Ok_7I07sg8JABEDbn1yvl33XhmlUU8o39xFjlymi-2bU_hBvA8FIgy8_ihU05eG_QyzuV5lT2BzT "jar")

Que vamos inicializar com o comando:

> java -jar **nome_do_seu_jar**

Depois disso o SpringBoot deve inicializar

![enter image description here](https://lh3.googleusercontent.com/wYJSf8hJPzqJ2tBP3QL077Dh2o4WUfQRy_SMgyCA1hHn4vSUZw1jXK3SDg7gvBOzIT_3xBqvqUtN "java -jar")

Depois de alguns segundos você já pode acessar a aplicação no endereço 
>  localhost:8080

![
](https://lh3.googleusercontent.com/agmuXXJTfgZo-HcRKRXjt1OiU5YPHyuPxOHf_WpvhIQqSLrEwZ89tTqm5eKZKbcMLYZD2bSG3FLb "page")
