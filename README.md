<h1 align="center">Email Sender</h1>
<p align="center">
  <a href="https://www.java.com">
    <img src="https://img.shields.io/badge/Java-17-yellow.svg">
  </a>
  <a href="https://spring.io/">
    <img src="https://img.shields.io/badge/Spring-3.0.7-green.svg">
  </a>
</p>

Para a versão em inglês, consulte o arquivo [README-EN.md](./README-EN.md).

## Introudução

O Email Sender é um sistema Java que permite enviar e-mails usando o protocolo SMTP. Ele utiliza o Spring e o Thymeleaf para criar e enviar vários tipos de e-mails.

## Funcionalidades

As seguintes funcionalidades estão disponíveis:

- Enviar e-mails escritos manualmente.
- Enviar e-mails usando templates, com ou sem anexos.
- Recupera todos os e-mails.
- Recupera um e-mail pelo seu ID.
- Recupera e-mails pelo nome do destinatário e pela data de envio.
<br></br>

## Instalação

Para executar este projeto Java Spring localmente, siga as instruções abaixo:

1. Clone o repositório em sua máquina local

```bash
  git clone https://github.com/laramt/mail-sender
  ````
2. Importe o projeto na sua IDE preferida.

3. Configure as variáveis de ambiente.

4. Configure o arquivo application.properties para incluir as configurações de SMTP necessárias.

5. Execute o projeto na sua IDE.
<br></br>

## Configuração

Para o envio de e-mails automáticos, é necessário adicionar as configurações de email no arquivo ``application.properties``.

```` properties
# EMAIL
spring.mail.host= seu_host_smtp
spring.mail.port= sua_porta_smtp
spring.mail.username= seu_email
spring.mail.password= sua_senha
spring.mail.properties.mail.smtp.auth= true
spring.mail.properties.mail.smtp.starttls.enable= true
 ````
Substitua os valores seu_host_smtp, sua_porta_smtp, seu_email e sua_senha pelas suas configurações específicas do servidor SMTP. Certifique-se de ter as credenciais apropriadas e as informações do servidor SMTP de seu provedor de serviços de e-mail.
<br></br>

## Contato

Se você tiver alguma dúvida ou comentário, sinta-se à vontade para entrar em contato [aqui](mailto:laramnckt@gmail.com).
