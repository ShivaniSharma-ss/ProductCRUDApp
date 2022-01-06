package productcrudapp.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.transaction.*;

import productcrudapp.model.Product;

@Component
public class productDAO {
	@Autowired
private HibernateTemplate hibernateTemplate;
@Transactional
	public void createProduct(Product product)
      {
		this.hibernateTemplate.saveOrUpdate(product);
	  }

@Transactional
public void updateProduct(Product product)
  {
	this.hibernateTemplate.update(product);
  }


public List<Product>getProducts()
{
	List<Product>products=this.hibernateTemplate.loadAll(Product.class);
	return products;
}
@Transactional
public void deleteProduct(int pid)
{
Product p=this.hibernateTemplate.load(Product.class,pid);	
this.hibernateTemplate.delete(p);
}

public Product getProduct(int pid)
{
	Product p=this.hibernateTemplate.get(Product.class,pid);
	return p;
}

}

