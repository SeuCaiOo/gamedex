# GameDex

GameDex é um aplicativo Android nativo, construído com as tecnologias mais modernas do ecossistema Android, para explorar o vasto universo dos jogos utilizando a API gratuita da [RAWG.io](https://rawg.io/).

<p align="center">
  <img src="https://img.shields.io/badge/version-1.0.0--alpha-blue" alt="Version">
  <img src="https://img.shields.io/badge/API-RAWG.io-brightgreen" alt="API">
  <img src="https://img.shields.io/badge/Kotlin-100%25-blueviolet" alt="Kotlin">
  <img src="https://img.shields.io/badge/Compose-100%25-00C853" alt="Compose">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License">
</p>

## 🖼️ Screenshots & Gifs


| Plataformas | Detalhes da Plataforma | Pesquisa |
| :--------------: | :--------------: | :--------------: |
| ![Lista](https://github.com/user-attachments/assets/53bc3d7b-e8fc-4913-88eb-2ee4d8e2e9f6) | ![Detalhes1](https://github.com/user-attachments/assets/03e5880b-7477-4f51-8cc1-9a7e4e1a47b2) | ![Pesquisa](https://github.com/user-attachments/assets/7a6c44f6-3363-4c15-bfe5-ca59a69f4343) | 
|  | ![Detalhes2](https://github.com/user-attachments/assets/5e152eb8-89c4-4975-ab86-2cf070a7b7e3) |  |



| Jogos | Detalhes do Jogo | Erro |
| :--------------: | :--------------: | :--------------: |
| ![Lista](https://github.com/user-attachments/assets/404b131b-ce39-44e7-8880-63b34bb261b5) | ![Detalhes1](https://github.com/user-attachments/assets/77fae813-ccab-45f9-894c-2144c67b3e94) | ![Erro](https://github.com/user-attachments/assets/64816c34-d5df-4829-9c62-9032803ef5e1) | 
|  | ![Detalhes2](https://github.com/user-attachments/assets/ae7d6a47-bf23-4e14-bb69-d3b9cd7b3c99) | ![Erro](https://github.com/user-attachments/assets/a380757c-3f38-4f71-b3f8-9e2d51283d94) |


<details>
<summary><h2>🏛️ Arquitetura e Estrutura do Projeto</h2></summary>

O projeto segue a **Clean Architecture**, dividida em três módulos principais para garantir uma separação clara de responsabilidades, testabilidade e manutenibilidade.

-   **`:app` (Camada de Apresentação)**
    -   Responsável por toda a interface do usuário (UI) e interação com o usuário.
    -   Contém as telas (`Screens`) construídas com **Jetpack Compose** e **Material 3**.
    -   Utiliza `ViewModels` para gerenciar o estado da UI e orquestrar as ações do usuário.
    -   Implementa o padrão **UDF (Unidirectional Data Flow)** com `UiState`, `UiAction` e `UiEvent`.
    -   Gerencia a navegação com **Navigation Compose**.

-   **`:domain` (Camada de Domínio)**
    -   É o coração do aplicativo, contendo a lógica de negócio pura.
    -   É um módulo **Kotlin puro**, sem dependências do Android.
    -   Define os `UseCases` (casos de uso) que encapsulam as regras de negócio.
    -   Define os `Models` de domínio (ex: `GamePlatform`, `GamePlatformDetail`).
    -   Define as `Interfaces` dos repositórios, que servem como um contrato para a camada de dados.

-   **`:data` (Camada de Dados)**
    -   Responsável por fornecer os dados para a camada de domínio.
    -   Implementa as interfaces de repositório definidas no `:domain`.
    -   Gerencia as diferentes fontes de dados:
        -   **Remote:** Acesso à API da RAWG.io usando **Retrofit**.
        -   **Local:** Persistência de dados com **Room** para cache e suporte offline.
    -   Contém os `Mappers` para converter DTOs (Data Transfer Objects) da API em modelos de domínio.

</details>

<details>
<summary><h2>🛠️ Bibliotecas e Tecnologias</h2></summary>

-   **Linguagem:** [Kotlin](https://kotlinlang.org/)
-   **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) com [Material 3](https://m3.material.io/).
-   **Gerenciamento de Estado:** ViewModel, StateFlow e SharedFlow (Padrão UDF).
-   **Assincronismo:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) e [Flow](https://kotlinlang.org/docs/flow.html).
-   **Injeção de Dependência:** [Koin](https://insert-koin.io/).
-   **Rede:** [Retrofit](https://square.github.io/retrofit/) e [OkHttp](https://square.github.io/okhttp/).
-   **Parsing JSON:** [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization).
-   **Persistência Local:** [Room](https://developer.android.com/jetpack/androidx/releases/room).
-   **Carregamento de Imagens:** [Coil](https://coil-kt.github.io/coil/).
-   **Testes:**
    -   [JUnit4](https://junit.org/junit4/)
    -   [MockK](https://mockk.io/) para mocking.
    -   [Turbine](https://github.com/cashapp/turbine) para testar Flows.
-   **Qualidade de Código:**
    -   [Detekt](https://detekt.dev/) para análise estática.
    -   [Kover](https://kotlinlang.org/docs/kover-overview.html) para cobertura de código.

</details>

<details>
<summary><h2>🗺️ Roadmap</h2></summary>

O roadmap do projeto é dividido por versões, detalhando as principais entregas em cada uma.

### ✅ Versão 1.1.0-alpha
Esta versão focou na implementação da principal feature do aplicativo, a exploração de jogos, e na adição de um robusto ferramental de qualidade de código.

*   [x] **Feature Completa de Jogos:**
    *   [x] **Camada de Domínio (`:domain`):**
        *   [x] Criação de novos modelos de domínio (`Game`, `GameDetails`).
        *   [x] Desenvolvimento de `UseCases` para buscar a lista de jogos e os detalhes de um jogo.
        *   [x] Definição da interface `GamesRepository`.
    *   [x] **Camada de Dados (`:data`):**
        *   [x] Implementação do `GamesRepositoryImpl`.
        *   [x] Criação de `GamesRemoteDataSource` para comunicação com a API.
        *   [x] Definição de novos DTOs para parsing das respostas da API de jogos.
        *   [x] Criação de `Mappers` para converter DTOs em modelos de domínio.
    *   [x] **Camada de Apresentação (`:app`):**
        *   [x] Desenvolvimento da tela de listagem de jogos (`GameListScreen`).
        *   [x] Desenvolvimento da tela de detalhes de jogos (`GameDetailsScreen`).
        *   [x] Criação dos `ViewModels` e componentes MVI (`UiState`, `UiAction`, `UiEvent`) para ambas as telas.
        *   [x] Implementação da navegação entre a tela de plataformas e as novas telas de jogos.
        *   [x] Adição de novos componentes de UI reutilizáveis para a tela de detalhes.
        *   [x] Implementação da funcionalidade de busca de jogos com um `SearchBottomSheetContent`.

*   [x] **Melhorias de Infraestrutura e Qualidade:**
    *   [x] **Análise Estática:** Integração completa do **Detekt** para garantir a qualidade e o padrão do código.
    *   [x] **Cobertura de Testes:** Integração do **Kover** para gerar relatórios de cobertura de testes agregados.
    *   [x] **Carregamento de Imagens:** Adição e configuração da biblioteca **Coil**.

*   [x] **Testes:**
    *   [x] Criação de testes unitários para os novos `UseCases` e `RemoteDataSource` da feature de jogos.

### ✅ Versão 1.0.0-alpha
*   [x] **Estrutura do Projeto:**
    *   [x] Fundação com Clean Architecture e 3 módulos (`:app`, `:data`, `:domain`).
    *   [x] Configuração de injeção de dependência com Koin.
*   [x] **Feature de Plataformas:**
    *   [x] Listagem de todas as plataformas de jogos.
    *   [x] Tela de detalhes para cada plataforma.
*   [x] **Cache com Room:** Implementação de cache para a feature de Plataformas.
*   [x] **Testes Unitários:** Base de testes para as camadas de domínio e dados.

### ⏳ Próximos Passos
*   [ ] **Qualidade de Código:**
    *   [ ] Adicionar **LeakCanary** para detecção de vazamentos de memória.
*   [ ] **Performance e Funcionalidades:**
    *   [ ] **Paginação na Lista:** Implementar paginação nas listas (ex: jogos, gêneros) usando a biblioteca Paging 3 do Jetpack.
    *   [ ] **Cache de Dados com Room:** Expandir a implementação de cache com Room para as novas features (Gêneros, Lojas, etc.).
*   [ ] **Feature de Gêneros:**
    *   [ ] Listagem de todos os gêneros de jogos.
    *   [ ] Tela de detalhes para cada gênero.
*   [ ] **Feature de Lojas:**
    *   [ ] Listagem de todas as lojas.
    *   [ ] Tela de detalhes para cada loja.
*   [ ] **Testes de UI:**
    *   [ ] Adicionar testes de UI com Jetpack Compose.
*   [ ] **CI/CD:**
    *   [ ] Configurar um pipeline de Integração e Entrega Contínua (ex: GitHub Actions).

</details>

<details>
<summary><h2>🔑 Configuração da API Key</h2></summary>

Este projeto requer uma chave de API da [RAWG.io](https://rawg.io/login) para acessar os dados dos jogos. Siga os passos abaixo para configurá-la:

1.  Crie um arquivo chamado `local.properties` na raiz do seu projeto (no mesmo nível de `settings.gradle.kts`).
2.  Adicione sua chave de API a este arquivo no seguinte formato:

    ```properties
    API_KEY="SUA_API_KEY_AQUI"
    ```
    Substitua `"SUA_API_KEY_AQUI"` pela sua chave de API real.

Após configurar a chave, sincronize o projeto com o Gradle para que a chave seja injetada no `BuildConfig`.

</details>
