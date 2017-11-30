# TheMovieDBChallenge
This is a repository that contains an Android project related to a Challenge test to the company Fast shop, which is a movie catalog that contains 3 levels: genres, movies and movie details. The movies information was obtained by The Movie Database API

**Arquitetura**

Para o desenvolvimento do projeto do catálogo de filmes, primeiro foi verificado qual seria a arquitetura mais adequada para tal. Dentre as várias disponíveis, foi escolhido o MVP (Model View Presenter). Mais detalhes sobre tal arquitetura podem ser vistos abaixo.

![MVP](http://i68.tinypic.com/1zxb3u1.png)

Como pode ser visto, o MVP separa as camadas de visualização (View) da de negócio e dados (Model), onde há a evocação de eventos através da chamada de uma camada de apresentação (Presenter), onde é organizado como será implementado tudo e realizando a intermediação entre a View e a Model (podendo ser dados locais ou remotos). No caso do aplicativo desenvolvido aqui, o Presenter tem como função implementar métodos de chamadas para APIs.
Consequentemente, a estrutura fica mais organizada e o código mais limpo e com uma maior manutenibilidade e testabilidade.

Um exemplo de como foi implementado tal arquitetura no projeto é a de listagem de gêneros, o qual possui menos dados e fica melhor para expor neste documento. A imagem abaixo mostra como tal implementação foi realizada:

![Class diagram](http://i64.tinypic.com/v5lm6f.png)

Há uma interface geral chamada GeneralContract<T> que possui um tipo genérico (Generics) para seu método onSuccess(T t). Então, com tal interface criada, foi criada a interface GenresListContract que herda da GeneralContract<ArrayList<Genre>>, ou seja, o tipo que é enviado para o onSuccess da interface mãe é um ArrayList<Genre>. Então, há uma outra interface interna nessa chamada de Presenter, o qual tem como função ser implementada na classe GenresListPresenter, a qual possui a implementação de fato do método getGenresList(). A interface é basicamente utilizada para diminuir o acoplamento entre as classes e para servir realmente como um contrato entre elas. A classe GenresListPresenter possui uma dependência com a interface RestApi, que basicamente possui os métodos para a API através da utilização do Retrofit.

**Testes**

Após decidir a arquitetura, foi pensado em como testar a aplicação, o qual foi iniciado por testes unitários, os quais englobam regras de negócio e dados. Foi implementado o teste unitário inicial dos Presenters, como a classe GenresListPresenterTest, que através da utilização do PowerMockito, pôde-se simular a chamada e resultado desse Presenter. Também foram feitos testes das classes de conversão de JSON para objeto, como por exemplo a classe GenresListParseJsonFromStringTest que testa, a partir de uma string de JSON estática, se a conversão é realizada corretamente e o dado é o esperado.

Com a implementação de testes antes do desenvolvimento do aplicativo em si, chamado de TDD (Test Driven Development), há uma maior garantia da qualidade do código, além de um maior entendimento das regras de negócio e posterior manutenibilidade, pois, caso tenha que ser feita alguma mudança em uma parte do código, isso pode ser feito de maneira mais facilitada e caso aconteça algum erro, este será facilmente identificado e o impacto que este pode causar.


**Tecnologias utilizadas**

As tecnologias utilizadas foram as seguintes:

- Android Studio
- Retrofit
- Glide
- PowerMockito


**Android Studio**

O Android Studio foi utilizado pois, não só por ser uma requisição do projeto, mas como é a ferramenta oficial e a mais estável para se desenvolver projetos Android. No momento em que esta apresentação foi escrita, o Android Studio se encontra na versão 3.0.1 (stable) e possui diversos benefícios, como um emulador próprio que possui uma alta velocidade se comparado com versões anteriores, as quais muitos desenvolvedores costumavam optar por utilizar o Genymotion.


**Retrofit**

O Retrofit foi utilizado pois, primeiramente, foi uma requisição do projeto e, tão importante quanto, é uma excelente library para comunicação com APIs. Realiza processos assíncronos e possui um tratamento para erros muito apurado.

**Glide**

O Glide foi utilizado nesse projeto pois para realizar o download e cache das diversas imagens dos filmes, seria complicado implementar isso manualmente. Essa library é muito estável, realiza o cache das imagens nativamente, realiza o loading das imagens assincronamente, consegue tratar erros que possam ocorrer no processo, etc.

**PowerMockito**

O PowerMockito foi utilizado para poder haver uma forma de obter mocks (instância de uma classe gerada por ele) de classes diretamente nas classes de teste numa forma simples e direta.
