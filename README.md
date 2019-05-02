# WeLoveEat
Esta aplicação web tem como objetivo satisfazer as necessidades de uma startup do ramo alimentício. O backend do projeto foi feito utilizando SpringBoot e sua principal função é armazenar os ingredientes(e seus respectivos valores unitários), e opções de lanche do menu(com seu respectivo preço, nome e ingredientes).  Além disso o usuário pode montar seu próprio lanche, e a aplicação deve calcular o preço do lanche "personalizado" considerando possíveis promoções que o lanche pode participar, dependendo dos ingredientes que este tiver.

# Requerido
Java 8, Maven

# Instruções
Após copiar o projeto todo em algum diretório da sua máquina, entre pelo prompt de comando na pasta base (WeLoveEat). 
Lembre-se: o diretório base é aquele que contém o arquivo pom.xml do projeto

$ mvn clean install

O comando maven acima irá gerar um JAR, dentro da pasta target que iremos utilizar.

Após o comando maven ter sido realizado com sucesso, iremos entrar na pasta target:

$ cd target

Você pode mapear o diretório para conferir que um arquivo "WeLoveEat-1.0-SNAPSHOT.jar" foi gerado.

$ java -jar WeLoveEat-1.0-SNAPSHOT.jar

Após o comando java -jar, o spring tratará de subir nossa aplicação.

Se tudo correu como esperado você já pode acessar a aplicação em "localhost:8080"
