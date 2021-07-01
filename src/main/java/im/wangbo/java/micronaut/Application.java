package im.wangbo.java.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Application
    {
    private static final Logger L = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws Exception
        {
        try (final ApplicationContext context = Micronaut.run(Application.class, args))
            {
            final UserRepository repository = context.getBean(UserRepository.class);

            if (false)
                {
                final DUser u = new DUser();
                u.setName("Elvis Wang");
                u.setBirthday(OffsetDateTime.now());

                u.setCreatedAt(Instant.now().toEpochMilli());
                u.setLastUpdatedAt(u.getCreatedAt());
                final DUser inserted = repository.save(u);

                L.info(inserted::toString);
                }
            {
            final Stream<DUser> users = repository.findByBirthdayNotAfter(OffsetDateTime.now());
            {
            users.forEach(_v -> L.info("Birthday: " + _v.toString()));
            }
            }

            final long result = repository.count();
            L.info(() -> String.format("Result => %d", result));

//            TimeUnit.SECONDS.sleep(10L);
            }

        L.info("Application exited !!!");
        }
    }
