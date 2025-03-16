package com.nafiuzzaman.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nafiuzzaman.model.Product;

@Repository
public class ProductRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// RowMapper to map ResultSet to Product object
	private static final class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getLong("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setQuantity(rs.getInt("quantity"));
			product.setPurchaseDate(rs.getDate("purchase_date").toLocalDate());
			product.setSellDate(rs.getDate("sell_date").toLocalDate());
			product.setAmount(rs.getDouble("amount"));
			return product;
		}
	}

	// CRUD Operations
	public List<Product> findAll() {
		String sql = "SELECT * FROM product";
		return jdbcTemplate.query(sql, new ProductRowMapper());
	}

	public Product findById(Long id) {
		String sql = "SELECT * FROM product WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
	}

	public void save(Product product) {
		String sql = "INSERT INTO product (name, price, quantity, purchase_date, sell_date, amount) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getQuantity(),
				product.getPurchaseDate(), product.getSellDate(), product.getAmount());
	}

	public void update(Product product) {
		String sql = "UPDATE product SET name = ?, price = ?, quantity = ?, purchase_date = ?, sell_date = ?, amount = ? WHERE id = ?";
		jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getQuantity(),
				product.getPurchaseDate(), product.getSellDate(), product.getAmount(), product.getId());
	}

	public void deleteById(Long id) {
		String sql = "DELETE FROM product WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
}