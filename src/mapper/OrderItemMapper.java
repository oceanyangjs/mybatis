package mapper;
 
import java.util.List;
 
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
 
import pojo.OrderItem;
 
public interface OrderItemMapper {
     
    @Select(" select * from order_item_ where oid = #{oid}")
    @Results({ 
        @Result(property="product",column="pid",one=@One(select="mapper.ProductMapper.get")) 
    }) 
    public List<OrderItem> listByOrder(int oid);
}
