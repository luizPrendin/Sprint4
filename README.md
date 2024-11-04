# Sprint4

# Video do Youtube:
-- https://www.youtube.com/watch?v=ug1Ljq5oHAY

# Documentação

- Dockerfile:
```Dockerfile
# Usar a imagem base do JDK 17
FROM openjdk:17-jdk-slim AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos de construção do Gradle
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src/
COPY gradlew ./
COPY gradlew.bat ./

# Dar permissão de execução ao script gradlew
RUN chmod +x gradlew

#tive que adicionar essa duas linhas pois o meu pc não estava reconhecendo gradle
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix gradlew

# Executar o comando de construção do Gradle
RUN ./gradlew build --no-daemon

# Usar uma imagem mais leve para a aplicação final
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado da fase de construção
COPY --from=build /app/build/libs/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

```
- Comandos cmd:
```Bash
## Variaveis do Script
rg=rg-SprintDevops &&
location=brazilsouth &&
appServicePlanName=plan-SprintDevops &&
webAppName=AdOptimize &&
sku=F1 &&
## Cria um novo Grupo de Recursos
az group create --name $rg --location $location &&
## Cria um novo Plano de Serviço para o App
az appservice plan create -n $appServicePlanName --location $location -g $rg --sku $sku &&
## Cria um novo Serviço de Aplicativo
## Cria a Infra em Windows
az webapp create -g $rg -p $appServicePlanName --runtime "JAVA:17" -n $webAppName
```

- Script sql(Oracle):
```sql
-- Criando sequência para Campanha
CREATE SEQUENCE campanha_seq START WITH 1 INCREMENT BY 1;

-- Criando sequência para Anuncio
CREATE SEQUENCE anuncio_seq START WITH 1 INCREMENT BY 1;

-- Tabela Campanha
CREATE TABLE Campanha (
    Id NUMBER PRIMARY KEY,
    nome VARCHAR2(255)
);

-- Tabela Anuncio
CREATE TABLE Anuncio (
    Id NUMBER PRIMARY KEY,
    titulo VARCHAR2(255) NOT NULL,
    preco NUMBER(10, 2) NOT NULL,
    descricao CLOB,
    campanha_id NUMBER,
    CONSTRAINT fk_campanha FOREIGN KEY (campanha_id) REFERENCES Campanha(Id) ON DELETE CASCADE
);

-- Trigger para auto-incremento de Campanha
CREATE OR REPLACE TRIGGER campanha_trigger
BEFORE INSERT ON Campanha
FOR EACH ROW
BEGIN
    SELECT campanha_seq.NEXTVAL INTO :NEW.Id FROM dual;
END;
/

-- Trigger para auto-incremento de Anuncio
CREATE OR REPLACE TRIGGER anuncio_trigger
BEFORE INSERT ON Anuncio
FOR EACH ROW
BEGIN
    SELECT anuncio_seq.NEXTVAL INTO :NEW.Id FROM dual;
END;
/

```
Esta API permite gerenciar anuncios e campanhas, fornecendo operações de CRUD (Criar, Ler, Atualizar e Excluir) para ambas as entidades.

## Endpoints

### Campanhas

- **POST /campanhas**: Cria uma nova campanha.
- **GET /campanhas**: Retorna todos as campanhas cadastradas.
- **GET /campanhas/{id}**: Retorna uma campanha específica com base no seu `id`.
- **PUT /camapanhas/{id}**: Atualiza os dados de uma campanha existente.
- **DELETE /campanhas/{id}**: Exclui uma campanha.

### Anuncios

- **POST /anuncios**: Cria um novo anuncio.
- **GET /anuncios**: Retorna todos os anuncios cadastrados.
- **GET /anuncios/{id}**: Retorna um anuncio específico com base no seu `id`.
- **PUT /anuncios/{id}**: Atualiza os dados de um anuncio existente.
- **DELETE /anuncios/{id}**: Exclui um anuncio.

---

## Exemplo de Requisições e Respostas

### Campanhas

#### Criar Campanha (POST `/campanhas`)

```Json
{
  "nome": "Campanha de celulares"
}
```
#### Atualizar (Put `/campanhas/{id}`)
```Json
{
  "nome": "Campanha de celulares Atualizado"
}
```
### Anuncios

#### Criar anuncio (POST `/anuncios`)
```Json
{
    "titulo":"SAMSUNG A72",
    "preco":20.30,
    "descricao":"Telefone novo",
    "campanha_id":1
}
```
#### Atualizar (Put `/anuncios/{id}`)
```Json
{
    "titulo":"SAMSUNG S22",
    "preco":20.50,
    "descricao":"Telefone novo",
    "campanha_id":1
}
```


# Integrantes
- Luiz Felipe RM552475
- Rennan Ferreira RM99364
- Jaisy Cibele RM552269
- Tomaz Pecoraro RM98499
- Gabriel Amâncio RM97936
