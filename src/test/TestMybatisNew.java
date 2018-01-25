package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pojo.Category;
import pojo.Product;

//一对多数据查询
public class TestMybatisNew {
	public static void main(String[] args) throws IOException {
		String source = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(source);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
		List<Category> cs = session.selectList("listCategoryNew");
		for(Category category : cs) {
			System.out.println(category);
			List<Product> ps = category.getProducts();
			for(Product product : ps) {
				System.out.println("\t" + product);;
			}
		}
	}
}
