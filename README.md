# EcoWaste Framework

## Descrição do Projeto

O **EcoWaste Framework** é um sistema desenvolvido em Java para o gerenciamento sustentável de resíduos eletrônicos. O projeto foi desenvolvido como trabalho final da disciplina de **Programação Orientada a Objetos 2**, com foco na aplicação prática de conceitos de orientação a objetos, Design Patterns, testes automatizados, interface gráfica e geração automática de documentos.

O sistema permite cadastrar, buscar, atualizar e remover resíduos eletrônicos, exibindo os dados em uma interface gráfica Java Swing com tabela e dashboard. Além disso, para cada resíduo cadastrado, o sistema gera automaticamente arquivos de imagem e um documento XML com os dados do item.

---

## Tema

**Um framework em Java voltado ao gerenciamento sustentável de resíduos eletrônicos, integrando boas práticas de Design Patterns, testes automatizados e interface gráfica para facilitar o controle e descarte adequado desses materiais.**

---

## Relação com o TCC

Este projeto está relacionado ao tema do TCC sobre as **condições de descarte do lixo eletrônico em empresas de TIC do Alto Vale do Itajaí**.

Enquanto o TCC investiga práticas, riscos ambientais e conformidade no descarte de resíduos eletrônicos, o EcoWaste Framework funciona como uma proposta prática de sistema para registrar, organizar e documentar informações sobre esses resíduos.

---

## Funcionalidades Principais

O sistema possui as seguintes funcionalidades:

- Cadastro de resíduos eletrônicos;
- Busca de resíduos por ID;
- Atualização de dados cadastrados;
- Remoção de resíduos;
- Exibição dos resíduos em tabela;
- Dashboard com informações resumidas;
- Geração automática de imagem PNG do resíduo;
- Conversão da imagem para PPM;
- Conversão da imagem para PGM;
- Conversão da imagem para PBM;
- Separação da imagem em canais RGB;
- Geração automática de XML com dados do resíduo;
- Testes automatizados com JUnit;
- Documentação JavaDoc;
- Diagrama UML de classes.

---

## Interface Gráfica

A interface gráfica foi desenvolvida com **Java Swing**.

A tela principal permite ao usuário preencher os dados do resíduo eletrônico, como:

- ID;
- Nome;
- Categoria;
- Peso;
- Risco ambiental;
- Valor estimado;
- Moeda;
- Cidade;
- Estado;
- País;
- Continente;
- Destino;
- Status.

Após o cadastro, o sistema atualiza automaticamente a tabela, o dashboard e aciona a geração dos documentos relacionados ao resíduo.

---

## Dashboard

O dashboard apresenta informações resumidas sobre os resíduos cadastrados:

- Total de resíduos;
- Peso total;
- Quantidade de resíduos com risco alto;
- Quantidade de categorias diferentes.

---

## Geração de Documentos

Após o cadastro de um resíduo eletrônico, o sistema gera automaticamente os seguintes arquivos:

```txt
PNG
PPM
PGM
PBM
Canal RGB vermelho
Canal RGB verde
Canal RGB azul
XML
```

Esses arquivos são salvos nas seguintes pastas:

```txt
imagens-geradas
xml-gerados
```

---

## Processamento Digital de Imagens no EcoWaste Framework

O sistema utiliza recursos nativos do Java para gerar e processar imagens, como:

- `BufferedImage`;
- `Graphics2D`;
- `Color`;
- `Font`;
- `ImageIO`.

A imagem principal é gerada em formato PNG e depois convertida para diferentes representações.

---

## PPM

O formato **PPM** (*Portable Pixmap*) representa a imagem colorida utilizando os valores RGB de cada pixel.

No projeto, esse formato é utilizado para demonstrar a imagem em sua forma colorida textual, permitindo visualizar diretamente os valores de vermelho, verde e azul que compõem cada pixel.

---

## PGM

O formato **PGM** (*Portable GrayMap*) representa a imagem em escala de cinza.

A conversão utiliza a seguinte fórmula:

```txt
cinza = R * 0.299 + G * 0.587 + B * 0.114
```

Essa fórmula considera pesos diferentes para os canais vermelho, verde e azul, aproximando a percepção visual humana.

---

## PBM

O formato **PBM** (*Portable BitMap*) representa a imagem em preto e branco, utilizando valores binários como `0` e `1`.

No projeto, esse formato representa a etapa de binarização da imagem.

---

## RGB

O sistema também separa a imagem original em três canais:

- Canal vermelho;
- Canal verde;
- Canal azul.

