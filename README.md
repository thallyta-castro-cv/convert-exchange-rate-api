# API de conversão de moedas

### Visão geral

Esta API fornece serviços para conversão de moedas utilizando a ExchangeRate-API como fonte de cotações. A aplicação é desenvolvida em Quarkus seguindo a arquitetura hexagonal e utiliza um banco de dados H2 para persistência. Além disso, a aplicação está containerizada e pode ser executada facilmente utilizando Docker Compose.

### 🎯 Por que Arquitetura Hexagonal?

A escolha da arquitetura hexagonal (também conhecida como Ports and Adapters) foi feita para garantir:

1. **Desacoplamento** - Separação clara entre as regras de negócio e os detalhes de infraestrutura, permitindo fácil substituição de componentes como banco de dados ou serviços externos.

2. **Facilidade de testes** - Com a inversão de dependência, é possível testar os casos de uso independentemente da tecnologia utilizada.

3. **Extensibilidade** - Novos adaptadores podem ser adicionados sem impactar a lógica principal da aplicação.

4. **Manutenção simples** - Código modular e organizado facilita a evolução da aplicação ao longo do tempo.

## Como executar a aplicação

### Requisitos

Antes de rodar a aplicação, certifique-se de ter instalado:

- Java 21
- Maven
- Docker (para execução em container)

### Clonando o repositório

```
git clone https://github.com/thallyta-castro-cv/convert-exchange-rate-api.git
cd convert-exchange-rate-api
```

### Executando a API

Há duas formas de rodar a aplicação: em modo desenvolvimento na IDE, ou executando o docker compose que está na raiz do projeto. A aplicação irá rodar na porta 8080.

Para rodar a aplicação em modo desenvolvimento:

```
mvn quarkus:dev
```

Executando com Docker Compose

A aplicação está containerizada e pode ser executada localmente usando Docker Compose:

