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
 
import pojo.Product;
  
public class TestMybatisDy {
  
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
  
        System.out.println("��ѯ���е�");
        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p);
        }
         
        System.out.println("ģ����ѯ");
        Map<String,Object> params = new HashMap<>();
        params.put("name","a");
        List<Product> ps2 = session.selectList("listProduct",params);
        for (Product p : ps2) {
            System.out.println(p);
        }      
         
        session.commit();
        session.close();
  
    }
}
