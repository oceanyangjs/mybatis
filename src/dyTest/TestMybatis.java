package dyTest;
   
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.CategoryMapper;
import mapper.OrderMapper;
import mapper.ProductMapper;
import pojo.Category;
import pojo.Order;
import pojo.OrderItem;
import pojo.Product;
   
public class TestMybatis {
   
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
        ProductMapper mapper1 = session.getMapper(ProductMapper.class);
  
//        add(mapper);
//        delete(mapper);
        //get(mapper);
//        update(mapper);
        //listAll(mapper);
        //listAllNew(mapper);
        //listNewAll(mapper1);
        listOrder(session);
              
        session.commit();
        session.close();
   
    }
  
    private static void update(CategoryMapper mapper) {
        Category c= mapper.get(8);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }
  
    private static void get(CategoryMapper mapper) {
        Category c= mapper.get(1);
        System.out.println(c.getName());
    }
  
    private static void delete(CategoryMapper mapper) {
        mapper.delete(2);
        listAll(mapper);
    }
  
    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
        listAll(mapper);
    }
   
    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
    //1对多查询数据
    private static void listAllNew(CategoryMapper mapper) {
    	List<Category> cs = mapper.listNew();
    	for(Category c : cs) {
    		System.out.println(c.getName());
    		List<Product> ps = c.getProducts();
    		for(Product p : ps) {
    			System.out.println("\t" + p.getName());
    		}
    	}
    }
    //多对一查询
    private static void listNewAll(ProductMapper mapper) {
    	List<Product> ps= mapper.list();
        for (Product p : ps) {
            System.out.println(p + "\t对应的分类是:\t" + p.getCategory().getName());
        }
    }
    //多对多查询
    private static void listOrder(SqlSession session) {
        OrderMapper mapper =session.getMapper(OrderMapper.class);
        List<Order> os = mapper.list();
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois= o.getOrderItems();
            if(null!=ois){
                for (OrderItem oi : ois) {
                    System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(),oi.getProduct().getPrice(),oi.getNumber());
                }              
            }
 
        }
    }
}
