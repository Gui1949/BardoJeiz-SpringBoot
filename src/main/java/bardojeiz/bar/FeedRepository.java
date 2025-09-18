package bardojeiz.bar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bardojeiz.bar.model.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
}