Essa funcionalidade permite visualizar separadamente a contribuição de cada canal de cor na imagem gerada para o resíduo eletrônico.

---

## Geração de XML

O arquivo XML gerado contém os dados do resíduo eletrônico e os caminhos dos arquivos de imagem produzidos pelo sistema.

Exemplo de estrutura:

```xml
<residuo>
    <id>1</id>
    <nome>Duracell</nome>
    <categoria>Pilha</categoria>
    <peso>1.0</peso>
    <riscoAmbiental>Médio</riscoAmbiental>
    <valorEstimado>499.0</valorEstimado>
    <moeda>BRL</moeda>
    <localizacao>
        <cidade>Rio do Sul</cidade>
        <estado>SC</estado>
        <pais>Brasil</pais>
        <continente>América do Sul</continente>
    </localizacao>
    <descarte>
        <destino>Ecoponto Municipal</destino>
        <status>Em Análise</status>
    </descarte>
    <arquivos>
        <png>imagens-geradas/residuo-1-duracell.png</png>
        <ppm>imagens-geradas/residuo-1-duracell.ppm</ppm>
        <pgm>imagens-geradas/residuo-1-duracell.pgm</pgm>
        <pbm>imagens-geradas/residuo-1-duracell.pbm</pbm>
        <canalVermelho>imagens-geradas/residuo-1-duracell-canal-red.png</canalVermelho>
        <canalVerde>imagens-geradas/residuo-1-duracell-canal-green.png</canalVerde>
        <canalAzul>imagens-geradas/residuo-1-duracell-canal-blue.png</canalAzul>
    </arquivos>
</residuo>
```

---

## Design Patterns Utilizados

O projeto utiliza diferentes padrões de projeto para organizar responsabilidades, reduzir acoplamento e melhorar a manutenção do código.

---

### Repository Pattern

Classe principal:

```txt
ResiduoRepository.java
```

O **Repository Pattern** é utilizado para centralizar as operações de CRUD dos resíduos eletrônicos:

- Salvar;
- Buscar;
- Atualizar;
- Remover;
- Listar.

Esse padrão evita que a interface gráfica manipule diretamente a estrutura de armazenamento dos resíduos.

---

### Facade Pattern

Classe principal:

```txt
GerenciadorDocumentosResiduo.java
```

O **Facade Pattern** é utilizado para simplificar o processo de geração documental.

A interface gráfica chama apenas um método:

```java
processarResiduo(residuo);
```

Internamente, esse método coordena:

- Geração da imagem PNG;
- Conversão para PPM;
- Conversão para PGM;
- Conversão para PBM;
- Separação RGB;
- Geração do XML.

Dessa forma, a interface gráfica não precisa conhecer os detalhes internos dos conversores, do gerador de imagem e do serviço XML.

---

### Strategy Pattern

Classes principais:

```txt
DescarteStrategy.java
GerenciadorDescarte.java
DescarteReciclagem.java
DescarteReuso.java
DescarteSeguro.java
```

O **Strategy Pattern** permite variar a estratégia de descarte aplicada a um resíduo eletrônico.

Exemplos de estratégias:

- Reciclagem;
- Reuso;
- Descarte seguro.

Esse padrão facilita a extensão do sistema para novos tipos de descarte no futuro.

---

### Builder Pattern

Classe principal:

```txt
ResiduoBuilder.java
```

O **Builder Pattern** permite construir objetos `ResiduoEletronico` de forma organizada, legível e flexível.

Esse padrão é útil porque a classe `ResiduoEletronico` possui vários atributos relacionados ao cadastro, localização, risco ambiental e destino do resíduo.

---

### Factory Pattern

Classe principal:

```txt
ResiduoFactory.java
```

O **Factory Pattern** centraliza a criação de resíduos eletrônicos pré-configurados.

Esse padrão facilita a criação de objetos e permite que novas categorias de resíduos sejam adicionadas futuramente com regras próprias.

---

### Proxy Pattern

Classes principais:

```txt
ResiduoRepositoryProxy.java
UsuarioSistema.java
```

O **Proxy Pattern** controla o acesso a operações sensíveis do repositório, como a remoção de resíduos.

Mesmo sem uma tela de login completa na interface gráfica, o padrão foi implementado e validado por testes automatizados com JUnit.

---

### Outros Padrões Presentes no Framework

Além dos padrões principais, o projeto também possui módulos demonstrando outros padrões de projeto:

- Composite;
- Flyweight;
- Observer;
- Prototype;
- Singleton.

Esses padrões aparecem como módulos complementares do framework e foram validados por testes específicos.

---

