package cn.itcast.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.itcast.domain.Product;

public interface ProductDao {
	
	@Select(value="select * from product ")
	public List<Product> findAll() throws Exception;
	
	@Insert(value="insert into product values(common_sequence.nextval,#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
	public void save (Product product) throws Exception;

	@Select("select * from product where id = #{id}")
	public Product findProductById(int productId) throws Exception;

	@Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},"
			+ "departureTime=#{departureTime},productPrice=#{productPrice},"
			+ "productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
	public void updateProduct(Product product) throws Exception;

	@Delete("delete from product where id =#{id}")
	public void deleteProduct(int productId);
	
	@Select("select count(1) from product")
	public int findTotalCount();

	@Select("select * from"
			+ "(select rownum r ,product.* from product where rownum<#{endIndex} ) t where t.r>#{startIndex}")
	public List<Product> findProductByPage(@Param("startIndex")int startIndex, @Param("endIndex")int endIndex);
	//因为是两个int属性的参数,所以需要将两个参数设置分别不同的属性,这样才可以使用el表达式按照属性获取传过来的参数
}
