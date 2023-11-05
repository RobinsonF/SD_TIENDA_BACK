package com.DAO.TiendaVirtualSB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.DTO.TiendaVirtualSB.ProveedorVO;;

public class ProveedorDAO {

	public void insertProveedor(ProveedorVO prov) {
		Conexion conex = new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO proveedores(nit_prov, ciudad_prov,direccion_prov,nombre_prov, telefono_prov) VALUES ('" + prov.getNit_prov() + "', '" + prov.getCiudad_prov()
					+ "', '" + prov.getDireccion_prov() + "', '" + prov.getNombre_prov() + "', '" + prov.getTelefono_prov() + "')");
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<ProveedorVO> consultarProveedores() {
		Conexion conex = new Conexion();
		List<ProveedorVO> proveedores = new ArrayList<>();

		try {
			Connection connection = conex.getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM proveedores");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ProveedorVO proveedor = new ProveedorVO();
				proveedor.setNit_prov(resultSet.getString("nit_prov"));
				proveedor.setCiudad_prov(resultSet.getString("ciudad_prov"));
				proveedor.setDireccion_prov(resultSet.getString("direccion_prov"));
				proveedor.setNombre_prov(resultSet.getString("nombre_prov"));
				proveedor.setTelefono_prov(resultSet.getString("telefono_prov"));
				proveedores.add(proveedor);
			}

			resultSet.close();
			statement.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return proveedores;
	}

	public ArrayList<ProveedorVO> consultarProveedores(String nit) {
		ArrayList<ProveedorVO> proveedores = new ArrayList<ProveedorVO>();
		Conexion conex = new Conexion();

		String sql = "SELECT * FROM proveedores ";
		if (!nit.equals("null")) {
			sql = sql + "WHERE nit_prov = '" + nit + "'";
		}

		try {
			Statement consulta = conex.getConnection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				ProveedorVO prov = new ProveedorVO(res.getString("nit_prov"), res.getString("ciudad_prov"),
						res.getString("direccion_prov"), res.getString("nombre_prov"), res.getString("telefono_prov"));
				proveedores.add(prov);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return proveedores;
	}

	public void actualizarProveedor(ProveedorVO proveedor) {
		Conexion conex = new Conexion();

		try {
			Connection connection = conex.getConnection();
			String query = "UPDATE proveedores SET ciudad_prov=?, direccion_prov=?, nombre_prov=?, telefono_prov=? WHERE nit_prov=?";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, proveedor.getCiudad_prov());
			statement.setString(2, proveedor.getDireccion_prov());
			statement.setString(3, proveedor.getNombre_prov());
			statement.setString(4, proveedor.getTelefono_prov());
			statement.setString(5, proveedor.getNit_prov());

			statement.executeUpdate();

			statement.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void eliminarProveedor(String nit) {
		Conexion conex = new Conexion();

		try {
			Connection connection = conex.getConnection();
			String query = "DELETE FROM proveedores WHERE nit_prov=?";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, nit);

			statement.executeUpdate();

			statement.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
