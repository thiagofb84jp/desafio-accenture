# Projeto de Testes Automatizados com REST-Assured

## Desafio Accenture - API Testes Automatizados

Este projeto foi desenvolvido para o desafio da Accenture, com foco em testes automatizados de APIs utilizando **Rest-Assured** e **JUnit**. 
O objetivo é garantir a validação de respostas de APIs, incluindo verificação de esquemas JSON e outros tipos de validação.

## Tecnologias Utilizadas

Certifique-se de que seu ambiente de desenvolvimento atende aos seguintes requisitos:

- **Java** (versão 17)
- **Rest-Assured** (versão 5.3.0)
- **JUnit** (versão 4.13.2)
- **Gson** (versão 2.10)
- **JAXB** para manipulação de XML (versão 2.3.1)
- **IntelliJ IDEA 2023.2.2 (Community Edition)** (ou outra IDE de sua preferência)
- **Maven** (ou Gradle, dependendo do seu gerenciador de dependências)
- **Git** (opcional, para controle de versão)

## Configurações do Projeto

- **JDK 17** instalado.
- **Maven** para gerenciamento de dependências.

## Dependências Utilizadas

As principais dependências do projeto são:

- **REST-Assured 5.3.0**: Para realizar testes em APIs REST.
- **JUnit 4.13.2**: Framework para estruturação e execução dos testes.
- **Google Code GSON 2.10**: Para serialização e desserialização de objetos JSON.
- **JSON Schema Validator 5.1.1**: Para validação de respostas JSON utilizando schemas.
- **JAXB**: Biblioteca para manipulação de XML.

As dependências estão configuradas no arquivo pom.xml. Para instalar todas as dependências, execute o comando Maven:

```bash
mvn clean install
```

## Estrutura do Projeto

A estrutura do projeto segue o padrão Maven, com os diretórios separados para código fonte e testes:

- **src/test/java**: Contém os casos de teste automatizados utilizando Rest-Assured e JUnit.
- **src/main/resources**: Contém os recursos e configurações utilizadas durante a execução dos testes.

```
/CursoRest/
|-- src/
|   |-- main/
|   |   |-- java/
|           |-- br
                |-- pb 
                    |-- thiagofb84jp
                        |-- core
                        |-- dto
                        |-- test
                        |-- utils
        |-- resources
|-- pom.xml
|-- README.md
```

## Links Importantes:

- Projeto no Github: https://github.com/thiagofb84jp/desafio-accenture-api


## Contribuição:

Sinta-se à vontade para abrir issues ou enviar pull requests caso encontre algo que possa ser melhorado ou corrigido.