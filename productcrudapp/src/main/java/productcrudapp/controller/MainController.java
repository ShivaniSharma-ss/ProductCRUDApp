package productcrudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import productcrudapp.DAO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.model.Product;

@Controller
public class MainController {
	@Autowired
	private productDAO productDAO;

	@RequestMapping("/")
	public String home(Model m)
	{
List<Product>products=productDAO.getProducts();
m.addAttribute("products",products);
		return "index";
		
	}
	//show add product form
	@RequestMapping("/addProduct")
	public String addProduct(Model m)
	{
		m.addAttribute("title","Add Product");
		return "addProductForm";
	}
	//handle add product form
	@RequestMapping(value="/handleProduct",method=RequestMethod.POST)
	
	public RedirectView handleProduct(@ModelAttribute Product product,HttpServletRequest request)
	{
		//System.out.println(product);
		productDAO.createProduct(product);
		RedirectView rd=new RedirectView();
		rd.setUrl(request.getContextPath()+ "/");
		return rd;
		
	}
	//delete handler
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId,HttpServletRequest request)
	{
		this.productDAO.deleteProduct(productId);
		RedirectView rd=new RedirectView();
		rd.setUrl(request.getContextPath()+ "/");
		return rd;
	}
	
	
	
	@RequestMapping("/update/{productId}")
	public String updateForm(@PathVariable("productId")int productId,Model m)
	{
		Product p=this.productDAO.getProduct(productId);
		m.addAttribute("product",p);
		return "updateForm";
	}
	
	
	
	
	
}
