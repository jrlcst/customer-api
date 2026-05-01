# 01 - Consulta de cliente por id

## Objetivo do fluxo

Atender uma consulta sincronizada de dados cadastrais por identificador, retornando as informacoes necessarias para consumidores como a `checkout-api`.

## Sequencia ponta a ponta

1. O cliente chama `GET /v1/customers/{id}`.
2. O `CustomerResource` recebe o identificador.
3. O resource delega para `CustomerService.findById(customerId)`.
4. O service procura o cliente no mapa `customers`.
5. Se encontrar, retorna o `CustomerResponse`.
6. O resource devolve `200` com JSON.

## Entradas importantes

- `id` no path.

## Saidas importantes

- `id`
- `name`
- `document`
- `status`

## Erros relevantes

- `404` quando o cliente nao existe na massa em memoria.

## Caminho no codigo

- `src/main/java/com/jeffersonjr/customer/resource/CustomerResource.java`
- `src/main/java/com/jeffersonjr/customer/service/CustomerService.java`
- `src/main/java/com/jeffersonjr/customer/dto/CustomerResponse.java`