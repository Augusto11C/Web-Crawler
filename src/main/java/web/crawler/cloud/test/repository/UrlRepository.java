package web.crawler.cloud.test.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import web.crawler.cloud.test.entity.Url;

import java.util.List;

public interface UrlRepository extends MongoRepository<Url, ObjectId> {

    Url findByUrl(String url);

}
