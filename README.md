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

*(Espaço reservado para adicionar capturas de tela e gifs do aplicativo)*

| Plataformas | Detalhes da Plataforma | Erro |
| :--------------: | :--------------: | :--------------: |
| ![Lista](https://github.com/user-attachments/assets/53bc3d7b-e8fc-4913-88eb-2ee4d8e2e9f6) | ![Detalhes1](https://github.com/user-attachments/assets/03e5880b-7477-4f51-8cc1-9a7e4e1a47b2) | ![Erro](https://github.com/user-attachments/assets/328cdf0a-4b7b-4852-b892-d7afcc45b4f3) | 
|  | ![Detalhes2](https://github.com/user-attachments/assets/91cee71d-f4f7-4764-a1d7-470273adad65) |  |


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
-   **Testes:**
    -   [JUnit4](https://junit.org/junit4/)
    -   [MockK](https://mockk.io/) para mocking.
    -   [Turbine](https://github.com/cashapp/turbine) para testar Flows.

</details>

<details>
<summary><h2>🗺️ Roadmap</h2></summary>

Esta é a primeira versão alfa do GameDex. O que já foi feito e o que vem por aí:

### ✅ Versão 1.0.0-alpha
*   [x] Estrutura do projeto com Clean Architecture.
*   [x] Módulos `:app`, `:data`, e `:domain`.
*   [x] Integração com a API da RAWG.io.
*   [x] Injeção de dependência configurada com Koin.
*   [x] **Feature de Plataformas:**
    *   [x] Listagem de todas as plataformas de jogos.
    *   [x] Tela de detalhes para cada plataforma.
*   [x] **Cache com Room:** Implementação de cache para a feature de Plataformas, permitindo uso offline básico.
*   [x] Testes unitários para a camada de domínio e dados.

### ⏳ Próximos Passos
*   [ ] **Qualidade de Código:**
    *   [ ] Integrar **Detekt** para análise estática.
    *   [ ] Integrar **JaCoCo** para relatórios de cobertura de testes.
    *   [ ] Adicionar **LeakCanary** para detecção de vazamentos de memória.
*   [ ] **Feature de Gêneros:**
    -   [ ] Listagem de todos os gêneros de jogos.
    -   [ ] Tela de detalhes para cada gênero.
*   [ ] **Feature de Lojas:**
    -   [ ] Listagem de todas as lojas.
    -   [ ] Tela de detalhes para cada loja.
*   [ ] **Feature de Jogos:**
    -   [ ] Listagem de jogos por plataforma/gênero/loja.
    -   [ ] Tela de detalhes do jogo.
*   [ ] **Busca:**
    -   [ ] Implementar funcionalidade de busca por jogos, plataformas, etc.
*   [ ] **Generalizar Cache/Offline:**
    *   [ ] Expandir a implementação de cache com Room para as novas features (Gêneros, Lojas, etc.).
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
