# Architecture Overview

## Sequencia logica do servico

1. O endpoint HTTP recebe um `id`.
2. O `CustomerResource` chama `customerService.findById(customerId)`.
3. O `CustomerService` procura o cliente no mapa `customers`.
4. Se houver cliente, devolve o `CustomerResponse`.
5. Se nao houver cliente, retorna `Optional.empty()` e o resource responde `404`.

## Componentes principais

### CustomerResource

- Arquivo: `src/main/java/com/jeffersonjr/customer/resource/CustomerResource.java`
- Papel: expor o endpoint `GET /v1/customers/{id}`.
- Responsabilidade: traduzir ausencia de resultado em `NotFoundException`.

### CustomerService

- Arquivo: `src/main/java/com/jeffersonjr/customer/service/CustomerService.java`
- Papel: conter a massa em memoria com os clientes da POC.
- Responsabilidade: localizar o cliente e devolver o DTO correspondente.

### CustomerResponse

- Arquivo: `src/main/java/com/jeffersonjr/customer/dto/CustomerResponse.java`
- Papel: definir o contrato JSON retornado pela API.

## Configuracao principal

- `quarkus.application.name=customer-api`
- `quarkus.http.port=8081`

Essas propriedades estao em `src/main/resources/application.properties`.

## Build, testes e coverage

- Build tool: Maven Wrapper
- Runtime: Quarkus 3.34.6
- Java release: 21
- Coverage: JaCoCo configurado no `pom.xml`
- Exclusoes de coverage: `**/dto/**`, `**/entity/**`, `**/config/**`

## Endpoints principais

- `GET /v1/customers/{id}`

## Integracoes e dependencias externas

- Nao ha chamadas HTTP externas.
- Nao ha banco, cache ou mensageria.
- Ha dependencia contratual com a `checkout-api` como consumidora do response.

## Onde isso aparece no codigo

- Entrada HTTP: `src/main/java/com/jeffersonjr/customer/resource/CustomerResource.java`
- Regra e massa em memoria: `src/main/java/com/jeffersonjr/customer/service/CustomerService.java`
- Contrato de resposta: `src/main/java/com/jeffersonjr/customer/dto/CustomerResponse.java`
- Validacao do comportamento: `src/test/java/com/jeffersonjr/customer/resource/CustomerResourceTest.java`