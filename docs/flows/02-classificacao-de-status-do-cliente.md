# 02 - Classificacao de status do cliente

## Objetivo do fluxo

Manter e expor o status cadastral do cliente como parte do contrato da API, permitindo que consumidores avaliem elegibilidade em fluxos de negocio.

## Sequencia ponta a ponta

1. O `CustomerService` recebe o `customerId`.
2. O service busca o cliente no mapa `customers`.
3. Quando encontra o cliente, devolve o registro com `status` predefinido.
4. Os valores atualmente presentes na massa em memoria sao `ACTIVE`, `BLOCKED` e `INACTIVE`.
5. O endpoint expoe esse status sem transformacao adicional.

## Entradas importantes

- `customerId`

## Saidas importantes

- `status = ACTIVE`
- `status = BLOCKED`
- `status = INACTIVE`

## Erros relevantes

- Quando nao existe cliente mapeado, o fluxo nao monta resposta e o endpoint retorna `404`.

## Caminho no codigo

- `src/main/java/com/jeffersonjr/customer/service/CustomerService.java`
- `src/test/java/com/jeffersonjr/customer/resource/CustomerResourceTest.java`