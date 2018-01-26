package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.One;

import pojo.Product;

public interface ProductMapper {
	  
    @Select(" select * from product_ where cid = #{cid}")
    public List<Product> listByCategory(int cid);
    
    //���һ
    @Select(" select * from product_ ")
    @Results({ 
        @Result(property="category",column="cid",one=@One(select="mapper.CategoryMapper.getNew")) 
    })
    public List<Product> list();
    
    //��Զ�
    @Select("select * from product_ where id = #{id}")
    public Product get(int id);
}
