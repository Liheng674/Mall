package json.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.lee.bean.T_MALL_CLASS_1;
import com.lee.mapper.T_MALL_CLASS_1_mapper;

class TestJson {

	@Test
	void test() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 1.获取SqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		// 2.获取SqlSession，相当于JDBC中的Connection
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// 3.获取Mapper对象
			T_MALL_CLASS_1_mapper mapper = session.getMapper(T_MALL_CLASS_1_mapper.class);
			List<T_MALL_CLASS_1> list = mapper.getTMallClass1();
			System.out.println(list.size());
			// 转JSON
			Gson gson = new Gson();
			String json = gson.toJson(list);
			
			FileOutputStream fos = new FileOutputStream("D:\\t_mall_class1.js");
			fos.write(json.getBytes());
			fos.close();
		} finally {
			// 5.关闭sqlSession
			session.close();
		}
	}
}
