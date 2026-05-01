# customer-api

Microserviço responsável por expor dados básicos de cliente para a POC de quality gates, AI review e revisão de documentação no pipeline.

## Contextos e fluxos

- Contexto funcional e arquitetural: [docs/contexts/customer-api-context.md](docs/contexts/customer-api-context.md)
- Visão de arquitetura e configuração: [docs/contexts/00-architecture-overview.md](docs/contexts/00-architecture-overview.md)
- Fluxo principal: [docs/flows/01-consulta-de-cliente-por-id.md](docs/flows/01-consulta-de-cliente-por-id.md)
- Fluxo secundário: [docs/flows/02-classificacao-de-status-do-cliente.md](docs/flows/02-classificacao-de-status-do-cliente.md)

## Contexto para IA e revisão automatizada

- Contexto estruturado para Claude code review e doc review: [docs/ai-context.yaml](docs/ai-context.yaml)
- Skill local de Claude para code review: [.claude/skills/claude-code-review/SKILL.md](.claude/skills/claude-code-review/SKILL.md)
- Skill local de Claude para doc review: [.claude/skills/claude-doc-review/SKILL.md](.claude/skills/claude-doc-review/SKILL.md)
- Mudanças em endpoint, DTO, regra de status, configuração ou documentação devem refletir neste README, no `docs/ai-context.yaml` e nos skills locais de `.claude` quando a orientação de review mudar.

## O que o serviço faz

1. Expõe o endpoint `GET /v1/customers/{id}`.
2. Busca clientes em uma massa em memória dentro do `CustomerService`.
3. Retorna dados cadastrais básicos com `id`, `name`, `document` e `status`.
4. Retorna `404` quando o cliente não existe na base em memória.

## Stack técnica

- Linguagem: Java 21
- Build: Maven Wrapper
- Framework HTTP e runtime: Quarkus 3.34.6
- Persistência: sem banco; dados em memória no service
- Mensageria: não utiliza
- Testes: JUnit 5, Rest Assured e Quarkus Test
- Coverage: JaCoCo com exclusões de `dto`, `entity` e `config` para análise de cobertura

## Modelo de execução

- Serviço síncrono via HTTP REST.
- Regra de acesso aos dados concentrada em `CustomerService`.
- Sem dependências externas em tempo de execução.

## Entrada e saída da API

### Endpoint principal

- `GET /v1/customers/{id}`

Exemplo de resposta:

```json
{
  "id": "cus-001",
  "name": "Maria Silva",
  "document": "12345678900",
  "status": "ACTIVE"
}
```

### Regras de retorno

- Retorna `200` quando o cliente existe.
- Retorna `404` quando o `id` não estiver mapeado.
- Os status atualmente presentes na massa em memória são `ACTIVE`, `BLOCKED` e `INACTIVE`.

## Erros e validação

- A validação de existência do cliente acontece no `CustomerService` por busca em mapa em memória.
- O `CustomerResource` converte ausência de resultado em `NotFoundException`.
- Não há autenticação, headers obrigatórios ou payload de entrada no fluxo atual.

## Integrações

- Não consome APIs externas.
- Não usa filas, banco ou cache.
- É consumida pela `checkout-api`, que utiliza os campos `document` e `status` para montar o resumo de checkout.

## Impacto documental e revisão automatizada

- Mudanças em `document`, `status`, estrutura do response ou path do endpoint podem impactar diretamente a `checkout-api`.
- Mudanças em `resource`, `service`, `dto`, `application.properties`, `pom.xml`, `docs` ou `.claude` devem revisar a documentação local e a orientação de review automatizado quando necessário.

## Como subir localmente

### Pré-requisitos

- Java 21
- Maven Wrapper disponível no repositório

### Dev mode

```bash
./mvnw quarkus:dev
```

O serviço sobe por padrão na porta `8081`.

### Build

```bash
./mvnw package
```

## Testes

```bash
./mvnw test
```

## Arquivos úteis para exploração

- `src/main/java/com/jeffersonjr/customer/resource/CustomerResource.java`
- `src/main/java/com/jeffersonjr/customer/service/CustomerService.java`
- `src/main/java/com/jeffersonjr/customer/dto/CustomerResponse.java`
- `src/test/java/com/jeffersonjr/customer/resource/CustomerResourceTest.java`
- `src/test/java/com/jeffersonjr/customer/service/CustomerServiceTest.java`
- `src/main/resources/application.properties`
- `pom.xml`
# pipeline-templates
