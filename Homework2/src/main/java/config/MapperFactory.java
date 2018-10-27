package config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import main.Main;

import javax.annotation.Resource;

public class MapperFactory {

    private static SqlSessionFactory factory;

    static {
        String resource = "/mybatis-config.xml";
        InputStream inputStream = Resource.class.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSession() throws IOException {
        return factory.openSession(true);
    }

//    public static <T> void processMapper(Class<T> clazz, Consumer<T> consumer) {
//        try (SqlSession session = getSession()) {
//            T mapper = session.getMapper(clazz);
//            consumer.accept(mapper);
//        }
//    }
}
