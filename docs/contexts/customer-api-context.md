# customer-api context

## O que e a API

A `customer-api` e uma API HTTP simples da POC que expoe dados cadastrais basicos de clientes. O servico nao utiliza banco nem integra com sistemas externos; os dados sao retornados a partir de uma massa em memoria definida no `CustomerService`.

## Para que serve

O objetivo principal do servico e fornecer para consumidores, especialmente a `checkout-api`, os dados minimos para composicao de fluxos de checkout:

- identificador do cliente
- nome
- documento
- status cadastral

## Papel no ecossistema

- Atua como servico fornecedor de contrato para a `checkout-api`.
- Faz parte da POC usada para demonstrar quality gates, AI review e doc revision.
- E um cenario util para demonstracao de quebra de contrato porque o campo `document` e consumido diretamente pela `checkout-api`.

## Visao de alto nivel do fluxo

1. O cliente chama `GET /v1/customers/{id}`.
2. O `CustomerResource` delega a busca para o `CustomerService`.
3. O `CustomerService` consulta um `Map<String, CustomerResponse>` em memoria.
4. Quando encontra o cliente, retorna o DTO diretamente.
5. Quando nao encontra, devolve vazio para o resource responder `404`.

## Entradas e saidas

### Entrada

- Metodo: `GET`
- Path: `/v1/customers/{id}`
- Parametro de caminho: `id`

### Saida

Resposta JSON com:

- `id`
- `name`
- `document`
- `status`

## Integracoes

- Integracoes consumidas: nenhuma.
- Consumidor conhecido: `checkout-api`, por meio de `CustomerApiClient`.
- Campos sensiveis para compatibilidade: `document` e `status`.

## Modelo de execucao

- Execucao sincrona via Quarkus REST.
- Sem filas, schedulers, jobs ou consumers assincronos.
- Sem persistencia externa.

## Arquivos de referencia

- `src/main/java/com/jeffersonjr/customer/resource/CustomerResource.java`
- `src/main/java/com/jeffersonjr/customer/service/CustomerService.java`
- `src/main/java/com/jeffersonjr/customer/dto/CustomerResponse.java`
- `src/test/java/com/jeffersonjr/customer/resource/CustomerResourceTest.java`
- `src/main/resources/application.properties`