## Estrutura de Pacotes

```txt
src
└── ecowaste
    ├── builder
    ├── composite
    ├── facade
    ├── factory
    ├── flyweight
    ├── generics
    ├── gui
    ├── model
    ├── observer
    ├── prototype
    ├── proxy
    ├── repository
    ├── service
    ├── singleton
    ├── strategy
    ├── test
    ├── testevisual
    └── util
```

---

## Principais Classes

### Interface

```txt
TelaPrincipal.java
```

Classe responsável pela interface gráfica principal do sistema, desenvolvida em Java Swing.

---

### Modelo

```txt
ResiduoEletronico.java
```

Representa o resíduo eletrônico cadastrado no sistema.

---

### Repositório

```txt
ResiduoRepository.java
```

Gerencia a coleção de resíduos eletrônicos e centraliza as operações de CRUD.

---

### Serviços

```txt
GerenciadorDocumentosResiduo.java
GerenciadorArquivosResiduo.java
ResiduoXmlService.java
GerenciadorDescarte.java
```

Essas classes são responsáveis pela geração de documentos, geração de arquivos, criação de XML e aplicação de regras de descarte.

---

### Utilitários de Imagem

```txt
GeradorImagemResiduo.java
ConversorPPM.java
ConversorPGM.java
ConversorPBM.java
ConversorRGB.java
```

Essas classes são responsáveis pela geração e conversão das imagens do sistema.

---

## Testes Automatizados

O projeto possui testes automatizados com **JUnit 5**.

Algumas classes de teste:

```txt
GerenciadorDocumentosResiduoTest.java
GerenciadorArquivosResiduoTest.java
ResiduoXmlServiceTest.java
GeradorImagemResiduoTest.java
ConversorPPMTest.java
ConversorPGMTest.java
ConversorPBMTest.java
ConversorRGBTest.java
ResiduoRepositoryTest.java
ResiduoBuilderTest.java
ResiduoFactoryTest.java
ProxyPatternTest.java
ObserverPatternTest.java
PrototypePatternTest.java
CompositePatternTest.java
FlyweightPatternTest.java
SingletonPatternTest.java
EcoWasteFacadeTest.java
GerenciadorDescarteTest.java
```

Os testes validam:

- CRUD;
- Geração de imagens;
- Conversão PPM;
- Conversão PGM;
- Conversão PBM;
- Separação RGB;
- Geração de XML;
- Aplicação de Design Patterns;
- Regras de descarte.

---

## JavaDoc

A documentação JavaDoc foi gerada em HTML e está disponível na pasta:

```txt
doc
```

Arquivo principal:

```txt
doc/index.html
```

---

## Diagrama UML

O diagrama de classes do projeto está disponível na pasta de documentação do projeto.

Arquivo sugerido:

```txt
documentacao/EcoWaste_Framework_Diagrama_Classes.pdf
```

O diagrama apresenta as principais classes do sistema, suas relações e os padrões de projeto aplicados.

---

## Tecnologias Utilizadas

- Java 21;
- Java Swing;
- JUnit 5;
- Eclipse IDE;
- JavaDoc;
- PlantUML;
- Manipulação de imagens com `BufferedImage`;
- Geração de arquivos XML;
- Design Patterns.

---

## Como Executar o Projeto

1. Abrir o projeto no Eclipse.
2. Verificar se o JDK está configurado.
3. Executar a classe:

```txt
ecowaste.gui.TelaPrincipal
```

4. Cadastrar um resíduo eletrônico pela interface gráfica.
5. Conferir os arquivos gerados nas pastas:

```txt
imagens-geradas
xml-gerados
```

---

## Como Executar os Testes

No Eclipse:

1. Abrir o pacote:

```txt
ecowaste.test
```

2. Clicar com o botão direito no pacote.
3. Selecionar:

```txt
Run As > JUnit Test
```

4. Verificar se todos os testes passam sem erros ou falhas.

---

## Relação do Projeto com Visão Computacional

O projeto também aplica conceitos básicos de visão computacional, como:

- Representação de imagem por pixels;
- Canais RGB;
- Escala de cinza;
- Binarização;
- Conversão de formatos;
- Manipulação de matriz de imagem.

Esses conceitos são aplicados nas classes responsáveis por gerar e converter as imagens dos resíduos eletrônicos.

---

## Status do Projeto

```txt
Funcionalidades principais implementadas.
Testes automatizados executados.
JavaDoc gerado.
Diagrama UML criado.
Projeto em fase final de organização para entrega.
```

---

## Autor

```txt
Lucas Miles
```

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos 2**.
