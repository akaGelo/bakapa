package ru.vyukov.bakapa.controller.repo;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vyukov.bakapa.controller.config.MongoDbConfig;

@RunWith(SpringRunner.class)
@Import({MongoDbConfig.class, ValidationAutoConfiguration.class})
@DataMongoTest
@ActiveProfiles("test")
abstract  public class SuperRepoTest {
}
