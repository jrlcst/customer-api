---
name: claude-code-review
description: Revisa mudanças da customer-api com foco em risco real de bug, quebra de contrato, impacto na checkout-api, ausência de teste relevante, inconsistência de regra de negócio e risco de configuração. Use quando quiser fazer code review desta API sem duplicar achados de Sonar ou Trivy.
argument-hint: [contexto opcional: PR, diff, foco em contrato, foco em integração, etc.]
---

# Claude Code Review

Use este skill para revisar mudanças da `customer-api` com foco em risco real e impacto funcional.

## Contexto obrigatório

Antes de revisar qualquer diff, leia nesta ordem:

1. `docs/ai-context.yaml`
2. `README.md`
3. `docs/contexts/**` e `docs/flows/**` quando forem necessários para entender contrato, fluxo ou integração

## Objetivo

O objetivo deste review é ajudar o reviewer humano.

Este review não é gate bloqueante.

## O que deve avaliar

- possível bug funcional
- quebra de contrato HTTP
- impacto em consumidor conhecido
- alteração de regra de negócio
- ausência de teste relevante em comportamento alterado
- risco em configuração

## O que não deve avaliar

- estilo irrelevante
- comentário genérico
- refatoração grande fora do escopo
- achados já cobertos por Sonar ou Trivy

## Prioridades específicas da customer-api

- mudanças no endpoint `GET /v1/customers/{id}`
- alterações nos campos `id`, `name`, `document` e `status`
- mudanças na massa de dados ou na forma como o serviço responde `404`
- impacto na `checkout-api`, que usa `document` e `status` para montar o resumo de checkout

## Procedimento

1. Leia `docs/ai-context.yaml` para entender endpoint, consumidores e campos sensíveis.
2. Leia apenas os arquivos alterados necessários para confirmar o comportamento real.
3. Priorize mudanças em contrato, DTO, service, resource, configuração e testes.
4. Considere impacto cross-service quando houver alteração de contrato consumido pela `checkout-api`.
5. Se não houver risco concreto, declare que não encontrou findings relevantes.

## Formato esperado da resposta

- Liste findings primeiro, ordenados por severidade.
- Use linguagem curta, técnica e verificável.
- Se não houver findings, diga explicitamente que não encontrou problemas relevantes.
- Pode mencionar riscos residuais ou gaps de teste ao final.