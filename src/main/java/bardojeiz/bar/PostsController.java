package bardojeiz.bar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.net.URI;
//Para fazer web request
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import bardojeiz.bar.model.Feed;

@Tag(name = "Posts", description = "Endpoints para gerenciamento de feeds")

@RestController
public class PostsController {

    @Autowired
    private FeedRepository feedRepository;

    @GetMapping("/data")
        @Operation(summary = "Exibe todos as postagens do Feed",
               description = "Exibir todas as postagens do Feed do Bar do Jeiz")
    public List<Feed> getAllFeeds() {
        // Encontra todos os feeds no banco de dados
        return feedRepository.findAll();
    }

    @GetMapping("/new_post")
        @Operation(summary = "Adiciona um novo post",
               description = "Cria um novo item de feed no banco de dados com nome e conteúdo.")
    public Feed newPost(@RequestParam(required = true) String nome, @RequestParam(required = true) String descr){
        Feed newFeed = new Feed(nome, descr);
        Feed save = feedRepository.save(newFeed);
        return save;
    }

    @GetMapping(path = "/old_data", produces = "application/json")
        @Operation(summary = "Dados do antigo BackEnd", description = "Dados do antigo endpoint em NodeJS do Bar do Jeiz")
    public String getOldPosts(){
        
        HttpClient client = HttpClient.newHttpClient();

                // Cria a requisição
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://bardo-jeiz-server.vercel.app/data"))
                .header("Accept", "application/json") // Adiciona o cabeçalho Accept
                .GET()
                .build();

        System.out.println(request);

        try {
            // Envia a requisição e recebe a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        
        
    }
}