```
docker compose up
````

Isso iniciará a API e todos os serviços necessários dentro de containers Docker.

### Testes

A aplicação possui testes de integração utilizando Testcontainers. Para executá-los:

```
mvn test
```

## Regras de Negócio

- Apenas moedas suportadas pela ExchangeRate-API podem ser convertidas.
- O valor mínimo para conversão é 1.00.
- O valor convertido é sempre arredondado para duas casas decimais.
- A taxa de câmbio utilizada é sempre a mais recente disponível.

## Endpoints da API

### Obter taxa de conversão da moeda
Este endpoint recupera a taxa de conversão de moedas integrando-se com a ExchangeRate-API para obter as taxas de câmbio mais recentes. Além disso, as taxas obtidas são automaticamente salvas no banco de dados, garantindo que o sistema mantenha uma cópia local das conversões mais recentes.

**Requisição:**
```http
GET /convert-currency/{base}/{target}
```

**Parâmetros de Path:**

- base (String): Código da moeda de origem (por exemplo, USD).
- target (String): Código da moeda de destino (por exemplo, BRL).


**Exemplo de resposta (200 Ok)**

```json
{
    "baseCurrency": "BRL",
    "targetCurrency": "USD",
    "rate": 0.1736,
    "timestamp": "2025-03-31T11:15:20.58624241"
}
```

**Descrição dos campos de resposta**

- **baseCurrency:** A moeda de origem, ou seja, a moeda que está sendo convertida. No exemplo, é o BRL (Real Brasileiro).
- **targetCurrency:** A moeda de destino, ou seja, a moeda para a qual a conversão será feita. No exemplo, é o USD (Dólar Americano).
- **rate:** A taxa de conversão entre a moeda base e a moeda de destino. No exemplo, o valor de 0.1736 indica que 1 BRL é igual a 0.1736 USD.
- **timestamp:** O momento exato em que a taxa de conversão foi registrada, fornecendo a data e hora em que a taxa foi obtida. No exemplo, a data e hora indicam que a taxa foi registrada em 2025-03-31 às 11:15:20.

**Respostas**

| **Código** | **Descrição**                               |
|------------|---------------------------------------------|
| 200        | Taxa de câmbio obtida com sucesso          |
| 400        | Parâmetros inválidos                       |
| 500        | Erro interno                               |


**Aqui está uma tabela com todas as moedas suportadas pela Exchange Rate API, que estão disponíveis para consulta e conversão:**

| Moeda | País/Região |
|-------|-------------|
| USD   | Estados Unidos |
| AED   | Emirados Árabes Unidos |
| AFN   | Afeganistão |
| ALL   | Albânia |
| AMD   | Armênia |
| ANG   | Antilhas Neerlandesas |
| AOA   | Angola |
| ARS   | Argentina |
| AUD   | Austrália |
| AWG   | Aruba |
| AZN   | Azerbaijão |
| BAM   | Bósnia e Herzegovina |
| BBD   | Barbados |
| BDT   | Bangladesh |
| BGN   | Bulgária |
| BHD   | Bahrein |
| BIF   | Burundi |
| BMD   | Bermudas |
| BND   | Brunei |
| BOB   | Bolívia |
| BRL   | Brasil |
| BSD   | Bahamas |
| BTN   | Butão |
| BWP   | Botswana |
| BYN   | Bielorrússia |
| BZD   | Belize |
| CAD   | Canadá |
| CDF   | República Democrática do Congo |
| CHF   | Suíça |
| CLP   | Chile |
| CNY   | China |
| COP   | Colômbia |
| CRC   | Costa Rica |
| CUP   | Cuba |
| CVE   | Cabo Verde |
| CZK   | República Tcheca |
| DJF   | Djibouti |
| DKK   | Dinamarca |
| DOP   | República Dominicana |
| DZD   | Argélia |
| EGP   | Egito |
| ERN   | Eritreia |
| ETB   | Etiópia |
| EUR   | União Europeia |
| FJD   | Fiji |
| FKP   | Ilhas Malvinas |
| FOK   | Ilhas Faroé |
| GBP   | Reino Unido |
| GEL   | Geórgia |
| GGP   | Guernsey |
| GHS   | Gana |
| GIP   | Gibraltar |
| GMD   | Gâmbia |
| GNF   | Guiné |
| GTQ   | Guatemala |
| GYD   | Guiana |
| HKD   | Hong Kong |
| HNL   | Honduras |
| HRK   | Croácia |
| HTG   | Haiti |
| HUF   | Hungria |
| IDR   | Indonésia |
| ILS   | Israel |
| IMP   | Ilha de Man |
| INR   | Índia |
| IQD   | Iraque |
| IRR   | Irã |
| ISK   | Islândia |
| JEP   | Jersey |
| JMD   | Jamaica |
| JOD   | Jordânia |
| JPY   | Japão |
| KES   | Quênia |
| KGS   | Quirguistão |
| KHR   | Camboja |
| KID   | Ilhas Kiribati |
| KMF   | Comores |
| KRW   | Coreia do Sul |
| KWD   | Kuwait |
| KYD   | Ilhas Cayman |
| KZT   | Cazaquistão |
| LAK   | Laos |
| LBP   | Líbano |
| LKR   | Sri Lanka |
| LRD   | Libéria |
| LSL   | Lesoto |
| LYD   | Líbia |
| MAD   | Marrocos |
| MDL   | Moldávia |
| MGA   | Madagascar |
| MKD   | Macedônia do Norte |
| MMK   | Myanmar |
| MNT   | Mongólia |
| MOP   | Macau |
| MRU   | Mauritânia |
| MUR   | Maurício |
| MVR   | Maldivas |
| MWK   | Malaui |
| MXN   | México |
| MYR   | Malásia |
| MZN   | Moçambique |
| NAD   | Namíbia |
| NGN   | Nigéria |
| NIO   | Nicarágua |
| NOK   | Noruega |
| NPR   | Nepal |
| NZD   | Nova Zelândia |
| OMR   | Omã |
| PAB   | Panamá |
| PEN   | Peru |
| PGK   | Papua Nova Guiné |
| PHP   | Filipinas |
| PKR   | Paquistão |
| PLN   | Polônia |
| PYG   | Paraguai |
| QAR   | Catar |
| RON   | Romênia |
| RSD   | Sérvia |
| RUB   | Rússia |
| RWF   | Ruanda |
| SAR   | Arábia Saudita |
| SBD   | Ilhas Salomão |
| SCR   | Seychelles |
| SDG   | Sudão |
| SEK   | Suécia |
| SGD   | Cingapura |
| SHP   | Santa Helena |
| SLE   | Serra Leoa |
| SLL   | Serra Leoa |
| SOS   | Somália |
| SRD   | Suriname |
| SSP   | Sudão do Sul |
| STN   | São Tomé e Príncipe |
| SYP   | Síria |
| SZL   | Suazilândia |
| THB   | Tailândia |
| TJS   | Tajiquistão |
| TMT   | Turcomenistão |
| TND   | Tunísia |
| TOP   | Tonga |
| TRY   | Turquia |
| TTD   | Trinidad e Tobago |
| TVD   | Tuvalu |
| TWD   | Taiwan |
| TZS   | Tanzânia |
| UAH   | Ucrânia |
| UGX   | Uganda |
| UYU   | Uruguai |
| UZS   | Uzbequistão |
| VES   | Venezuela |
| VND   | Vietnã |
| VUV   | Vanuatu |
| WST   | Samoa |
| XAF   | Franco CFA (África Central) |
| XCD   | Dólar do Caribe Oriental |
| XCG   | Franco CFA (África Ocidental) |
| XDR   | Direitos Especiais de Saque |
| XOF   | Franco CFA (África Ocidental) |
| XPF   | Franco CFP (Pacífico) |
| YER   | Iémen |
| ZAR   | África do Sul |
| ZMW   | Zâmbia |
| ZWL   | Zimbábue |

### Obter a lista de todas as consultas realizadas e salvas no banco de dados

Este endpoint permite obter a lista completa de todas as consultas de conversão de moedas que foram realizadas e salvas no banco de dados. Através desta funcionalidade, o usuário pode acessar o histórico de conversões, incluindo informações sobre as moedas de origem, destino, taxas de conversão e o timestamp de cada operação.

Requisição:

```http
GET /convert-currency
```

Resposta Exemplo (200 OK):

```json
[
    {
        "baseCurrency": "USD",
        "targetCurrency": "AED",
        "rate": 3.6725,
        "timestamp": "2025-03-31T11:52:18.178363"
    },
    {
        "baseCurrency": "USD",
        "targetCurrency": "BRL",
        "rate": 5.7581,
        "timestamp": "2025-03-31T11:52:39.692443"
    },
    {
        "baseCurrency": "BRL",
        "targetCurrency": "USD",
        "rate": 0.1736,
        "timestamp": "2025-03-31T11:52:59.817227"
    }
]
```

**Respostas**

| **Código** | **Descrição**                               |
|------------|---------------------------------------------|
| 200        | Lista de taxas de câmbio obtida com sucesso|
| 400        | Parâmetros inválidos                       |
| 500        | Erro interno                               |


## Tecnologias Utilizadas

- Quarkus - Framework Java para aplicações nativas e cloud-native
- H2 Database - Banco de dados em memória para armazenamento
- Testcontainers - Testes de integração isolados
- RestAssured - Testes de API
- Docker - Containerização da aplicação

## Contato

Caso tenha dúvidas ou sugestões, entre em contato pelo e-mail **thallytacastro.dev@gmail.com**.
