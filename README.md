# Sistema de faculdade

Sistema desktop desenvolvido em Java para gerenciamento de cursos, disciplinas, professores e inscrições em processos seletivos acadêmicos.

## Funcionalidades

- Cadastro, consulta, edição e exclusão de cursos
- Cadastro, consulta, edição e exclusão de disciplinas
- Cadastro, consulta, edição e exclusão de professores
- Cadastro, consulta, edição e exclusão de inscrições
- Consulta de disciplinas com processos abertos
- Persistência de dados em arquivos CSV
- Geração automática de identificadores

## Tecnologias Utilizadas

 <img src="https://skillicons.dev/icons?i=java" />

## Arquitetura

O projeto foi desenvolvido seguindo o padrão MVC, complementado pelas camadas Service e Repository para separação das responsabilidades da aplicação.

```text
View → Controller → Service → Repository → Arquivos CSV
```

## Estruturas de Dados

O sistema implementa estruturas de dados próprias, sem utilização das coleções prontas do Java.

- Lista Encadeada Genérica
- Tabela de Espalhamento (Hash Table)
- Controle de Auto Incremento através de arquivos

## Persistência

Os dados são armazenados em arquivos CSV, dispensando a utilização de banco de dados externo.

## Conceitos Aplicados

- Programação Orientada a Objetos
- Arquitetura MVC
- Repository Pattern
- Service Layer
- Estruturas de Dados
- Manipulação de Arquivos
- Generics
- Encapsulamento
- Separação de Responsabilidades

