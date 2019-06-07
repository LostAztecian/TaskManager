package tm.server.repository.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import tm.common.entity.Task;
import tm.server.repository.mybatis.mapper.TaskMapper;

import java.io.InputStream;
import java.util.List;

public class TestRepositoryMyBatis {

    public void test() throws Exception {
        final String resource = "mybatis-config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            SqlSessionFactory sqlSessionFactory
                    = new SqlSessionFactoryBuilder().build(inputStream);
            final SqlSession session = sqlSessionFactory.openSession();
            session.getConfiguration().addMapper(TaskMapper.class);

            TaskMapper mapper = session.getMapper(TaskMapper.class);
            //findall
            final List<Task> users = mapper.findAll();
            for (final Task user : users) {
                System.out.println(user);
            }
        }
    }

}
