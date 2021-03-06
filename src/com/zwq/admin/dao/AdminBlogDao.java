package com.zwq.admin.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zwq.admin.domain.Admin;
import com.zwq.blog.domain.Blog;
import com.zwq.category.domain.Category;
import com.zwq.user.domain.User;

import cn.itcast.jdbc.TxQueryRunner;

public class AdminBlogDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Blog> findBlogList() throws SQLException {
		String sql = "select * from blog order by bid desc";
		 List<Blog>bloglist = qr.query(sql, new BeanListHandler<Blog>(Blog.class));
		 return bloglist;
	}
	
	public List<Category> findCategoryList() throws SQLException {
		String sql = "select * from category order by clevel desc,cid desc";
		 List<Category>categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
		 return categoryList;
	}
	
	public int deleteBlogByBid(int bid) throws SQLException {
		String sql = "delete from blog where bid =?";
		return qr.update(sql,bid);
	}
	
	public int updateBlogByBid(Blog blog) throws SQLException {
		String sql = "update blog set title =?,content=?,category_id =?,created_time=? where bid=?";
		Object[]params = new Object[] {blog.getTitle(),blog.getContent(),blog.getCategory_id(),blog.getCreated_time(),blog.getBid()};
		return qr.update(sql,params);
	}
	
	public Blog findByBidGetBlog(int bid) throws SQLException {
		String sql = "select bid,category_id,title,content,created_time,cname from blog b,category c where b.`category_id`=c.`cid`and bid =?";
		return qr.query(sql, new BeanHandler<Blog>(Blog.class),bid);
	}	
	
	
	
	
	
	
	
	
}
