package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pojo.Category;

public class TestMybasis {
	public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        
        listAll(session);
        
        //增加
        System.out.println("增加");
        Category c = new Category(); 
        c.setName("新增的categoty");
        session.insert("addCategory",c);
        listAll(session);
        
        //删除
        System.out.println("删除");
        Category c1 = new Category(); 
        c1.setId(7);
        session.delete("deleteCategory",c1);
        listAll(session);
        
        //查询
        System.out.println("查询");
        Category c2 = new Category(); 
        Category c4 = session.selectOne("getCategory",1);
        System.out.println(c4.getName());
        listAll(session);
        
        //修改
        System.out.println("修改");
        Category c3 = new Category(); 
        c3.setId(2);
        c3.setName("新的categoty");
        session.update("updateCategory",c3);
        listAll(session);
        
        //模糊查询
        System.out.println("模糊查询");
        List<Category> cs = session.selectList("listCategoryByName","新");
        for (Category c5 : cs) {
            System.out.println(c5.getName());
        }

        //多条件查询
        System.out.println("多条件查询");
        Map<String, Object> params = new HashMap<>();
        params.put("id", 8);
        params.put("name", "新");
        List<Category> cs1 = session.selectList("listCategotyByIdAndName",params);
        for (Category c6 : cs1) {
            System.out.println(c6.getName());
        }
        
        
        session.commit();
        session.close();  
    }
	
	private static void listAll(SqlSession session) {
        List<Category> cs=session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
	}
}
