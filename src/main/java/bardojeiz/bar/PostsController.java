package bardojeiz.bar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
}