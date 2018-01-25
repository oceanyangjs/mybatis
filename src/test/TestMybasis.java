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
        
        //����
        System.out.println("����");
        Category c = new Category(); 
        c.setName("������categoty");
        session.insert("addCategory",c);
        listAll(session);
        
        //ɾ��
        System.out.println("ɾ��");
        Category c1 = new Category(); 
        c1.setId(7);
        session.delete("deleteCategory",c1);
        listAll(session);
        
        //��ѯ
        System.out.println("��ѯ");
        Category c2 = new Category(); 
        Category c4 = session.selectOne("getCategory",1);
        System.out.println(c4.getName());
        listAll(session);
        
        //�޸�
        System.out.println("�޸�");
        Category c3 = new Category(); 
        c3.setId(2);
        c3.setName("�µ�categoty");
        session.update("updateCategory",c3);
        listAll(session);
        
        //ģ����ѯ
        System.out.println("ģ����ѯ");
        List<Category> cs = session.selectList("listCategoryByName","��");
        for (Category c5 : cs) {
            System.out.println(c5.getName());
        }

        //��������ѯ
        System.out.println("��������ѯ");
        Map<String, Object> params = new HashMap<>();
        params.put("id", 8);
        params.put("name", "��");
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
